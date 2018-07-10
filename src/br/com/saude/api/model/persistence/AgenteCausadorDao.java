package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AgenteCausador;

public class AgenteCausadorDao extends GenericDao<AgenteCausador>{
	private static AgenteCausadorDao instance;
	
	private AgenteCausadorDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static AgenteCausadorDao getInstance() {
		if(instance == null)
			instance = new AgenteCausadorDao();
		return instance;
	}

}
