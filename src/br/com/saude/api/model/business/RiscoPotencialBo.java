package br.com.saude.api.model.business;

import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoPotencialBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoPotencialExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.persistence.RiscoPotencialDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusRiscoPotencial;
import br.com.saude.api.util.constant.StatusTarefa;

public class RiscoPotencialBo extends GenericBo<RiscoPotencial, RiscoPotencialFilter, 
	RiscoPotencialDao, RiscoPotencialBuilder, RiscoPotencialExampleBuilder> {

	private Function<RiscoPotencialBuilder,RiscoPotencialBuilder> functionLoadAcoes;

	private static RiscoPotencialBo instance;
	
	private RiscoPotencialBo() {
		super();
	}
	
	public static RiscoPotencialBo getInstance() {
		if(instance == null)
			instance = new RiscoPotencialBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadRiscoEmpregados().loadEquipes();
		};
		
		this.functionLoadAcoes = builder -> {
			return builder.loadRiscoEmpregados().loadEquipes().loadAcoes();
		};
	}
	
	@Override
	public RiscoPotencial getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public RiscoPotencial getByIdLoadAcoes(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAcoes(id), this.functionLoadAcoes);
	}
	
	public PagedList<RiscoPotencial> getListLoadAll(RiscoPotencialFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), this.functionLoadAcoes);
	}

	@Override
	public RiscoPotencial save(RiscoPotencial riscoPotencial) throws Exception {
		
		if ( riscoPotencial.getRiscoEmpregados() != null && riscoPotencial.getRiscoEmpregados().size() > 0 ) {
			riscoPotencial.getRiscoEmpregados().forEach(rE -> { 
				rE.setRiscoPotencial(riscoPotencial);
				
				if ( rE.getTriagens() != null && rE.getTriagens().size() > 0 ) {
					rE.getTriagens().forEach(t -> { 
						t.setRiscoEmpregado(rE);
						if ( t.getAcoes() != null && t.getAcoes().size() > 0 ) {
							t.getAcoes().forEach(a -> { 
								a.setTriagem(t);
								if ( a.getAcompanhamentos() != null && a.getAcompanhamentos().size() > 0 ) {
									a.getAcompanhamentos().forEach( ac -> ac.setAcao(a));
								}
							});
						}
					});
				}

			});
			
		}
		
		return super.save(riscoPotencial);
	}
	
	public RiscoPotencial validar(RiscoPotencial riscoPotencial) throws Exception {
		riscoPotencial.setStatus(StatusRiscoPotencial.getInstance().VALIDADO);
		return save(riscoPotencial);
	}
	
	public RiscoPotencial saveAcoes(RiscoPotencial riscoPotencial) throws Exception {
		
		if(riscoPotencial.getRiscoEmpregados() != null) {
			
			ServicoFilter filter = new ServicoFilter();
			filter.setPageNumber(1);
			filter.setPageSize(1);
			filter.setCodigo("0001");
			filter.setGrupo(GrupoServico.ATENDIMENTO_PROGRAMA_SAUDE);
			
			Servico servico =  ServicoBo.getInstance().getList(filter).getList().get(0);
			
			riscoPotencial.getRiscoEmpregados().forEach(r -> {
				if(r.getTriagens() != null)
					r.getTriagens().forEach(t -> {
						if(t.getAcoes() != null)
							t.getAcoes().forEach(a->{
								if(a.getTarefa().getId() == 0) {
									a.getTarefa().setInicio(Helper.getNow());
									a.getTarefa().setAtualizacao(a.getTarefa().getInicio());
									a.getTarefa().setCliente(riscoPotencial.getEmpregado());
									a.getTarefa().setEquipe(t.getEquipeAbordagem());
									a.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
									a.getTarefa().setServico(servico);
								}
							});
					});
			});			
		}
		
		return save(riscoPotencial);
	}
}
