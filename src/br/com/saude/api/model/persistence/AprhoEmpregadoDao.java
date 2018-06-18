package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AprhoEmpregado;

public class AprhoEmpregadoDao extends GenericDao<AprhoEmpregado> {

	private static AprhoEmpregadoDao instance;
	
	private AprhoEmpregadoDao() {
		super();
	}
	
	public static AprhoEmpregadoDao getInstance() {
		if(instance == null)
			instance = new AprhoEmpregadoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
}
