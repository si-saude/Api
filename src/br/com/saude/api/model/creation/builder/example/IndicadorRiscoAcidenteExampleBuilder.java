package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;

public class IndicadorRiscoAcidenteExampleBuilder 
					extends IndicadorRiscoExampleBuilder<IndicadorRiscoAcidente> {

	public static IndicadorRiscoAcidenteExampleBuilder newInstance(IndicadorRiscoFilter filter) {
		return new IndicadorRiscoAcidenteExampleBuilder(filter);
	}

	private IndicadorRiscoAcidenteExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
}
