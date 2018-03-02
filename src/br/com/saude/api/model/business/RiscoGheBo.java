package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RiscoGheBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoGheExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoGheFilter;
import br.com.saude.api.model.entity.po.RiscoGhe;
import br.com.saude.api.model.persistence.RiscoGheDao;

public class RiscoGheBo 
	extends GenericBo<RiscoGhe, RiscoGheFilter, RiscoGheDao, RiscoGheBuilder, RiscoGheExampleBuilder> {

	private static RiscoGheBo instance;
	
	private RiscoGheBo() {
		super();
	}
	
	public static RiscoGheBo getInstance() {
		if(instance == null)
			instance = new RiscoGheBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
