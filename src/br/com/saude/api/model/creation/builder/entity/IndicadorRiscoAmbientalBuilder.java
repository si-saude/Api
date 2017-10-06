package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;

public class IndicadorRiscoAmbientalBuilder extends IndicadorRiscoBuilder<IndicadorRiscoAmbiental> {

	public static IndicadorRiscoAmbientalBuilder 
			newInstance(IndicadorRiscoAmbiental indicadorRiscoAmbiental) {
		return new IndicadorRiscoAmbientalBuilder(indicadorRiscoAmbiental);
	}
	
	public static IndicadorRiscoAmbientalBuilder 
			newInstance(List<IndicadorRiscoAmbiental> indicadorRiscoAmbientais) {
		return new IndicadorRiscoAmbientalBuilder(indicadorRiscoAmbientais);
	}
	
	private IndicadorRiscoAmbientalBuilder(List<IndicadorRiscoAmbiental> indicadorRiscoAmbientais) {
		super(indicadorRiscoAmbientais);
	}

	private IndicadorRiscoAmbientalBuilder(IndicadorRiscoAmbiental indicadorRiscoAmbiental) {
		super(indicadorRiscoAmbiental);
	}
}
