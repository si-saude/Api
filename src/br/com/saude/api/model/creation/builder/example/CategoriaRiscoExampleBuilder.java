package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CategoriaRiscoFilter;
import br.com.saude.api.model.entity.po.CategoriaRisco;

public class CategoriaRiscoExampleBuilder extends GenericExampleBuilder<CategoriaRisco, CategoriaRiscoFilter> {

	public static CategoriaRiscoExampleBuilder newInstance(CategoriaRiscoFilter filter) {
		return new CategoriaRiscoExampleBuilder(filter);
	}
	
	private CategoriaRiscoExampleBuilder(CategoriaRiscoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addObservacao();
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addObservacao() {
		if(this.filter.getObservacao() != null)
			this.entity.setObservacao(Helper.filterLike(this.filter.getObservacao()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}

}
