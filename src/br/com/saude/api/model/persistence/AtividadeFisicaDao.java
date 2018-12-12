package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AtividadeFisica;

public class AtividadeFisicaDao extends GenericDao<AtividadeFisica> {
	private static AtividadeFisicaDao instance;
	
	private AtividadeFisicaDao() {
		super();
	}
	
	public static AtividadeFisicaDao getInstance() {
		if(instance==null)
			instance = new AtividadeFisicaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}
}
