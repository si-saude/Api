package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.entity.po.RegraAtendimento;

public class RegraAtendimentoDao extends GenericDao<RegraAtendimento> {

	private static RegraAtendimentoDao instance;
	
	private RegraAtendimentoDao() {
		super();
	}
	
	public static RegraAtendimentoDao getInstance() {
		if(instance == null)
			instance = new RegraAtendimentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = regra -> {
			
			if(regra.getRegraAtendimentoEquipes() != null) {
				Hibernate.initialize(regra.getRegraAtendimentoEquipes());
				
				regra.getRegraAtendimentoEquipes().forEach(r -> {
					if(r.getRegraAtendimentoEquipeRequisitos() != null)
						Hibernate.initialize(r.getRegraAtendimentoEquipeRequisitos());
				});
			}
			
			return regra;
		};
		
		this.functionBeforeSave = pair -> {
			RegraAtendimento regra = pair.getValue0();
			Session session = pair.getValue1();
			
			if(regra.getRegraAtendimentoEquipes() != null) {
				regra.getRegraAtendimentoEquipes().forEach(e->{
					e.setEquipe(session.get(Equipe.class, e.getEquipe().getId()));
					
					if(e.getRegraAtendimentoEquipeRequisitos() != null) {
						e.getRegraAtendimentoEquipeRequisitos().forEach(r->{
							r.setEquipe(session.get(Equipe.class, r.getEquipe().getId()));
						});
					}
				});
			}
			
			return regra;
		};
	}
	
	public RegraAtendimento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public PagedList<RegraAtendimento> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
}
