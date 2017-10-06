package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;

public class IndicadorRiscoSanitarioDao extends GenericDao<IndicadorRiscoSanitario>{

	private static IndicadorRiscoSanitarioDao instance;
	
	private IndicadorRiscoSanitarioDao() {
		super();
	}
	
	public static IndicadorRiscoSanitarioDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoSanitarioDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {

	}
}
