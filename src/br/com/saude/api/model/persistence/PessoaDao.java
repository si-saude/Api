package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Pessoa;

public class PessoaDao extends GenericDao<Pessoa> {
	
	private static PessoaDao instance;
	
	private PessoaDao() {
		super();
	}
	
	public static PessoaDao getInstance() {
		if(instance==null)
			instance = new PessoaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
