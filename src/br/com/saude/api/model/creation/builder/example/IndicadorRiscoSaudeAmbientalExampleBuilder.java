package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;

public class IndicadorRiscoSaudeAmbientalExampleBuilder 
				extends IndicadorRiscoExampleBuilder<IndicadorRiscoSaudeAmbiental> {

	public static IndicadorRiscoSaudeAmbientalExampleBuilder newInstance(IndicadorRiscoFilter filter) {
		return new IndicadorRiscoSaudeAmbientalExampleBuilder(filter);
	}

	private IndicadorRiscoSaudeAmbientalExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
}
