package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AgenteRisco;

public class AgenteRiscoDao extends GenericDao<AgenteRisco> {

	private static AgenteRiscoDao instance;
	
	private AgenteRiscoDao() {
		super();
	}
	
	public static AgenteRiscoDao getInstance() {
		if(instance==null)
			instance = new AgenteRiscoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
