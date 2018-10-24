package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.MedidaAlimentar;

public class MedidaAlimentarDao extends GenericDao<MedidaAlimentar> {
	private static MedidaAlimentarDao instance;
	
	private MedidaAlimentarDao() {
		super();
	}
	
	public static MedidaAlimentarDao getInstance() {
		if(instance==null)
			instance = new MedidaAlimentarDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {}
	
}
