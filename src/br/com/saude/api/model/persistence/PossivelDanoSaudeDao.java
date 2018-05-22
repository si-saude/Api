package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.PossivelDanoSaude;

public class PossivelDanoSaudeDao extends GenericDao<PossivelDanoSaude> {

	private static PossivelDanoSaudeDao instance;
	
	private PossivelDanoSaudeDao() {
		super();
	}
	
	public static PossivelDanoSaudeDao getInstance() {
		if(instance==null)
			instance = new PossivelDanoSaudeDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
