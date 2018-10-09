package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.entity.po.Acao;
import br.com.saude.api.model.entity.po.Acompanhamento;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Diagnostico;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.entity.po.IndicadorAssociadoSast;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.entity.po.Intervencao;

public class RiscoEmpregadoDao extends GenericDao<RiscoEmpregado> {

	private static RiscoEmpregadoDao instance;
	private Function<RiscoEmpregado,RiscoEmpregado> functionLoadAcoes;
	
	private RiscoEmpregadoDao() {
		super();
	}
	
	public static RiscoEmpregadoDao getInstance() {
		if(instance==null)
			instance = new RiscoEmpregadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = riscoEmpregado -> {
			riscoEmpregado = loadRiscoPotencial(riscoEmpregado);
			riscoEmpregado = loadTriagens(riscoEmpregado);
			return riscoEmpregado;
		};
		
		this.functionLoadAll = riscoEmpregado -> {
			riscoEmpregado = loadRiscoPotencial(riscoEmpregado);
			riscoEmpregado = loadTriagensAll(riscoEmpregado);
			return riscoEmpregado;
		};
		
		this.functionLoadAcoes = riscoEmpregado -> {
			riscoEmpregado = loadRiscoPotencial(riscoEmpregado);
			riscoEmpregado = loadAcoes(riscoEmpregado);
			return riscoEmpregado;
		};
	}
	
	private RiscoEmpregado loadRiscoPotencial(RiscoEmpregado riscoEmpregado) {
		if (riscoEmpregado.getRiscoPotencial() != null)
			Hibernate.unproxy(riscoEmpregado.getRiscoPotencial());
		
		return riscoEmpregado;
	}
	
	private RiscoEmpregado loadTriagens(RiscoEmpregado riscoEmpregado) {
		if (riscoEmpregado.getTriagens() != null) {
			List<Triagem> triagens = new ArrayList<Triagem>();
			riscoEmpregado.getTriagens().forEach(t -> {
				if ( t.getIndicadorSast().getIndicadorAssociadoSasts() != null ) {
					List<IndicadorAssociadoSast> iass = new ArrayList<IndicadorAssociadoSast>();
					t.getIndicadorSast().getIndicadorAssociadoSasts().forEach(ias -> {
						iass.add((IndicadorAssociadoSast) Hibernate.unproxy(ias));
					});
					t.getIndicadorSast().setIndicadorAssociadoSasts(iass);
				}
				triagens.add((Triagem) Hibernate.unproxy(t));
			});
			riscoEmpregado.setTriagens(triagens);
		}
		
		return riscoEmpregado;
	}
	
	private RiscoEmpregado loadAcoes(RiscoEmpregado riscoEmpregado) {
				
	riscoEmpregado = (RiscoEmpregado) Hibernate.unproxy(riscoEmpregado);	
	if(riscoEmpregado.getTriagens() != null) {
		List<Triagem> t = new ArrayList<Triagem>();
		riscoEmpregado.getTriagens().forEach(tr -> {
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
			
			if(tr.getAtendimento() != null)
				tr.setAtendimento((Atendimento)Hibernate.unproxy(tr.getAtendimento()));
			
			t.add(tr);
		});
		riscoEmpregado.setTriagens(t);
	}
		
		return riscoEmpregado;
	}
	
	private RiscoEmpregado loadTriagensAll(RiscoEmpregado riscoEmpregado) {
		if (riscoEmpregado.getTriagens() != null) {
			List<Triagem> triagens = new ArrayList<Triagem>();
			riscoEmpregado.getTriagens().forEach(t -> {
				if ( t.getIndicadorSast().getIndicadorAssociadoSasts() != null ) {
					List<IndicadorAssociadoSast> iass = new ArrayList<IndicadorAssociadoSast>();
					t.getIndicadorSast().getIndicadorAssociadoSasts().forEach(ias -> {
						iass.add((IndicadorAssociadoSast) Hibernate.unproxy(ias));
					});
					t.getIndicadorSast().setIndicadorAssociadoSasts(iass);
				}
				
				if(t.getAtendimento() != null)
					t.setAtendimento((Atendimento) Hibernate.unproxy(t.getAtendimento()));
				
				if(t.getDiagnostico() != null)
					t.setDiagnostico((Diagnostico) Hibernate.unproxy(t.getDiagnostico()));
				
				if(t.getEquipeAbordagem() != null)
					t.setEquipeAbordagem((Equipe) Hibernate.unproxy(t.getEquipeAbordagem()));
				
				if(t.getIntervencao() != null)
					t.setIntervencao((Intervencao) Hibernate.unproxy(t.getIntervencao()));
				
				triagens.add((Triagem) Hibernate.unproxy(t));
			});
			riscoEmpregado.setTriagens(triagens);
		}
		
		return riscoEmpregado;
	}
	
	@Override
	public PagedList<RiscoEmpregado> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	public PagedList<RiscoEmpregado> getListAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	@Override
	public RiscoEmpregado getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}
	
	public RiscoEmpregado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public RiscoEmpregado getListAcoes(Object id) throws Exception {
		return super.getById(id, this.functionLoadAcoes);
	}
}
