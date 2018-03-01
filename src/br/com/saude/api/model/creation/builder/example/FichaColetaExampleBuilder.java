package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.FichaColetaFilter;
import br.com.saude.api.model.entity.po.FichaColeta;

public class FichaColetaExampleBuilder extends GenericExampleBuilder<FichaColeta, FichaColetaFilter> {
	
	public static FichaColetaExampleBuilder newInstance(FichaColetaFilter filter) {
		return new FichaColetaExampleBuilder(filter);
	}
	
	private FichaColetaExampleBuilder(FichaColetaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {}
	
}
