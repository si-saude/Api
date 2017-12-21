package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Feriado;

public class FeriadoDao extends GenericDao<Feriado> {
	private static FeriadoDao instance;
	
	private FeriadoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {

	}
	
	public static FeriadoDao getInstance() {
		if (instance == null)
			instance = new FeriadoDao();
		return instance;
	}

}
