package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.InstalacaoFilter;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoExampleBuilder extends GenericExampleBuilder<Instalacao,InstalacaoFilter> {

	public static InstalacaoExampleBuilder newInstance(InstalacaoFilter filter) {
		return new InstalacaoExampleBuilder(filter);
	}
	
	private InstalacaoExampleBuilder(InstalacaoFilter filter) {
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
