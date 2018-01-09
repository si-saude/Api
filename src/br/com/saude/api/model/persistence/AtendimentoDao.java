package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Atendimento;

public class AtendimentoDao extends GenericDao<Atendimento> {

	private static AtendimentoDao instance;
	
	private AtendimentoDao() {
		super();
	}
	
	public static AtendimentoDao getInstance() {
		if(instance == null)
			instance = new AtendimentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atendimento -> {
			if(atendimento.getTarefa() != null)
				Hibernate.initialize(atendimento.getTarefa());
			return atendimento;
		};
	}
	
	public Atendimento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
