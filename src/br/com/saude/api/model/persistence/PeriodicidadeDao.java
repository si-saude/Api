package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Periodicidade;

public class PeriodicidadeDao extends GenericDao<Periodicidade> {

	private static PeriodicidadeDao instance;
	
	private PeriodicidadeDao() {
		super();
	}
	
	public static PeriodicidadeDao getInstance() {
		if(instance == null)
			instance = new PeriodicidadeDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
