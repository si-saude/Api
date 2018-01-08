package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Exame;

public class ExameDao extends GenericDao<Exame> {
	private static ExameDao instance;
	
	private ExameDao(){
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = exame -> {
			exame = loadCampoExames(exame);
			return exame;
		};

	}
	
	public static ExameDao getInstance() {
		if(instance == null)
			instance = new ExameDao();
		return instance;
	}
	
	public Exame getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	private Exame loadCampoExames(Exame exame) {
		if(exame.getCampoExames() != null) {
			Hibernate.initialize(exame.getCampoExames());
		}
		
		return exame;
	}
	
	public PagedList<Exame> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
	
	public PagedList<Exame> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
}
