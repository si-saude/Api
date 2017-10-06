package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;

public class IndicadorRiscoSaudeAmbientalBuilder 
				extends IndicadorRiscoBuilder<IndicadorRiscoSaudeAmbiental> {

	public static IndicadorRiscoSaudeAmbientalBuilder 
			newInstance(IndicadorRiscoSaudeAmbiental indicadorRiscoSaudeAmbiental) {
		return new IndicadorRiscoSaudeAmbientalBuilder(indicadorRiscoSaudeAmbiental);
	}
		
	public static IndicadorRiscoSaudeAmbientalBuilder 
			newInstance(List<IndicadorRiscoSaudeAmbiental> indicadorRiscoSaudeAmbientais) {
		return new IndicadorRiscoSaudeAmbientalBuilder(indicadorRiscoSaudeAmbientais);
	}
		
	private IndicadorRiscoSaudeAmbientalBuilder(List<IndicadorRiscoSaudeAmbiental> indicadorRiscoSaudeAmbientais) {
		super(indicadorRiscoSaudeAmbientais);
	}
		
	private IndicadorRiscoSaudeAmbientalBuilder(IndicadorRiscoSaudeAmbiental indicadorRiscoSaudeAmbiental) {
		super(indicadorRiscoSaudeAmbiental);
	}
}
