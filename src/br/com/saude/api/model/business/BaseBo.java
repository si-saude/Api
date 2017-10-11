package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.BaseBuilder;
import br.com.saude.api.model.creation.builder.example.BaseExampleBuilder;
import br.com.saude.api.model.entity.filter.BaseFilter;
import br.com.saude.api.model.entity.po.Base;
import br.com.saude.api.model.persistence.BaseDao;

public class BaseBo 
		extends GenericBo<Base, BaseFilter, BaseDao, BaseBuilder, BaseExampleBuilder> {

	private static BaseBo instance;
	
	private BaseBo() {
		super();
	}
	
	public static BaseBo getInstance() {
		if(instance == null)
			instance = new BaseBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
