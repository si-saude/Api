package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;

public class IndicadorRiscoAcidenteBuilder extends IndicadorRiscoBuilder<IndicadorRiscoAcidente> {


	public static IndicadorRiscoAcidenteBuilder 
			newInstance(IndicadorRiscoAcidente indicadorRiscoAcidente) {
		return new IndicadorRiscoAcidenteBuilder(indicadorRiscoAcidente);
	}
	
	public static IndicadorRiscoAcidenteBuilder 
			newInstance(List<IndicadorRiscoAcidente> indicadorRiscoAcidentes) {
		return new IndicadorRiscoAcidenteBuilder(indicadorRiscoAcidentes);
	}
	
	private IndicadorRiscoAcidenteBuilder(List<IndicadorRiscoAcidente> indicadorRiscoAcidentes) {
		super(indicadorRiscoAcidentes);
	}

	private IndicadorRiscoAcidenteBuilder(IndicadorRiscoAcidente indicadorRiscoAcidente) {
		super(indicadorRiscoAcidente);
	}
}
