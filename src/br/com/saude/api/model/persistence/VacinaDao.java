package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Vacina;

public class VacinaDao extends GenericDao<Vacina> {

	private static VacinaDao instance;
	
	private VacinaDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static VacinaDao getInstance() {
		if(instance == null)
			instance = new VacinaDao();
		return instance;
	}	
}
