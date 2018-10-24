package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ItemIndicadorConhecimentoAlimentar;

public class ItemIndicadorConhecimentoAlimentarDao  extends GenericDao<ItemIndicadorConhecimentoAlimentar> {
	private static ItemIndicadorConhecimentoAlimentarDao instance;
	
	private ItemIndicadorConhecimentoAlimentarDao() {
		super();
	}
	
	public static ItemIndicadorConhecimentoAlimentarDao getInstance() {
		if(instance==null)
			instance = new ItemIndicadorConhecimentoAlimentarDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	}

}
