package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Base;

public class BaseDao extends GenericDao<Base> {

	private static BaseDao instance;
	
	private BaseDao() {
		super();
	}
	
	public static BaseDao getInstance() {
		if(instance==null)
			instance = new BaseDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
