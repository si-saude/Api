package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ItemAuditoriaAtestado;

public class ItemAuditoriaAtestadoDao extends GenericDao<ItemAuditoriaAtestado> {
	private static ItemAuditoriaAtestadoDao instance;
	
	private ItemAuditoriaAtestadoDao() {
		super();
	}
	
	public static ItemAuditoriaAtestadoDao getInstance() {
		if(instance==null)
			instance = new ItemAuditoriaAtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	}
}
