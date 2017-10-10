package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Exame;

public class ExameDao extends GenericDao<Exame> {
	private static ExameDao instance;
	
	private ExameDao(){
		super();
	}
	
	@Override
	protected void initializeFunctions() {

	}
	
	public static ExameDao getInstance() {
		if(instance == null)
			instance = new ExameDao();
		return instance;
	}
}
