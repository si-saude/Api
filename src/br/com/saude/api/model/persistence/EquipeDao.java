package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeDao extends GenericDao<Equipe> {
	
	private static EquipeDao instance;
	
	private EquipeDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static EquipeDao getInstance() {
		if(instance==null)
			instance = new EquipeDao();
		return instance;
	}
}
