package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RegimeBuilder;
import br.com.saude.api.model.creation.builder.example.RegimeExampleBuilder;
import br.com.saude.api.model.entity.filter.RegimeFilter;
import br.com.saude.api.model.entity.po.Regime;
import br.com.saude.api.model.persistence.RegimeDao;

public class RegimeBo extends GenericBo<Regime, RegimeFilter, RegimeDao, 
										RegimeBuilder, RegimeExampleBuilder> {

	private static RegimeBo instance;
	
	private RegimeBo() {
		super();
	}
	
	public static RegimeBo getInstance() {
		if(instance == null)
			instance = new RegimeBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

}
