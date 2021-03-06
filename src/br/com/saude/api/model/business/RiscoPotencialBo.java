package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoPotencialBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoPotencialExampleBuilder;
import br.com.saude.api.model.entity.dto.RiscoPotencialDto;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.persistence.RiscoPotencialDao;
import br.com.saude.api.model.persistence.report.RiscoPotencialReport;
import br.com.saude.api.util.constant.StatusAcao;
import br.com.saude.api.util.constant.StatusRPSat;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;

public class RiscoPotencialBo extends GenericBo<RiscoPotencial, RiscoPotencialFilter, 
	RiscoPotencialDao, RiscoPotencialBuilder, RiscoPotencialExampleBuilder>
	implements GenericReportBo<RiscoPotencialDto>{

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
		RiscoPotencial r = getByEntity(getDao().getByIdLoadAcoes(id), this.functionLoadAcoes); 
		return r;
	}
	
	public PagedList<RiscoPotencial> getListLoadAll(RiscoPotencialFilter filter) throws Exception {
		PagedList<RiscoPotencial> riscos = super.getList(getDao().
				getListLoadAll(getExampleBuilder(filter).example()), this.functionLoadAcoes);
		if ( riscos.getTotal() > 0 )
			riscos.getList().sort(new Comparator<RiscoPotencial>() {
				@Override
				public int compare(RiscoPotencial o1, RiscoPotencial o2) {
					try {
						return new Double(o2.getValor()).compareTo(new Double(o1.getValor()));
					} catch (Exception e) {
						e.printStackTrace();
						return 0;
					}
				}
			});
		
		return riscos;
	}
	
	public List<RiscoPotencialDto> getRiscoPotenciais(String uf, Profissional profissional) throws IOException{
		return RiscoPotencialReport.getInstance().getRiscoPotenciais(uf, profissional);
	}
	
	public RiscoPotencial criarPlano(RiscoPotencial riscoPotencial) throws Exception {
		
		RiscoPotencial riscoAux = getByIdLoadAcoes(riscoPotencial.getId());
		
		riscoAux.setStatus(StatusRiscoPotencial.getInstance().PLANEJAMENTO);
		
		if(riscoPotencial.getRiscoEmpregados() != null && riscoAux.getRiscoEmpregados() != null)
			riscoAux.getRiscoEmpregados().forEach(r->{
				r.setStatus(StatusRiscoEmpregado.getInstance().VALIDADO);
				r.setAtivo(riscoPotencial.getRiscoEmpregados().stream().filter(rE->rE.getId() == r.getId())
					.findFirst().get().isAtivo());
			});
		else
			throw new Exception("N�o � poss�vel prosseguir, pois n�o h� Riscos Empregado.");
		
		RiscoPotencial rp = getById(riscoPotencial.getId());
		
		//DEFINIR EQUIPE RESPONS�VEL
		riscoAux.setRiscosInterdiciplinares(null);
		riscoAux.setEquipeResponsavel(rp.getRiscosInterdiciplinares().get(0).getEquipe());
		
		//ALTERAR O STATUS DAS A��ES
		riscoAux.getRiscoEmpregados().forEach(r->{
			if(r.isAtivo() && r.getTriagens() != null) {
				r.getTriagens().forEach(t-> {
					if(t.getAcoes() != null) {
						t.getAcoes().forEach(a->{
							if(a.getStatus().equals(StatusAcao.getInstance().REAVALIADA))
								a.setStatus(StatusAcao.getInstance().VALIDADA);
						});
					}
				});
			}
		});
		
		return simpleSave(riscoAux);
	}
	
	public RiscoPotencial simpleSave(RiscoPotencial riscoPotencial) throws Exception {
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

	@Override
	public RiscoPotencial save(RiscoPotencial riscoPotencial) throws Exception {
		
		if ( riscoPotencial.getRiscoEmpregados() != null && riscoPotencial.getRiscoEmpregados().size() > 0 ) {
			
			riscoPotencial.getRiscoEmpregados().forEach(rE -> {
				if ( rE.getId() == 0 ) {
					riscoPotencial.getRiscoEmpregados().stream().
						filter(r -> {
							if ( r.getEquipe().getId() == rE.getEquipe().getId() &&
									r.isAtivo() )
								return true;
							return false;
							}).findFirst().get().setAtivo(false);
					rE.setAtivo(true);
				}
				
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
	
	public RiscoPotencial saveReavaliacao(RiscoPotencial riscoPotencial) throws Exception {
		
		if ( riscoPotencial.getRiscoEmpregados() != null && riscoPotencial.getRiscoEmpregados().size() > 0 ) {
			riscoPotencial.getRiscoEmpregados().forEach(rE -> {
				if ( rE.getId() == 0 ) {
					riscoPotencial.getRiscoEmpregados().stream().
						filter(r -> {
							if ( r.getEquipe().getId() == rE.getEquipe().getId() &&
									r.isAtivo() )
								return false;
							return true;
							}).findFirst().get().setAtivo(false);
					rE.setAtivo(true);
				}
				
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
		
		return riscoPotencial;
	}
	
	public RiscoPotencial validar(RiscoPotencial riscoPotencial) throws Exception {
		riscoPotencial.setStatus(StatusRiscoPotencial.getInstance().VALIDADO);
		return save(riscoPotencial);
	}
	
	public RiscoPotencial saveAcoes(RiscoPotencial riscoPotencial) throws Exception {
		
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
		super.save(riscoPotencial);
		if(riscoPotencial.getAcoesDelete() != null) {
			riscoPotencial.getAcoesDelete().forEach(a -> {
				try {
					AcaoBo.getInstance().delete(a.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		return riscoPotencial;
	}
	
	public RiscoPotencial saveAcompanhamentos(RiscoPotencial riscoPotencial) throws Exception {
		return save(riscoPotencial);
	}
	
	public String getStatusRPSat(double valor) {
		String statusRPSat = "";
		
		if ( valor > 0 && valor < 0.57 ) {
			statusRPSat = StatusRPSat.getInstance().ACEITAVEL;
		} else if ( valor >= 0.57 && valor < 0.725 ) {
			statusRPSat = StatusRPSat.getInstance().TOLERAVEL;
		} else if ( valor >= 0.725 ) {
			statusRPSat = StatusRPSat.getInstance().INACEITAVEL;
		}
		
		return statusRPSat;
	}
}
