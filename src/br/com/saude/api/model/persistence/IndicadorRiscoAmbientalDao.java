package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;

public class IndicadorRiscoAmbientalDao extends GenericDao<IndicadorRiscoAmbiental> {

	private static IndicadorRiscoAmbientalDao instance;
	
	private IndicadorRiscoAmbientalDao() {
		super();
	}
	
	public static IndicadorRiscoAmbientalDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoAmbientalDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {

	}
}
