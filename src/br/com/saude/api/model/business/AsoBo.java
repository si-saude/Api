package br.com.saude.api.model.business;

import java.util.ArrayList;
import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.AsoBuilder;
import br.com.saude.api.model.creation.builder.example.AsoExampleBuilder;
import br.com.saude.api.model.entity.filter.AsoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.AsoAlteracao;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.RequisitoAso;
import br.com.saude.api.model.persistence.AsoDao;
import br.com.saude.api.util.constant.StatusAso;

public class AsoBo 
	extends GenericBo<Aso, AsoFilter, AsoDao, AsoBuilder, AsoExampleBuilder> {
	
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
			return this.functionLoad.apply(builder).loadAlteracoes();
		};
	}

	public Aso getUltimoByEmpregado(Aso aso) throws Exception{
		return getDao().getUltimoByEmpregado(aso);
	}
	
	@Override
	public Aso getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<Aso> getList(AsoFilter filter) throws Exception {
		return super.getList(filter, this.functionLoad);
	}
	
	public PagedList<Aso> getListLoadAll(AsoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}
	
	public List<RequisitoAso> getExamesConvocacao(Aso aso) throws Exception {
		Atendimento atendimento = AtendimentoBo.getInstance().getById(aso.getAtendimento().getId());
		EmpregadoConvocacaoFilter empConFilter = 
				AtendimentoBo.getInstance().configureEmpregadoConvocacaoFilter(atendimento);
		
		//ALTERAR PARA LISTALL
		PagedList<EmpregadoConvocacao> pagedList = 
				EmpregadoConvocacaoBo.getInstance().getListLoadAll(empConFilter);
		List<RequisitoAso> requisitos = new ArrayList<RequisitoAso>();
		if(pagedList.getTotal() > 0) {
			EmpregadoConvocacao eC = pagedList.getList().get(0);
			eC.getEmpregadoConvocacaoExames().forEach(e->{
				RequisitoAso requisito = new RequisitoAso();
				requisito.setConteudo(e.getExame().getCodigo()+" - "+e.getExame().getDescricao());
				requisitos.add(requisito);
			});
		}
		
		//GRUPOS DE MONITORAMENTO
		Empregado empregado = EmpregadoBo.getInstance().getById(
				atendimento.getFilaEsperaOcupacional().getEmpregado().getId());
		
		boolean pssicossocial = false;
		boolean usoDeMascara = false;
		
		if(empregado.getGrupoMonitoramentos() != null) {
			for(GrupoMonitoramento g : empregado.getGrupoMonitoramentos()) {
				RequisitoAso requisito = null;
				
				//VERIFICAR SE É NR
				if(g.getNome().contains("(NR")) {
					requisito = new RequisitoAso();
					requisito.setConteudo(g.getNome());
					requisitos.add(requisito);
				}
				
				//VERIFICAR SE ENTRA AVALIAÇÃO BIOPSSICOSSOCIAL
				if(g.isAuditoriaAso() && (!pssicossocial)) {
					requisito = new RequisitoAso();
					requisito.setConteudo("Avaliação Biopsicossocial");
					requisitos.add(requisito);
					pssicossocial = true;
				}
				
				//VERIFICAR SE ENTRA AVALIAÇÃO P/ USO DE MÁSCARA
				if(g.isRelatorio() && (!usoDeMascara)) {
					requisito = new RequisitoAso();
					requisito.setConteudo("Avaliação para uso de máscara");
					requisitos.add(requisito);
					usoDeMascara = true;
				}
			}
		}

		return requisitos;
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
		
		return super.save(aso);
	}
}
