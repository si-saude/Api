package br.com.saude.api.model.persistence;

import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;

public class IndicadorRiscoSanitarioDao extends IndicadorRiscoDao<IndicadorRiscoSanitario>{

	private static IndicadorRiscoSanitarioDao instance;
	
	private IndicadorRiscoSanitarioDao() {
		super();
	}
	
	public static IndicadorRiscoSanitarioDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoSanitarioDao();
		return instance;
	}
}
