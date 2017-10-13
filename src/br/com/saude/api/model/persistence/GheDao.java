package br.com.saude.api.model.persistence;

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
		
	}
}
