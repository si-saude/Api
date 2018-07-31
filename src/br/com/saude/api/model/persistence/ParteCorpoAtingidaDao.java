package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ParteCorpoAtingida;

public class ParteCorpoAtingidaDao extends GenericDao<ParteCorpoAtingida>{
	private static ParteCorpoAtingidaDao instance;
	
	private ParteCorpoAtingidaDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static ParteCorpoAtingidaDao getInstance() {
		if(instance == null)
			instance = new ParteCorpoAtingidaDao();
		return instance;
	}

}
