package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;

public class IndicadorRiscoSanitarioBuilder extends IndicadorRiscoBuilder<IndicadorRiscoSanitario> {

	public static IndicadorRiscoSanitarioBuilder 
			newInstance(IndicadorRiscoSanitario indicadorRiscoSanitario) {
		return new IndicadorRiscoSanitarioBuilder(indicadorRiscoSanitario);
	}
		
	public static IndicadorRiscoSanitarioBuilder 
			newInstance(List<IndicadorRiscoSanitario> indicadorRiscoSanitarios) {
		return new IndicadorRiscoSanitarioBuilder(indicadorRiscoSanitarios);
	}
		
	private IndicadorRiscoSanitarioBuilder(List<IndicadorRiscoSanitario> indicadorRiscoSanitarios) {
		super(indicadorRiscoSanitarios);
	}
		
	private IndicadorRiscoSanitarioBuilder(IndicadorRiscoSanitario indicadorRiscoSanitario) {
		super(indicadorRiscoSanitario);
	}
}
