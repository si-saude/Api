package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Acompanhamento;

public class AcompanhamentoDao extends GenericDao<Acompanhamento> {

	private static AcompanhamentoDao instance;
	
	private AcompanhamentoDao() {
		super();
	}
	
	public static AcompanhamentoDao getInstance() {
		if(instance==null)
			instance = new AcompanhamentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
