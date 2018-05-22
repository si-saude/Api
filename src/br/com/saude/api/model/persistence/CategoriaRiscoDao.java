package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.CategoriaRisco;

public class CategoriaRiscoDao extends GenericDao<CategoriaRisco> {

	private static CategoriaRiscoDao instance;
	
	private CategoriaRiscoDao() {
		super();
	}
	
	public static CategoriaRiscoDao getInstance() {
		if(instance==null)
			instance = new CategoriaRiscoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
