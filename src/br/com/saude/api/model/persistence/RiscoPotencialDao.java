package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.entity.po.IndicadorAssociadoSast;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.entity.po.Acao;
import br.com.saude.api.model.entity.po.Acompanhamento;
import br.com.saude.api.model.entity.po.Atendimento;

public class RiscoPotencialDao extends GenericDao<RiscoPotencial> {

	private static RiscoPotencialDao instance;
	private Function<RiscoPotencial, RiscoPotencial> functionLoadAcoes;
	
	
	private RiscoPotencialDao() {
		super();
	}
	
	public static RiscoPotencialDao getInstance() {
		if(instance==null)
			instance = new RiscoPotencialDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = risco -> {
			
			if(risco.getRiscoEmpregados() != null) {
				List<RiscoEmpregado> riscos = new ArrayList<RiscoEmpregado>();
				
				risco.getRiscoEmpregados().forEach(r->{
					
					r = (RiscoEmpregado) Hibernate.unproxy(r);
					
					if(r.getTriagens() != null) {
						List<Triagem> t = new ArrayList<Triagem>();
						r.getTriagens().forEach(tr -> {
							tr = (Triagem) Hibernate.unproxy(tr);
							
							if(tr.getIndicadorSast() != null) {
								tr.setIndicadorSast((IndicadorSast) Hibernate.unproxy(tr.getIndicadorSast()));
								
								if(tr.getIndicadorSast().getIndicadorAssociadoSasts() != null) {
									List<IndicadorAssociadoSast> iS = new ArrayList<IndicadorAssociadoSast>();
									
									tr.getIndicadorSast().getIndicadorAssociadoSasts().forEach(iAS -> {
										iS.add((IndicadorAssociadoSast) Hibernate.unproxy(iAS));
									});
									
									tr.getIndicadorSast().setIndicadorAssociadoSasts(iS);
								}
							}
							
							if ( tr.getAcoes() != null ) {
								List<Acao> acs = new ArrayList<Acao>();
								tr.getAcoes().forEach(a -> {
									acs.add((Acao) Hibernate.unproxy(a));
									if ( a.getAcompanhamentos() != null ) {
										List<Acompanhamento> acomps = new ArrayList<Acompanhamento>();
										a.getAcompanhamentos().forEach(acomp -> acomps.add((Acompanhamento) Hibernate.unproxy(acomp)));
									}
										
								});
							}
							
							t.add(tr);
						});
						r.setTriagens(t);
					}
					
					riscos.add(r);
				});
				
				risco.setRiscoEmpregados(riscos);
			}
			
			if(risco.getEquipes() != null) {
				List<Equipe> equipes = new ArrayList<Equipe>();
				
				risco.getEquipes().forEach(e->{
					equipes.add((Equipe) Hibernate.unproxy(e));
				});
				
				risco.setEquipes(equipes);
			}
			
			return risco;
		};
		
		this.functionLoadAcoes = risco -> {
			
			if(risco.getRiscoEmpregados() != null) {
				List<RiscoEmpregado> riscos = new ArrayList<RiscoEmpregado>();
				
				risco.getRiscoEmpregados().forEach(r->{
					
					r = (RiscoEmpregado) Hibernate.unproxy(r);
					
					if(r.getTriagens() != null) {
						List<Triagem> t = new ArrayList<Triagem>();
						r.getTriagens().forEach(tr -> {
							tr = (Triagem) Hibernate.unproxy(tr);
							
							if(tr.getIndicadorSast() != null) {
								tr.setIndicadorSast((IndicadorSast) Hibernate.unproxy(tr.getIndicadorSast()));
								
								if(tr.getIndicadorSast().getEquipe() != null)
									tr.getIndicadorSast().setEquipe(
											(Equipe) Hibernate.unproxy(tr.getIndicadorSast().getEquipe()));
								
								if(tr.getIndicadorSast().getIndicadorAssociadoSasts() != null) {
									List<IndicadorAssociadoSast> iS = new ArrayList<IndicadorAssociadoSast>();
									
									tr.getIndicadorSast().getIndicadorAssociadoSasts().forEach(iAS -> {
										iS.add((IndicadorAssociadoSast) Hibernate.unproxy(iAS));
									});
									
									tr.getIndicadorSast().setIndicadorAssociadoSasts(iS);
								}
							}
							
							if ( tr.getAcoes() != null ) {
								List<Acao> acs = new ArrayList<Acao>();
								tr.getAcoes().forEach(a -> {
									acs.add((Acao) Hibernate.unproxy(a));
									if ( a.getAcompanhamentos() != null ) {
										List<Acompanhamento> acomps = new ArrayList<Acompanhamento>();
										a.getAcompanhamentos().forEach(acomp -> acomps.add((Acompanhamento) Hibernate.unproxy(acomp)));
									}
										
								});
							}	
							if(tr.getAtendimento() != null)
								tr.setAtendimento((Atendimento) Hibernate.unproxy(tr.getAtendimento()));
							
							t.add(tr);
						});
						r.setTriagens(t);
					}
					
					riscos.add(r);
				});
				
				risco.setRiscoEmpregados(riscos);
			}
			
			if(risco.getEquipes() != null) {
				List<Equipe> equipes = new ArrayList<Equipe>();
				
				risco.getEquipes().forEach(e->{
					equipes.add((Equipe) Hibernate.unproxy(e));
				});
				
				risco.setEquipes(equipes);
			}
			
			return risco;
		};
	}
	
	@Override
	public RiscoPotencial getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public RiscoPotencial getByIdLoadAcoes(Object id) throws Exception {
		return super.getById(id,this.functionLoadAcoes);
	}
	
	public PagedList<RiscoPotencial> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
