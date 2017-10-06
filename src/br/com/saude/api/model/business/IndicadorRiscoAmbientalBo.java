package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoAmbientalBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorRiscoAmbientalExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;
import br.com.saude.api.model.persistence.IndicadorRiscoAmbientalDao;

public class IndicadorRiscoAmbientalBo extends GenericBo<IndicadorRiscoAmbiental, 
					IndicadorRiscoFilter, IndicadorRiscoAmbientalDao, 
					IndicadorRiscoAmbientalBuilder, IndicadorRiscoAmbientalExampleBuilder> {

	private static IndicadorRiscoAmbientalBo instance;
	
	private IndicadorRiscoAmbientalBo() {
		super();
	}
	
	
	public static IndicadorRiscoAmbientalBo getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoAmbientalBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

}
