package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.FonteGeradora;

public class FonteGeradoraDao extends GenericDao<FonteGeradora> {

	private static FonteGeradoraDao instance;
	
	private FonteGeradoraDao() {
		super();
	}
	
	public static FonteGeradoraDao getInstance() {
		if(instance==null)
			instance = new FonteGeradoraDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
