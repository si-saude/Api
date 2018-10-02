package br.com.saude.api.model.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.CatBuilder;
import br.com.saude.api.model.creation.builder.example.CatExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.EmailFactory;
import br.com.saude.api.model.entity.dto.CatDto;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.po.Cat;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.CatDao;
import br.com.saude.api.model.persistence.report.CatReport;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TipoConvocacao;

public class CatBo extends GenericBo<Cat, CatFilter, CatDao, CatBuilder, CatExampleBuilder> 
	implements GenericReportBo<CatDto> {

	private static CatBo instance;

	private CatBo() {
		super();
	}

	public static CatBo getInstance() {
		if (instance == null)
			instance = new CatBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadProfissionalCaracterizacao().loadProfissionalClassificacao().
					loadMunicipio().loadAgenteCausador().loadNaturezaLesao().loadParteCorpoAtingida()
					.loadCnae().loadClassificaoGravidade().loadInstalacao().loadDiagnosticoProvavel()
					.loadExamesConvocacao();
		};
	}

	@Override
	public Cat getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public List<CatDto> getCats() throws Exception{
		return CatReport.getInstance().getCats();
	}

	@Override
	public PagedList<Cat> getList(CatFilter filter) throws Exception {
		return super.getList(filter);
	}

	@Override
	public Cat save(Cat cat) throws Exception {
		if ( cat.getClassificacao() != null && cat.getClassificacao().getId() > 0 && !cat.isConvocado() && cat.getDataEmissao() != null ) {
			if ( ( cat.getExamesConvocacao() == null || cat.getExamesConvocacao().size() == 0 ) && !cat.isAusenciaExames() ) {
				throw new Exception("Por favor, adicione exames para o atestado ou selecione ausência de exames.");
			}
			
			cat.setConvocado(true);
			
			String tipoConvocacao = "";
			Servico servico = null;
			
			if(cat.getTempoPrevisto() >= 30) {
				tipoConvocacao = TipoConvocacao.RETORNO_AO_TRABALHO;
				servico = AtestadoBo.getInstance().servicoRetornoTrabalho();
			}else {
				tipoConvocacao = TipoConvocacao.PERICIAL;
				servico = AtestadoBo.getInstance().servicoExamePericial();
			}
			
			if(!cat.isAusenciaExames()) {
				criarConvocacao(cat,tipoConvocacao);
			}
			else {
				EquipeFilter equipeFilter = new EquipeFilter();
				equipeFilter.setPageNumber(1);
				equipeFilter.setPageSize(1);
				equipeFilter.setAbreviacao("MED");
				
				Calendar data = Calendar.getInstance();
				data.setTime(getVencimentoAtestado(cat).getTime());
				data.set(Calendar.HOUR, 8);
				
				Tarefa tarefa = new Tarefa();
				tarefa.setAtualizacao(Helper.getNow());
				tarefa.setCliente(cat.getEmpregado());
				tarefa.setInicio(data.getTime());
				
				data.set(Calendar.HOUR, 16);
				
				tarefa.setFim(data.getTime());
				tarefa.setServico(servico);
				tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
				tarefa.setEquipe(EquipeBo.getInstance().getList(equipeFilter).getList().get(0));
				
				TarefaBo.getInstance().save(tarefa);
			}
		}
		
		return super.save(cat);
	}
	
	private void criarConvocacao(Cat cat, String tipoConvocacao) throws Exception {
		
		Convocacao convocacao = new Convocacao();
		
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(cat.getDataOcorrencia());
		
		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		convocacaoFilter.setTitulo(tipoConvocacao+" - "+dtCalendar.get(Calendar.YEAR));
		convocacaoFilter.setTipo(tipoConvocacao);
		convocacaoFilter.setPageNumber(1);
		convocacaoFilter.setPageSize(1);
		
		PagedList<Convocacao> convocacoes = ConvocacaoBo.getInstance().getList(convocacaoFilter);
		if ( convocacoes.getTotal() > 0 ) { 
			convocacao = convocacoes.getList().get(0);
			convocacao = ConvocacaoBo.getInstance().getById(convocacao.getId());
		} else {			
			dtCalendar = Calendar.getInstance();
			dtCalendar.setTime(Helper.getToday());
			
			convocacao.setTipo(tipoConvocacao);
			convocacao.setTitulo(convocacaoFilter.getTitulo());
			convocacao.setInicio(dtCalendar.getTime());
			
			dtCalendar.set(Calendar.MONTH, 11);
			dtCalendar.set(Calendar.DATE, 31);
			
			convocacao.setFim(dtCalendar.getTime());
		}
		
		if(convocacao.getEmpregadoConvocacoes() == null)
			convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
		
		EmpregadoConvocacao empregadoConvocacao = new EmpregadoConvocacao();
		empregadoConvocacao.setEmpregado(cat.getEmpregado());
		empregadoConvocacao.setConvocacao(convocacao);
		empregadoConvocacao.setDataConvocacao(Helper.getNow());
		empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
		
		if ( cat.getExamesConvocacao() != null && cat.getExamesConvocacao().size() > 0 ) {
			for ( Exame exame: cat.getExamesConvocacao() ) {
				EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
				empregadoConvocacaoExame.setExame(exame);
				empregadoConvocacaoExame.setEmpregadoConvocacao(empregadoConvocacao);
				empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
			}
		}
		
		convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);
		
		if(convocacao.getId() == 0) {
			ProfissiogramaFilter profissiogramaFilter = new ProfissiogramaFilter();
			profissiogramaFilter.setNome(convocacaoFilter.getTitulo());
			profissiogramaFilter.setPageSize(1);
			profissiogramaFilter.setPageNumber(1);
			
			PagedList<Profissiograma> profissiogramas = ProfissiogramaBo.getInstance().getList(profissiogramaFilter); 
			
			if(profissiogramas.getTotal() == 0)
				throw new Exception("Não foi possível encontrar o profissiograma "+convocacaoFilter.getTitulo());
			
			convocacao.setProfissiograma(profissiogramas.getList().get(0));
			convocacao.setGerenciaConvocacoes(new ArrayList<GerenciaConvocacao>());
			convocacao = ConvocacaoBo.getInstance().save(convocacao);
			convocacao = ConvocacaoBo.getInstance().getById(convocacao.getId());
			
			convocacao.getEmpregadoConvocacoes().sort(new Comparator<EmpregadoConvocacao>() {
				@Override
				public int compare(EmpregadoConvocacao arg0, EmpregadoConvocacao arg1) {
					return new Integer(arg1.getId()).compareTo(arg0.getId());
				}
			});
			
			convocacao.getEmpregadoConvocacoes().get(0).setSelecionado(true);
		}else
			empregadoConvocacao.setSelecionado(true);

		String pathAnexos = ConvocacaoBo.getInstance().processarConvocacaoPath(convocacao);
		
		Calendar vencimentoAtestado = getVencimentoAtestado(cat);
		
		String dtVencimento = vencimentoAtestado.get(Calendar.DATE)+ "/" +
				((Integer)(vencimentoAtestado.get(Calendar.MONTH)+1)).toString() + "/" +
				vencimentoAtestado.get(Calendar.YEAR);
		
		EmailFactory emailFactory = EmailFactory.newInstance().assunto(tipoConvocacao)
				.conteudo("Prezado(a),</br></br> informamos a necessidade da realização do "+tipoConvocacao+
						" no serviço de saúde no dia "+dtVencimento+".")
				.destinatarios(getDestinatarioEmail(cat))
				.anexos(pathAnexos);
		EmailBo.getInstance().save(emailFactory.get());
	}
	
	private Calendar getVencimentoAtestado(Cat cat) throws Exception {
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(cat.getDataOcorrencia());
		dtCalendar.add(Calendar.DATE, cat.getTempoPrevisto());
		
		return FeriadoBo.getInstance().getValidDates(dtCalendar, 1);
	}
	
	private List<Empregado> getDestinatarioEmail(Cat cat) {
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(cat.getEmpregado());
		return empregados;
	}
	
}
