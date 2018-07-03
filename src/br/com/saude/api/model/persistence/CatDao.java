package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Cat;

public class CatDao extends GenericDao<Cat> {

	private static CatDao instance;
	
	private CatDao() {
		super();
	}
	
	public static CatDao getInstance() {
		if(instance==null)
			instance = new CatDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
