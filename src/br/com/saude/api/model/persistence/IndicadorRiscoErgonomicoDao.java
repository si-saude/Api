package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;

public class IndicadorRiscoErgonomicoDao extends GenericDao<IndicadorRiscoErgonomico> {

	private static IndicadorRiscoErgonomicoDao instance;
	
	private IndicadorRiscoErgonomicoDao() {
		super();
	}
	
	public static IndicadorRiscoErgonomicoDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoErgonomicoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
}
