package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;

public class IndicadorRiscoAmbientalExampleBuilder 
				extends IndicadorRiscoExampleBuilder<IndicadorRiscoAmbiental> {

	public static IndicadorRiscoAmbientalExampleBuilder newInstance(IndicadorRiscoFilter filter) {
		return new IndicadorRiscoAmbientalExampleBuilder(filter);
	}
	
	private IndicadorRiscoAmbientalExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
}
