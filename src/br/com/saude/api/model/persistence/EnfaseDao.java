package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Enfase;

public class EnfaseDao extends GenericDao<Enfase> {

	private static EnfaseDao instance;
	
	private EnfaseDao() {
		super();
	}
	
	public static EnfaseDao getInstance() {
		if(instance==null)
			instance = new EnfaseDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
