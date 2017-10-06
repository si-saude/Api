package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoSaudeAmbientalBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorRiscoSaudeAmbientalExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;
import br.com.saude.api.model.persistence.IndicadorRiscoSaudeAmbientalDao;

public class IndicadorRiscoSaudeAmbientalBo extends GenericBo<IndicadorRiscoSaudeAmbiental, 
		IndicadorRiscoFilter, IndicadorRiscoSaudeAmbientalDao, 
		IndicadorRiscoSaudeAmbientalBuilder, IndicadorRiscoSaudeAmbientalExampleBuilder> {

	private static IndicadorRiscoSaudeAmbientalBo instance;

	private IndicadorRiscoSaudeAmbientalBo() {
		super();
	}


	public static IndicadorRiscoSaudeAmbientalBo getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoSaudeAmbientalBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	
	}

}