package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EnfaseFilter;
import br.com.saude.api.model.entity.po.Enfase;

public class EnfaseExampleBuilder extends GenericExampleBuilder<Enfase,EnfaseFilter> {

	public static EnfaseExampleBuilder newInstance(EnfaseFilter filter) {
		return new EnfaseExampleBuilder(filter);
	}
	
	private EnfaseExampleBuilder(EnfaseFilter filter) {
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
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}

}