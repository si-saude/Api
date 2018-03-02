package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.RiscoGhe;

public class RiscoGheDao extends GenericDao<RiscoGhe> {

	private static RiscoGheDao instance;
	
	private RiscoGheDao() {
		super();
	}
	
	public static RiscoGheDao getInstance() {
		if(instance == null)
			instance = new RiscoGheDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
