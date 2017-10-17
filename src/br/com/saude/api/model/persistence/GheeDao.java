package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Ghee;

public class GheeDao extends GenericDao<Ghee> {

private static GheeDao instance;
	
	private GheeDao() {
		super();
	}
	
	public static GheeDao getInstance() {
		if(instance==null)
			instance = new GheeDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
