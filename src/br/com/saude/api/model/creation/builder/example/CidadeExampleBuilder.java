package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;

public class CidadeExampleBuilder extends GenericExampleBuilder<Cidade,CidadeFilter> {

	public static CidadeExampleBuilder newInstance(CidadeFilter filter) {
		return new CidadeExampleBuilder(filter);
	}
	
	private CidadeExampleBuilder(CidadeFilter filter) {
		super(filter);
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addUf() {
		if(this.filter.getUf() != null)
			this.entity.setUf(Helper.filterLike(this.filter.getUf()));
	}

	@Override
	protected void createExample() {
		addNome();
		addUf();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
