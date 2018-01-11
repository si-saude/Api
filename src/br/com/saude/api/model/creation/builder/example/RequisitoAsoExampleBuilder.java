package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RequisitoAsoFilter;
import br.com.saude.api.model.entity.po.RequisitoAso;

public class RequisitoAsoExampleBuilder 
	extends GenericExampleBuilder<RequisitoAso, RequisitoAsoFilter> {

	public static RequisitoAsoExampleBuilder newInstance(RequisitoAsoFilter filter) {
		return new RequisitoAsoExampleBuilder(filter);
	}
	
	private RequisitoAsoExampleBuilder(RequisitoAsoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addConteudo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addConteudo();
	}
	
	private void addConteudo() {
		if(this.filter.getConteudo()!= null)
			this.entity.setConteudo(Helper.filterLike(this.filter.getConteudo()));
	}
}
