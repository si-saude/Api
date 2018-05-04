package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Intervencao;

public class IntervencaoDao extends GenericDao<Intervencao> {
	private static IntervencaoDao instance;
	
	private IntervencaoDao() {
		super();
	}
	
	public static IntervencaoDao getInstance() {
		if(instance==null)
			instance = new IntervencaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	}

}