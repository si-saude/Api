package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AprhoItem;

public class AprhoItemDao extends GenericDao<AprhoItem> {

	private static AprhoItemDao instance;
	
	private AprhoItemDao() {
		super();
	}
	
	public static AprhoItemDao getInstance() {
		if(instance == null)
			instance = new AprhoItemDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
}
