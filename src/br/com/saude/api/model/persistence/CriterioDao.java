package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Criterio;

public class CriterioDao extends GenericDao<Criterio> {

	private static CriterioDao instance;
	
	private CriterioDao() {
		super();
	}
	
	public static CriterioDao getInstance() {
		if(instance == null)
			instance = new CriterioDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
