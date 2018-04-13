package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Acao;

public class AcaoDao extends GenericDao<Acao> {

	private static AcaoDao instance;
	
	private AcaoDao() {
		super();
	}
	
	public static AcaoDao getInstance() {
		if(instance==null)
			instance = new AcaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
