package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AprhoItemBuilder;
import br.com.saude.api.model.creation.builder.example.AprhoItemExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoItemFilter;
import br.com.saude.api.model.entity.po.AprhoItem;
import br.com.saude.api.model.persistence.AprhoItemDao;

public class AprhoItemBo 
	extends GenericBo<AprhoItem, AprhoItemFilter, 
		AprhoItemDao, AprhoItemBuilder, AprhoItemExampleBuilder> {
	
	private static AprhoItemBo instance;
	
	private AprhoItemBo() {
		super();
	}
	
	public static AprhoItemBo getInstance() {
		if(instance == null)
			instance = new AprhoItemBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
	}	
}
