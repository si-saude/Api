package br.com.saude.api.model.persistence;

import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;

public class IndicadorRiscoErgonomicoDao extends IndicadorRiscoDao<IndicadorRiscoErgonomico> {

	private static IndicadorRiscoErgonomicoDao instance;
	
	private IndicadorRiscoErgonomicoDao() {
		super();
	}
	
	public static IndicadorRiscoErgonomicoDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoErgonomicoDao();
		return instance;
	}
}
