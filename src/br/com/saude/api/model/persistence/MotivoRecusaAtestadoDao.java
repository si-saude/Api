package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.MotivoRecusaAtestado;

public class MotivoRecusaAtestadoDao extends GenericDao<MotivoRecusaAtestado> {

	private static MotivoRecusaAtestadoDao instance;
	
	private MotivoRecusaAtestadoDao() {
		super();
	}
	
	public static MotivoRecusaAtestadoDao getInstance() {
		if(instance==null)
			instance = new MotivoRecusaAtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
