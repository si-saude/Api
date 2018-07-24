package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.NaturezaLesao;

public class NaturezaLesaoDao extends GenericDao<NaturezaLesao>{
	private static NaturezaLesaoDao instance;
	
	private NaturezaLesaoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static NaturezaLesaoDao getInstance() {
		if(instance == null)
			instance = new NaturezaLesaoDao();
		return instance;
	}

}
