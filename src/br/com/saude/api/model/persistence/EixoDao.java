package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Eixo;

public class EixoDao extends GenericDao<Eixo> {

	private static EixoDao instance;
	
	private EixoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static EixoDao getInstance() {
		if(instance==null)
			instance = new EixoDao();
		return instance;
	}

}
