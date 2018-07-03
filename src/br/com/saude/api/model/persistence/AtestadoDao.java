package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoDao extends GenericDao<Atestado> {

	private static AtestadoDao instance;
	
	private AtestadoDao() {
		super();
	}
	
	public static AtestadoDao getInstance() {
		if(instance==null)
			instance = new AtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
