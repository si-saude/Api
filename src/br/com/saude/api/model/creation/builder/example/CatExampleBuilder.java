package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;

public class CatExampleBuilder extends GenericExampleBuilder<Cat, CatFilter> {

	public static CatExampleBuilder newInstance(CatFilter filter) {
		return new CatExampleBuilder(filter);
	}
	
	private CatExampleBuilder(CatFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNumero();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNumero() {
		if(this.filter.getNumero() != null)
			this.entity.setNumero(Helper.filterLike(this.filter.getNumero()));
	}
	
}
