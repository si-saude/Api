package br.com.saude.api.model.business;

import java.util.ArrayList;
import java.util.List;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.OrderFilter;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.AsoBuilder;
import br.com.saude.api.model.creation.builder.example.AsoExampleBuilder;
import br.com.saude.api.model.entity.dto.ConformidadeAsoDto;
import br.com.saude.api.model.entity.filter.AsoFilter;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.RequisitoAsoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.AsoAlteracao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Ghe;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.ItemAuditoriaAso;
import br.com.saude.api.model.entity.po.RequisitoAso;
import br.com.saude.api.model.persistence.AsoDao;
import br.com.saude.api.model.persistence.report.ConformidadeAsoReport;
import br.com.saude.api.util.constant.StatusAso;

public class AsoBo 
	extends GenericBo<Aso, AsoFilter, AsoDao, AsoBuilder, AsoExampleBuilder> 
		implements GenericReportBo<ConformidadeAsoDto> {
	
	private static AsoBo instance;
	
	private AsoBo() {
		super();
	}
	
	public static AsoBo getInstance() {
		if(instance==null)
			instance = new AsoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadAtendimento().loadEmpregado();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadAlteracoes().loadAvaliacoes().loadAptidoes().loadExamesConvocacao().loadItemsAuditoriaAso();
		};
	}

	public Aso getUltimoByEmpregado(Aso aso) throws Exception{
		return getDao().getUltimoByEmpregado(aso);
	}
	
	public List<ConformidadeAsoDto> getAsosByAno(int ano) throws Exception {
		return ConformidadeAsoReport.getInstance().getAsosByAno(ano);
	}
	
	@Override
	public Aso getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<Aso> getList(AsoFilter filter) throws Exception {
		
		filter.setAtendimento(new AtendimentoFilter());
		filter.getAtendimento().setTarefa(new TarefaFilter());
		filter.getAtendimento().getTarefa().setStatus("CONCLUÍDA");
		
		return super.getList(filter, this.functionLoad);
	}
	
	public PagedList<Aso> getListLoadAll(AsoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}
	
	public Aso criarItensAuditoriaAso(Aso aso) throws Exception {
			
		EmpregadoConvocacaoFilter empConFilter = new EmpregadoConvocacaoFilter();
		empConFilter.setPageNumber(1);
		empConFilter.setPageSize(1);
		empConFilter.setEmpregado(new EmpregadoFilter());
		empConFilter.getEmpregado().setId(aso.getEmpregado().getId());
		empConFilter.setConvocacao(new ConvocacaoFilter());
		empConFilter.getConvocacao().setTipo(AtendimentoBo.getInstance().getTipoAtendimento(aso.getAtendimento()));
		empConFilter.setDataConvocacao(new DateFilter());
		empConFilter.getDataConvocacao().setTypeFilter(TypeFilter.MENOR_IGUAL);
		empConFilter.getDataConvocacao().setInicio(Helper.getNow());
		empConFilter.setOrder(new OrderFilter());
		empConFilter.getOrder().setDesc(true);
		empConFilter.getOrder().setProperty("dataConvocacao");
		//ALTERAR PARA LISTALL
		
		PagedList<EmpregadoConvocacao> pagedList= EmpregadoConvocacaoBo.getInstance().getListLoadAll(empConFilter);
		EmpregadoConvocacao empregadoConvocacao = null;
		
		if(pagedList.getTotal() > 0 && pagedList.getList().get(0).getDataConvocacao() != null)
			empregadoConvocacao = pagedList.getList().get(0);
		
		aso.setEmpregado(EmpregadoBo.getInstance().getById(
				aso.getEmpregado().getId()));
		
		
		if(aso.getItemAuditoriaAsos() == null)
			aso.setItemAuditoriaAsos(new ArrayList<ItemAuditoriaAso>());
		
		if(empregadoConvocacao != null) {
			for(EmpregadoConvocacaoExame e : empregadoConvocacao.getEmpregadoConvocacaoExames()) {
				if(e.isOpcional() == false) {
					ItemAuditoriaAso itemAuditoriaAso = new ItemAuditoriaAso();
					itemAuditoriaAso.setDescricao(e.getExame().getCodigo()+" - "+e.getExame().getDescricao());
					itemAuditoriaAso.setAso(aso);
					itemAuditoriaAso.setOrdem(3);
					aso.getItemAuditoriaAsos().add(itemAuditoriaAso);
				}
			}
		}
		
		aso = getRequisitosAso(aso, empregadoConvocacao);
		
		if(aso.getEmpregado().getGrupoMonitoramentos() != null) {
							
			for(GrupoMonitoramento gAt : aso.getEmpregado().getGrupoMonitoramentos()) {
				Aso asoAux = aso;
				
				if(gAt.getTipoGrupoMonitoramento().getNome().equals("ATIVIDADES CRÍTICAS")) {
					
					if(aso.getItemAuditoriaAsos().stream().filter(x->x.getDescricao().equals(gAt.getNome())).count() == 0 && gAt.isAuditoriaAso()){
					    ItemAuditoriaAso itemAuditoriaAso = new ItemAuditoriaAso();
						itemAuditoriaAso.setDescricao(gAt.getNome());
						itemAuditoriaAso.setAso(aso);
						itemAuditoriaAso.setOrdem(4);
						aso.getItemAuditoriaAsos().add(itemAuditoriaAso);
					}					
				}
				
				gAt.getAvaliacoes().forEach(a->{
					if(asoAux.getItemAuditoriaAsos().stream().filter(x->x.getDescricao().equals(a.getNome())).count() == 0 && a.isAuditoriaAso()) {
						 	ItemAuditoriaAso itemAuditoriaAso = new ItemAuditoriaAso();
							itemAuditoriaAso.setDescricao(a.getNome());
							itemAuditoriaAso.setAso(asoAux);
							itemAuditoriaAso.setOrdem(5);
							asoAux.getItemAuditoriaAsos().add(itemAuditoriaAso);
							
					}							
				});
			}
					
		}
		return aso;
	}
	
	private Aso getRequisitosAso(Aso aso,EmpregadoConvocacao eCc) throws Exception {
		
		RequisitoAsoFilter reqFilter = new RequisitoAsoFilter();
		reqFilter.setPageNumber(1);
		reqFilter.setPageSize(Integer.MAX_VALUE);
		List<RequisitoAso> requisitos = RequisitoAsoBo.getInstance().getList(reqFilter).getList();
		
		requisitos.forEach(r -> {		
			ItemAuditoriaAso itemAuditoriaAso = new ItemAuditoriaAso();
			itemAuditoriaAso.setOrdem(1);
			if(r.getConteudo().contains("[NOME]")) {
				itemAuditoriaAso.setDescricao(r.getConteudo().replace("[NOME]", ": "+aso.getEmpregado().getPessoa().getNome()));
			}else if(r.getConteudo().contains("[CARGO]")) {
				itemAuditoriaAso.setDescricao(r.getConteudo().replace("[CARGO]", ": "+aso.getEmpregado().getCargo().getNome()));
			}else if(r.getConteudo().contains("[GERENCIA]")) {
				if(aso.getEmpregado().getGerencia() != null)
					itemAuditoriaAso.setDescricao(r.getConteudo().replace("[GERENCIA]", 
							": "+aso.getEmpregado().getGerencia().getCodigoCompleto()));
			}else if(r.getConteudo().contains("[TIPO_CONVOCACAO]")) {
				if(eCc != null)
					itemAuditoriaAso.setDescricao(r.getConteudo().replace("[TIPO_CONVOCACAO]", 
							": "+eCc.getConvocacao().getTipo()));
			}else if(r.getConteudo().contains("[RISCOS_GHE]")) {
				if(aso.getEmpregado().getGhe() != null) { 	
					try {
						Ghe ghe = GheBo.getInstance().getById(aso.getEmpregado().getGhe().getId());
						itemAuditoriaAso.setDescricao(r.getConteudo().replace("[RISCOS_GHE]", 
								": "+ghe.getNome()));					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}else {
				itemAuditoriaAso.setDescricao(r.getConteudo());		
				itemAuditoriaAso.setOrdem(2);
			}
			if(itemAuditoriaAso.getDescricao() != null && aso.getItemAuditoriaAsos().stream().filter(x->x.getDescricao().equals(itemAuditoriaAso.getDescricao())).count() == 0) {
				itemAuditoriaAso.setAso(aso);
				aso.getItemAuditoriaAsos().add(itemAuditoriaAso);
			}
		});
		return aso;
	}
	
	@Override
	public Aso save(Aso aso) throws Exception {
		
		if(aso.getUsuario() == null || aso.getUsuario().getId() == 0)
			throw new Exception("É necessário informar o Usuário.");
		
		switch(aso.getStatus()) {
			case StatusAso.PENDENTE_AUDITORIA:
				if(aso.isConforme())
					aso.setStatus(StatusAso.PENDENTE_ARQUIVAMENTO);
				else
					aso.setStatus(StatusAso.PENDENTE_CORRECAO);					
				break;
			
			case StatusAso.PENDENTE_CORRECAO:
				if(aso.isConforme())
					aso.setStatus(StatusAso.PENDENTE_ARQUIVAMENTO);
				break;
			
			case StatusAso.PENDENTE_ARQUIVAMENTO:
				if(aso.isConforme())
					aso.setStatus(StatusAso.ARQUIVADO);
				else
					aso.setStatus(StatusAso.PENDENTE_CORRECAO);
				break;
				
			case StatusAso.ARQUIVADO:
				if(!aso.isConforme())
					aso.setStatus(StatusAso.PENDENTE_ARQUIVAMENTO);
				break;
		}		
		
		AsoAlteracao asoAlteracao = new AsoAlteracao();
		asoAlteracao.setData(Helper.getNow());
		asoAlteracao.setUsuario(aso.getUsuario());
		asoAlteracao.setStatus(aso.getStatus());
		
		if(aso.getAsoAlteracoes() == null)
			aso.setAsoAlteracoes(new ArrayList<AsoAlteracao>());
		
		aso.getAsoAlteracoes().add(asoAlteracao);
		aso.getAsoAlteracoes().forEach(aA->aA.setAso(aso));		
		
		aso.getAptidoes().forEach(a->a.setAso(aso));
		aso.getAsoAvaliacoes().forEach(av->av.setAso(aso));
		aso.getItemAuditoriaAsos().forEach(i->i.setAso(aso));
		return super.save(aso);
	}
}
