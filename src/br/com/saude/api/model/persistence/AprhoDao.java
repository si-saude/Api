package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Aprho;

public class AprhoDao extends GenericDao<Aprho> {

	private static AprhoDao instance;
	
	private AprhoDao() {
		super();
	}
	
	public static AprhoDao getInstance() {
		if(instance==null)
			instance = new AprhoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
