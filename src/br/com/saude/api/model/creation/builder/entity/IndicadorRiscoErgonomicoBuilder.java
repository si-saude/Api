package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;

public class IndicadorRiscoErgonomicoBuilder extends IndicadorRiscoBuilder<IndicadorRiscoErgonomico> {


	public static IndicadorRiscoErgonomicoBuilder 
			newInstance(IndicadorRiscoErgonomico indicadorRiscoErgonomico) {
		return new IndicadorRiscoErgonomicoBuilder(indicadorRiscoErgonomico);
	}
	
	public static IndicadorRiscoErgonomicoBuilder 
			newInstance(List<IndicadorRiscoErgonomico> indicadorRiscoErgonomicos) {
		return new IndicadorRiscoErgonomicoBuilder(indicadorRiscoErgonomicos);
	}
	
	private IndicadorRiscoErgonomicoBuilder(List<IndicadorRiscoErgonomico> indicadorRiscoErgonomicos) {
		super(indicadorRiscoErgonomicos);
	}

	private IndicadorRiscoErgonomicoBuilder(IndicadorRiscoErgonomico indicadorRiscoErgonomico) {
		super(indicadorRiscoErgonomico);
	}
}
