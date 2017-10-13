package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoExampleBuilder extends GenericExampleBuilder<Funcao, FuncaoFilter> {

	public static FuncaoExampleBuilder newInstance(FuncaoFilter filter) {
		return new FuncaoExampleBuilder(filter);
	}
	
	private FuncaoExampleBuilder(FuncaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
}
