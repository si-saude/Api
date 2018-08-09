package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.BaseFilter;
import br.com.saude.api.model.entity.po.Base;

public class BaseExampleBuilder extends GenericExampleBuilder<Base, BaseFilter> {

	public static BaseExampleBuilder newInstance(BaseFilter filter) {
		return new BaseExampleBuilder(filter);
	}
	
	private BaseExampleBuilder(BaseFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addUf();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.criterions.add(Restrictions.ilike("nome", Helper.filterLike(this.filter.getNome())));
	}
	
	private void addUf() {
		if(this.filter.getUf() != null)
			this.entity.setUf(Helper.filterLike(this.filter.getUf()));
	}

}
