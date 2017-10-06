package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;

public class IndicadorRiscoSanitarioExampleBuilder 
					extends IndicadorRiscoExampleBuilder<IndicadorRiscoSanitario> {

	public static IndicadorRiscoSanitarioExampleBuilder newInstance(IndicadorRiscoFilter filter) {
		return new IndicadorRiscoSanitarioExampleBuilder(filter);
	}

	private IndicadorRiscoSanitarioExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
}