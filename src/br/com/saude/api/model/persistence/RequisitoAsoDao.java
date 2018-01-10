package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.RequisitoAso;

public class RequisitoAsoDao extends GenericDao<RequisitoAso> {

	private static RequisitoAsoDao instance;
	
	private RequisitoAsoDao() {
		super();
	}
	
	public static RequisitoAsoDao getInstance() {
		if(instance==null)
			instance = new RequisitoAsoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
