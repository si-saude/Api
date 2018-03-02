package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RiscoGheFilter;
import br.com.saude.api.model.entity.po.RiscoGhe;

public class RiscoGheExampleBuilder extends GenericExampleBuilder<RiscoGhe, RiscoGheFilter> {

	public static RiscoGheExampleBuilder newInstance(RiscoGheFilter filter) {
		return new RiscoGheExampleBuilder(filter);
	}
	
	private RiscoGheExampleBuilder(RiscoGheFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addTitulo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}

	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
}
