package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Ghe;

public class GheDao extends GenericDao<Ghe> {

	private static GheDao instance;
	
	private GheDao() {
		super();
	}
	
	public static GheDao getInstance() {
		if(instance==null)
			instance = new GheDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = ghe -> {
			
			if(ghe.getRisco() != null)
				Hibernate.initialize(ghe.getRisco());
			
			return ghe;
		};
	}
	
	@Override
	public Ghe getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
