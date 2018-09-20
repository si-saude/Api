package br.com.saude.api.model.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.AtestadoBuilder;
import br.com.saude.api.model.creation.builder.example.AtestadoExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.EmailFactory;
import br.com.saude.api.model.entity.dto.AtestadoDto;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Atestado;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.HistoricoAtestado;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.AtestadoDao;
import br.com.saude.api.model.persistence.report.AtestadoReport;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusAtestado;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TipoConvocacao;

public class AtestadoBo
		extends GenericBo<Atestado, AtestadoFilter, AtestadoDao, AtestadoBuilder, AtestadoExampleBuilder>
		implements GenericReportBo<AtestadoDto> {

	protected Function<AtestadoBuilder, AtestadoBuilder> functionLoadRegime;
	
	private static AtestadoBo instance;

	private AtestadoBo() {
		super();
	}

	public static AtestadoBo getInstance() {
		if (instance == null)
			instance = new AtestadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadRegime = builder -> {
			return builder.loadRegime();
		};
		this.functionLoadAll = builder -> {
			return builder.loadRegime().loadAgendamento().loadExamesConvocacao().loadHistoricoAtestados();
		};
	}
	
	public Atestado cancelarAgendamento(Atestado atestado) throws Exception {
		
		if ( atestado.getAgendamento() != null && atestado.getAgendamento().getId() > 0 ) {
			Tarefa tarefa = TarefaBo.getInstance().getById(atestado.getAgendamento().getId());
			 tarefa.setStatus(StatusTarefa.getInstance().CANCELADA);
			 TarefaBo.getInstance().save(tarefa);
		}
		
		atestado.setAgendamento(null);
		
		return atestado;
	}
	
	@Override
	public Atestado getById(Object id) throws Exception {
		Atestado atestado = getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		atestado = definirDatasLimites(atestado);
		
		atestado.setPreviewStatus(atestado.getStatus());
		atestado = loadFiles(atestado);
		
		return atestado;
	}
	
	public Atestado recalcularLimites (Atestado atestado) throws Exception {
		//DEFINIR LIMITES
		atestado = definirLimites(atestado);
		//DEFINIR DATAS LIMITES
		atestado = definirDatasLimites(atestado);

		return atestado;
	}

	private Atestado definirDatasLimites(Atestado atestado) throws Exception {
		Calendar data = Calendar.getInstance();
		
		data.setTime(atestado.getDataSolicitacao());
		atestado.setDataLimiteAuditar(
				FeriadoBo.getInstance().getValidDates(data, atestado.getLimiteAuditar()).getTime());
		
		if(atestado.isPresencial() && atestado.getDataAuditoria() != null) {
			data.setTime(atestado.getDataAuditoria());
			atestado.setDataLimiteAgendamento(
					FeriadoBo.getInstance().getValidDates(data, atestado.getLimiteHomologar()).getTime());
		}else
			atestado.setDataLimiteAgendamento(null);
		
		if ( atestado.getAgendamento() != null && atestado.getAgendamento().getId() > 0 && 
				atestado.getAgendamento().getStatus().equals(StatusTarefa.getInstance().CONCLUIDA)) {
			data.setTime(atestado.getAgendamento().getFim());
			atestado.setDataLimiteLancar(
					FeriadoBo.getInstance().getValidDates(data, atestado.getLimiteLancar()).getTime());
		}else
			atestado.setDataLimiteLancar(null);
		
		if ( atestado.getDataAuditoria() != null ) {
			data.setTime(atestado.getDataAuditoria());
			atestado.setDataLimiteHomologar(
					FeriadoBo.getInstance().getValidDates(data, atestado.getLimiteHomologar()).getTime());
		}else
			atestado.setDataLimiteHomologar(null);
		
		return atestado;
	}
	
	public PagedList<Atestado> getListAll(AtestadoFilter filter) throws Exception {
		return super.getList(getDao().getListAll(getExampleBuilder(filter).example()), this.functionLoadAll);
	}
	
	public List<AtestadoDto> getAtestados() throws Exception {
		return AtestadoReport.getInstance().getAtestados();
	}

	public boolean verificarAtrasoAtestado(Atestado atestado) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(atestado.getInicio());
		calendar = FeriadoBo.getInstance().getValidDates(calendar, 4);
		
		Calendar today = Calendar.getInstance();
		today.setTime(Helper.getToday());

		if (calendar.before(today))
			return false;
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public Atestado save(Atestado atestado) throws Exception {
		if ( atestado.getStatus().equals(StatusAtestado.HOMOLOGADO) && !atestado.isAusenciaExames() ) {
			if ( atestado.getExamesConvocacao() == null || atestado.getExamesConvocacao().size() == 0 ) {
				throw new Exception("Por favor, adicione exames para o atestado ou selecione ausência de exames.");
			}
		}
		
		if ( atestado.getStatus().equals(StatusAtestado.ANALISE_TECNICA) && atestado.getDataAuditoria() == null )
			throw new Exception("É necessário informar a data da auditoria.");
		
		if( !atestado.isPresencial() && atestado.getAgendamento() != null && atestado.getAgendamento().getId() > 0) {
			throw new Exception("Não é possível salvar o atestado que não seja presencial e tenha agendamento. "
					+ "Favor cancelar o agendamento.");
		}
		
		if ( atestado.getTarefa() != null && ((  
				atestado.getTarefa().getEquipe() != null && 
				atestado.getTarefa().getServico().getId() == 0 ) ||
				( atestado.getTarefa().getId() == 0 &&  
				atestado.getStatus().equals(StatusAtestado.getInstance().ANALISE_TECNICA) )) ) 
			atestado = configureTarefaAtestado(atestado);
		
		if ( atestado.getTarefa() != null && atestado.getTarefa().getId() > 0 && 
				atestado.getStatus().equals(StatusAtestado.getInstance().ANALISE_TECNICA) &&
				atestado.getPreviewStatus().equals(StatusAtestado.getInstance().ANALISE_ADMINISTRATIVA)) {
			atestado.getTarefa().setStatus(StatusTarefa.getInstance().EXECUCAO);
			atestado.getTarefa().setAtualizacao(Helper.getToday());
			atestado.getTarefa().setInicio(Helper.getToday());
		}
		
		if(!atestado.isConvocado() && atestado.getStatus().equals(StatusAtestado.HOMOLOGADO)) {
			
			atestado.setConvocado(true);

			String tipoConvocacao = "";
			Servico servico = null;
			
			EquipeFilter equipeFilter = new EquipeFilter();
			equipeFilter.setPageNumber(1);
			equipeFilter.setPageSize(1);
			
			if(atestado.getNumeroDias() >= 30) {
				tipoConvocacao = TipoConvocacao.RETORNO_AO_TRABALHO;
				servico = servicoRetornoTrabalho();
				equipeFilter.setAbreviacao("MED");
			}else if(atestado.getNumeroDias() >= 5 && atestado.getNumeroDias() < 30 ) {
				tipoConvocacao = TipoConvocacao.PERICIAL;
				servico = servicoExamePericial();
				equipeFilter.setId(atestado.getTarefa().getEquipe().getId());
			}
			
			if(!atestado.isAusenciaExames()) {
				criarConvocacao(atestado,tipoConvocacao);
			}
			else {
				
				Calendar data = Calendar.getInstance();
				data.setTime(getVencimentoAtestado(atestado).getTime());
				data.set(Calendar.HOUR, 8);
				
				Tarefa tarefa = new Tarefa();
				tarefa.setAtualizacao(Helper.getNow());
				tarefa.setCliente(atestado.getEmpregado());
				tarefa.setInicio(data.getTime());
				
				data.set(Calendar.HOUR, 16);
				
				tarefa.setFim(data.getTime());
				tarefa.setServico(servico);
				tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
				tarefa.setEquipe(EquipeBo.getInstance().getList(equipeFilter).getList().get(0));
				
				TarefaBo.getInstance().save(tarefa);
			}
		}
			
		if ( atestado.getStatus().equals(StatusAtestado.getInstance().HOMOLOGADO) && atestado.isAtestadoFisicoRecebido() &&
				atestado.isControleLicenca() && atestado.isLancadoSap() )
			atestado.setStatus(StatusAtestado.getInstance().PENDENTE_DE_ARQUIVAMENTO);
		
		EmailFactory emailFactory = EmailFactory.newInstance();
		emailFactory.assunto("HOMOLOGAÇÃO DE ATESTADO").destinatarios(getDestinatarioEmail(atestado));
		String conteudo = "";
		
		if ( !atestado.isAtestadoFisicoRecebido() ) {
			conteudo += "Prezado(a),</br></br> informamos que o atestado " + 
						atestado.getId() +" original ainda não foi recebido pelo serviço de saúde. </br>";			
		}
		
		if ( !atestado.getPreviewStatus().equals(atestado.getStatus()) ) {
			String complemento = "";
			if ( atestado.getStatus().equals(StatusAtestado.HOMOLOGADO) )
				complemento = "Número de Dias:" + String.valueOf(atestado.getNumeroDias());
			else if ( atestado.getStatus().equals(StatusAtestado.RECUSADO) ) 
				complemento = "Motivo da Recusa: " + atestado.getMotivoRecusa().getDescricao();
			conteudo += "Prezado(a),</br></br> "
					+ "informamos que o status da homologação do atestado "+ 
					atestado.getId() +" é: "+ atestado.getStatus() +". </br>\r\n"+
					complemento;
		}
		
		if ( !conteudo.isEmpty() ) {
			emailFactory.conteudo(conteudo);
			EmailBo.getInstance().save(emailFactory.get());
		}
		
		configureHistoricoAtestado(atestado);
		
		atestado = definirLimites(atestado);
		
		Map<Integer, Integer> anexo = atestado.getAnexo();
		atestado = super.save(atestado);
		atestado.setAnexo(anexo);
		saveFiles(atestado);
		return atestado;
	}

	private List<Empregado> getDestinatarioEmail(Atestado atestado) {
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(atestado.getEmpregado());
		return empregados;
	}

	private void configureHistoricoAtestado(Atestado atestado) {
		if ( atestado.getHistoricoAtestados() == null )
			atestado.setHistoricoAtestados(new ArrayList<HistoricoAtestado>());
		else atestado.getHistoricoAtestados().forEach(ha -> {
				ha.setAtestado(atestado);
			});
		
		HistoricoAtestado historicoAtestado = new HistoricoAtestado();
		historicoAtestado.setAtestado(atestado);
		historicoAtestado.setData(Helper.getToday());
		historicoAtestado.setStatus(atestado.getStatus());
		historicoAtestado.setProfissional(atestado.getProfissional());
		
		atestado.getHistoricoAtestados().add(historicoAtestado);
	}

	private void criarConvocacao(Atestado atestado, String tipoConvocacao) throws Exception {
		
		Convocacao convocacao = new Convocacao();
		
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(atestado.getInicio());
		
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
		empregadoConvocacao.setEmpregado(atestado.getEmpregado());
		empregadoConvocacao.setConvocacao(convocacao);
		empregadoConvocacao.setDataConvocacao(Helper.getNow());
		empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
		
		if ( atestado.getExamesConvocacao() != null && atestado.getExamesConvocacao().size() > 0 ) {
			for ( Exame exame: atestado.getExamesConvocacao() ) {
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
		
		Calendar vencimentoAtestado = getVencimentoAtestado(atestado);
		
		String dtVencimento = vencimentoAtestado.get(Calendar.DATE)+ "/" +
				((Integer)(vencimentoAtestado.get(Calendar.MONTH)+1)).toString() + "/" +
				vencimentoAtestado.get(Calendar.YEAR);
		
		EmailFactory emailFactory = EmailFactory.newInstance().assunto(tipoConvocacao)
				.conteudo("Prezado(a),</br></br> informamos a necessidade da realização do "+tipoConvocacao+
						" no serviço de saúde no dia "+dtVencimento+".")
				.destinatarios(getDestinatarioEmail(atestado))
				.anexos(pathAnexos);
		EmailBo.getInstance().save(emailFactory.get());
	}
	
	private Calendar getVencimentoAtestado(Atestado atestado) throws Exception {
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(atestado.getInicio());
		dtCalendar.add(Calendar.DATE, atestado.getNumeroDias());
		
		return FeriadoBo.getInstance().getValidDates(dtCalendar, 1);
	}

	public Atestado solicitacaoServico(Atestado atestado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		if (atestado.getNumeroDias() >= 5) {
			atestado.setPresencial(true);
		}

		atestado.setDataSolicitacao(Helper.getToday());
		atestado.setStatus(StatusAtestado.ANALISE_ADMINISTRATIVA);
		atestado.setDataSolicitacao(Helper.getToday());
		atestado = definirLimites(atestado);

		String servico = atestado.getTarefa().getServico().getNome();
		atestado.setTarefa(null);
		atestado.setMotivoRecusa(null);

		Atestado at = atestado;
		atestado.getHistoricoAtestados().forEach(ha -> {
			ha.setAtestado(at);
		});
		
		Map<Integer, Integer> anexo = atestado.getAnexo();
		Map<Integer, Integer> anexoRelatorioMedico = atestado.getAnexoRelatorioMedico();
		atestado = super.save(atestado);

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(atestado.getEmpregado());
		
		EmailFactory emailFactory = EmailFactory.newInstance();
		emailFactory.assunto(servico)
				.conteudo("Prezado(a),</br></br> " + "informamos que o seu atestado " + atestado.getId()
						+ " está em ANÁLISE ADMINISTRATIVA. "
						+ "</br>Obs: Atestados de 1 dia devem ser tratados no âmbito da atribuição gerencial. </br>")
				.destinatarios(empregados);
		EmailBo.getInstance().save(emailFactory.get());
		
		atestado.setAnexo(anexo);
		atestado.setAnexoRelatorioMedico(anexoRelatorioMedico);
		saveFiles(atestado);
		return atestado;
	}

	@SuppressWarnings("static-access")
	public Atestado configureTarefaAtestado(Atestado atestado) throws Exception {
		Servico servico = servicoHomologacaoAtestado();

		atestado.getTarefa().setServico(servico);
		atestado.getTarefa().setAtualizacao(Helper.getToday());
		atestado.getTarefa().setInicio(Helper.getToday());
		atestado.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atestado.getTarefa().setCliente(atestado.getEmpregado());

		return atestado;
	}

	private Servico servicoHomologacaoAtestado() throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("0001");
		servicoFilter.setGrupo(GrupoServico.HOMOLOGACAO_ATESTADO);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getList(servicoFilter).getList().get(0);
		return servico;
	}
	
	private Servico servicoRetornoTrabalho() throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("0004");
		servicoFilter.setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getList(servicoFilter).getList().get(0);
		return servico;
	}
	
	private Servico servicoExamePericial() throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("0007");
		servicoFilter.setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getList(servicoFilter).getList().get(0);
		return servico;
	}

	public Atestado definirLimites(Atestado atestado) throws Exception {
		Calendar hoje = Calendar.getInstance();
		
		hoje.setTime(atestado.getDataSolicitacao());

		int qtdDiasUteis = dataLimite(hoje);
		int qtdMax = 9;
		if (!atestado.isPresencial())
			qtdMax = 7;
		if (qtdDiasUteis <= qtdMax)
			qtdMax = qtdDiasUteis;

		atestado.setLimiteAuditar(1);
		atestado.setLimiteHomologar(3);
		atestado.setLimiteLancar(1);

		while (atestado.getLimiteAuditar() + atestado.getLimiteHomologar() + atestado.getLimiteLancar() < qtdMax) {
			if (atestado.isPresencial() && atestado.getLimiteHomologar() < 5 && qtdMax > 5)
				atestado.setLimiteHomologar(atestado.getLimiteHomologar() + 1);
			else if (atestado.getLimiteAuditar() < 2)
				atestado.setLimiteAuditar(atestado.getLimiteAuditar() + 1);
			else
				atestado.setLimiteLancar(atestado.getLimiteLancar() + 1);
		}

		return atestado;
	}

	private int dataLimite(Calendar amanha) throws Exception {
		amanha.setTime(Helper.getToday());
		amanha.add(Calendar.DATE, 1);

		Calendar mesSeguinte = Calendar.getInstance();
		mesSeguinte.setTime(Helper.getToday());
		mesSeguinte.add(Calendar.MONTH, 1);
		mesSeguinte.set(Calendar.DATE, 10);

		int days = FeriadoBo.getInstance().getDaysBetweenDates(amanha, mesSeguinte);

		return days;
	}

	@SuppressWarnings("resource")
	public String getAnexo(int id, String path) throws Exception {
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + path
				+ id + ".pdf");

		File anexoPath = new File(uri.getPath());

		byte[] pdfArray = new byte[(int) anexoPath.length()];

		try {
			new FileInputStream(anexoPath).read(pdfArray);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo não encontrado para esse anexo.");
		} catch (IOException e) {
			throw new Exception("Erro no servidor. Por favor, contacte o administrador do sistema.");
		}

		return Base64.getEncoder().encodeToString(pdfArray);
	}

	@SuppressWarnings({ "resource", "unused" })
	private Atestado loadFiles(Atestado atestado) throws URISyntaxException {

		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "atestado/anexo/"
				+ atestado.getId() + ".pdf");

		File anexoPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(anexoPath);
			byte[] anexoArray = new byte[(int) anexoPath.length()];
			fileInputStreamReader.read(anexoArray);
			atestado.setAnexoBase64(Base64.getEncoder().encodeToString(anexoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return atestado;
	}

	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(Atestado atestado) throws URISyntaxException, IOException {
		if (atestado.getAnexo() != null) {

			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+ "atestado/anexo/" + atestado.getId() + ".pdf");

			File file = new File(uri.getPath());

			file.getParentFile().mkdirs();

			byte[] anexoArray = new byte[atestado.getAnexo().size()];

			for (int i = 0; i < atestado.getAnexo().size(); i++) {
				anexoArray[i] = new Integer(atestado.getAnexo().get(i + "")).byteValue();
			}

			InputStream in;
			FileOutputStream stream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];

			in = new ByteArrayInputStream(anexoArray);

			int len;
			while ((len = in.read(buffer)) > 0) {
				stream.write(buffer, 0, len);
			}

			in.close();
			stream.close();

		}
		if (atestado.getAnexoRelatorioMedico() != null) {

			URI uriRM = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+ "atestado/relatorio/" + atestado.getId() + ".pdf");

			File fileRM = new File(uriRM.getPath());

			fileRM.getParentFile().mkdirs();

			byte[] anexoArrayRM = new byte[atestado.getAnexoRelatorioMedico().size()];

			for (int i = 0; i < atestado.getAnexoRelatorioMedico().size(); i++) {
				anexoArrayRM[i] = new Integer(atestado.getAnexoRelatorioMedico().get(i + "")).byteValue();
			}

			InputStream inRM;
			FileOutputStream streamRM = new FileOutputStream(fileRM);
			byte[] bufferRM = new byte[1024];

			inRM = new ByteArrayInputStream(anexoArrayRM);

			int lenRM;
			while ((lenRM = inRM.read(bufferRM)) > 0) {
				streamRM.write(bufferRM, 0, lenRM);
			}

			inRM.close();
			streamRM.close();
		}
	}

}