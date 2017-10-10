package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.PeriodicidadeFilter;
import br.com.saude.api.model.entity.po.Periodicidade;

public class PeriodicidadeExampleBuilder 
				extends GenericExampleBuilder<Periodicidade,PeriodicidadeFilter> {

	public static PeriodicidadeExampleBuilder newInstance(PeriodicidadeFilter filter) {
		return new PeriodicidadeExampleBuilder(filter);
	}
	
	private PeriodicidadeExampleBuilder(PeriodicidadeFilter filter) {
		super(filter);
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addMeses() {
		if(this.filter.getMeses() > 0)
			this.entity.setMeses(this.filter.getMeses());
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
		addMeses();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
