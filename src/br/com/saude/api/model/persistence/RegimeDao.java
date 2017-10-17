package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Regime;

public class RegimeDao extends GenericDao<Regime> {

	private static RegimeDao instance;
	
	private RegimeDao() {
		super();
	}
	
	public static RegimeDao getInstance() {
		if(instance==null)
			instance = new RegimeDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
