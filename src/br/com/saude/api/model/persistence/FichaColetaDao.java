package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.FichaColeta;

public class FichaColetaDao extends GenericDao<FichaColeta> {
	private static FichaColetaDao instance;
	
	private FichaColetaDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {

	}
	
	public static FichaColetaDao getInstance() {
		if (instance == null)
			instance = new FichaColetaDao();
		return instance;
	}

}
