package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AcompanhamentoFilter;
import br.com.saude.api.model.entity.po.Acompanhamento;

public class AcompanhamentoExampleBuilder extends GenericExampleBuilder<Acompanhamento, AcompanhamentoFilter> {

	public static AcompanhamentoExampleBuilder newInstance(AcompanhamentoFilter filter) {
		return new AcompanhamentoExampleBuilder(filter);
	}
	
	private AcompanhamentoExampleBuilder(AcompanhamentoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}

}
