package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RequisitoAsoBuilder;
import br.com.saude.api.model.creation.builder.example.RequisitoAsoExampleBuilder;
import br.com.saude.api.model.entity.filter.RequisitoAsoFilter;
import br.com.saude.api.model.entity.po.RequisitoAso;
import br.com.saude.api.model.persistence.RequisitoAsoDao;

public class RequisitoAsoBo extends GenericBo<RequisitoAso, RequisitoAsoFilter, 
	RequisitoAsoDao, RequisitoAsoBuilder, RequisitoAsoExampleBuilder> {

	private static RequisitoAsoBo instance;
	
	private RequisitoAsoBo() {
		super();
	}
	
	public static RequisitoAsoBo getInstance() {
		if(instance==null)
			instance = new RequisitoAsoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
