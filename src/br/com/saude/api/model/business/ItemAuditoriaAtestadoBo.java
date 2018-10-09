package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ItemAuditoriaAtestadoBuilder;
import br.com.saude.api.model.creation.builder.example.ItemAuditoriaAtestadoExampleBuilder;
import br.com.saude.api.model.entity.filter.ItemAuditoriaAtestadoFilter;
import br.com.saude.api.model.entity.po.ItemAuditoriaAtestado;
import br.com.saude.api.model.persistence.ItemAuditoriaAtestadoDao;

public class ItemAuditoriaAtestadoBo extends GenericBo<ItemAuditoriaAtestado, ItemAuditoriaAtestadoFilter, 
ItemAuditoriaAtestadoDao, ItemAuditoriaAtestadoBuilder, ItemAuditoriaAtestadoExampleBuilder> {
	
private static ItemAuditoriaAtestadoBo instance;
	
	private ItemAuditoriaAtestadoBo() {
		super();
	}
	
	public static ItemAuditoriaAtestadoBo getInstance() {
		if(instance == null)
			instance = new ItemAuditoriaAtestadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {	
	}

}
