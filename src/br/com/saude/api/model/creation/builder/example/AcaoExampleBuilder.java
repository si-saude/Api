package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AcaoFilter;
import br.com.saude.api.model.entity.po.Acao;

public class AcaoExampleBuilder extends GenericExampleBuilder<Acao, AcaoFilter> {
	
	public static AcaoExampleBuilder newInstance(AcaoFilter filter) {
		return new AcaoExampleBuilder(filter);
	}
	
	private AcaoExampleBuilder(AcaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
}
