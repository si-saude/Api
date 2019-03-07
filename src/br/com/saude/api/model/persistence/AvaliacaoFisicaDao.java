package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;

public class AvaliacaoFisicaDao extends GenericDao<AvaliacaoFisica> {
	private static AvaliacaoFisicaDao instance;
	
	private AvaliacaoFisicaDao() {
		super();
	}
	
	public static AvaliacaoFisicaDao getInstance() {
		if(instance==null)
			instance = new AvaliacaoFisicaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}
}
