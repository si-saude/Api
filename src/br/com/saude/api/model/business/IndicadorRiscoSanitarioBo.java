package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoSanitarioBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorRiscoSanitarioExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;
import br.com.saude.api.model.persistence.IndicadorRiscoSanitarioDao;

public class IndicadorRiscoSanitarioBo extends GenericBo<IndicadorRiscoSanitario, 
								IndicadorRiscoFilter, IndicadorRiscoSanitarioDao, 
								IndicadorRiscoSanitarioBuilder, IndicadorRiscoSanitarioExampleBuilder> {

	private static IndicadorRiscoSanitarioBo instance;

	private IndicadorRiscoSanitarioBo() {
		super();
	}


	public static IndicadorRiscoSanitarioBo getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoSanitarioBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	
	}

}