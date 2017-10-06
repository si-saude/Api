package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;

public class IndicadorRiscoErgonomicoExampleBuilder 
				extends IndicadorRiscoExampleBuilder<IndicadorRiscoErgonomico> {

	public static IndicadorRiscoErgonomicoExampleBuilder newInstance(IndicadorRiscoFilter filter) {
		return new IndicadorRiscoErgonomicoExampleBuilder(filter);
	}

	private IndicadorRiscoErgonomicoExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
}
