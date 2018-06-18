package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoPotencialBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoPotencialExampleBuilder;
import br.com.saude.api.model.entity.dto.RiscoPotencialDto;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Notificacao;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Acompanhamento;
import br.com.saude.api.model.persistence.RiscoPotencialDao;
import br.com.saude.api.model.persistence.report.RiscoPotencialReport;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusAcao;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;
import br.com.saude.api.util.constant.StatusTarefa;

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
	
	public List<RiscoPotencialDto> getRiscoPotenciais(String uf) throws IOException{
		return RiscoPotencialReport.getInstance().getRiscoPotenciais(uf);
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
			throw new Exception("Não é possível prosseguir, pois não há Riscos Empregado.");
		
		RiscoPotencial rp = getById(riscoPotencial.getId());
		
		//DEFINIR EQUIPE RESPONSÁVEL
		riscoAux.setRiscosInterdiciplinares(null);
		riscoAux.setEquipeResponsavel(rp.getRiscosInterdiciplinares().get(0).getEquipe());
		
		//ALTERAR O STATUS DAS AÇÕES
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
		
		RiscoPotencial risco = save(riscoPotencial);
		
		if(riscoPotencial.getAcoesDelete() != null) {
			riscoPotencial.getAcoesDelete().forEach(a -> {
				try {
					AcaoBo.getInstance().delete(a.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		return risco;
	}
	
	public RiscoPotencial saveAcompanhamentos(RiscoPotencial riscoPotencial) throws Exception {
		Map<Integer, Boolean> flagAcoes = new HashMap<Integer, Boolean>();
		
		if(riscoPotencial.getRiscoEmpregados() != null) {
			riscoPotencial.getRiscoEmpregados().forEach(r -> {
				if(r.getTriagens() != null)
					r.getTriagens().forEach(t -> {
						if(t.getAcoes() != null)
							t.getAcoes().forEach(a->{
								if ( a.getAcompanhamentos() != null ) {
									a.getAcompanhamentos().forEach(ac -> {
										Acompanhamento acomp = null;
										
										if ( ac.getId() > 0 )
											try {
												acomp = AcompanhamentoBo.getInstance().getById(ac.getId());
											} catch (Exception e1) {
												e1.printStackTrace();
											}
										
										if ( ( ac.getId() == 0 ) || !ac.getDescricao().equals(acomp.getDescricao()) ) {
											if ( flagAcoes.containsKey(a.getId()) && flagAcoes.get(a.getId()) ) return;
											
											flagAcoes.put(a.getId(), true);
											
											Notificacao notificacao = new Notificacao();
											if ( a.getDetalhe().length() < 151 )
												notificacao.setDescricao("Houve mudança nos acompanhamentos da seguinte ação: " 
														+ a.getDetalhe());
											else notificacao.setDescricao("Houve mudança nos acompanhamentos da seguinte ação: " 
													+ a.getDetalhe().charAt(147) + "...");
											
											if ( riscoPotencial.getProfissional().getEquipe().getId() == 
													riscoPotencial.getEquipeResponsavel().getId() )
												notificacao.setEquipe( r.getEquipe() );
											else notificacao.setEquipe( riscoPotencial.getEquipeResponsavel() );
											
											try {
												NotificacaoBo.getInstance().save(notificacao);
											} catch (Exception e) {
												e.printStackTrace();
											}
										} 
									});
								}
							});
					});
			});			
		}
		
		return save(riscoPotencial);
	}
}
