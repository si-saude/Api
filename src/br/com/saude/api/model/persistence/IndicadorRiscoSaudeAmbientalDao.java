package br.com.saude.api.model.persistence;

import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;

public class IndicadorRiscoSaudeAmbientalDao extends IndicadorRiscoDao<IndicadorRiscoSaudeAmbiental> {

	private static IndicadorRiscoSaudeAmbientalDao instance;
	
	private IndicadorRiscoSaudeAmbientalDao() {
		super();
	}
	
	public static IndicadorRiscoSaudeAmbientalDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoSaudeAmbientalDao();
		return instance;
	}
}
