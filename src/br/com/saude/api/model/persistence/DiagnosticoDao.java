package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Diagnostico;

public class DiagnosticoDao extends GenericDao<Diagnostico> {
	private static DiagnosticoDao instance;
	
	private DiagnosticoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {}
	
	public static DiagnosticoDao getInstance() {
		if(instance==null)
			instance = new DiagnosticoDao();
		return instance;
	}
}