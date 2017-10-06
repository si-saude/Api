package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoErgonomicoBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorRiscoErgonomicoExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;
import br.com.saude.api.model.persistence.IndicadorRiscoErgonomicoDao;

public class IndicadorRiscoErgonomicoBo extends GenericBo<IndicadorRiscoErgonomico, 
						IndicadorRiscoFilter, IndicadorRiscoErgonomicoDao, 
						IndicadorRiscoErgonomicoBuilder, IndicadorRiscoErgonomicoExampleBuilder> {

	private static IndicadorRiscoErgonomicoBo instance;

	private IndicadorRiscoErgonomicoBo() {
		super();
	}


	public static IndicadorRiscoErgonomicoBo getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoErgonomicoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	
	}

}
