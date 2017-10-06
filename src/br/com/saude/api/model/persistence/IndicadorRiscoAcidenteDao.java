package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;

public class IndicadorRiscoAcidenteDao extends GenericDao<IndicadorRiscoAcidente> {

	private static IndicadorRiscoAcidenteDao instance;
	
	private IndicadorRiscoAcidenteDao() {
		super();
	}
	
	public static IndicadorRiscoAcidenteDao getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoAcidenteDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {

	}
}
