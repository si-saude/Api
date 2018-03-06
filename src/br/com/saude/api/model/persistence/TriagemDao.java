package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Triagem;

public class TriagemDao extends GenericDao<Triagem> {

	private static TriagemDao instance;
	
	private TriagemDao() {
		super();
	}
	
	public static TriagemDao getInstance() {
		if(instance == null)
			instance = new TriagemDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
