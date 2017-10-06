package br.com.saude.api.model.persistence;

import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;

public class IndicadorRiscoAmbientalDao extends IndicadorRiscoDao<IndicadorRiscoAmbiental> {

	private static IndicadorRiscoAmbientalDao instance;
	
	private IndicadorRiscoAmbientalDao() {
		super();
	}
	
	public static IndicadorRiscoAmbientalDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoAmbientalDao();
		return instance;
	}
}
