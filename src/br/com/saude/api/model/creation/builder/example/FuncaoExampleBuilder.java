package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoExampleBuilder extends GenericExampleBuilder<Funcao,FuncaoFilter> {

	public static FuncaoExampleBuilder newInstance(FuncaoFilter filter) {
		return new FuncaoExampleBuilder(filter);
	}
	
	private FuncaoExampleBuilder(FuncaoFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}

	@Override
	protected void createExample() {
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
	
}
