package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CriterioFilter;
import br.com.saude.api.model.entity.po.Criterio;

public class CriterioExampleBuilder extends GenericExampleBuilder<Criterio,CriterioFilter> {

	public static CriterioExampleBuilder newInstance(CriterioFilter filter) {
		return new CriterioExampleBuilder(filter);
	}
	
	private CriterioExampleBuilder(CriterioFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addTipo();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addTipo() {
		if(this.filter.getTipo() != null)
			this.entity.setTipo(Helper.filterLike(this.filter.getTipo()));
	}
}
