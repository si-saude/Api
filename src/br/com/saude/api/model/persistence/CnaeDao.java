package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Cnae;

public class CnaeDao extends GenericDao<Cnae> {
	private static CnaeDao instance;
	
	private CnaeDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = cnae -> {
			cnae = loadEmpresa(cnae);
			
			return cnae;
		};
	}
	
	public static CnaeDao getInstance() {
		if(instance==null)
			instance = new CnaeDao();
		return instance;
	}
	
	private Cnae loadEmpresa(Cnae cnae) {
		if(cnae.getEmpresa()!=null)
			Hibernate.initialize(cnae.getEmpresa());
		return cnae;
	}
	
	public Cnae getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Cnae> getListFunctionLoadAll(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoadAll);
	}
}
