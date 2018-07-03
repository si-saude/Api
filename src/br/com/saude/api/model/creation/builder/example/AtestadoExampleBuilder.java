package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoExampleBuilder extends GenericExampleBuilder<Atestado, AtestadoFilter> {

	public static AtestadoExampleBuilder newInstance(AtestadoFilter filter) {
		return new AtestadoExampleBuilder(filter);
	}
	
	private AtestadoExampleBuilder(AtestadoFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException { }
	
	@Override
	protected void createExampleSelectList() { }
}
