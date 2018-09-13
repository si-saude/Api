package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FonteGeradoraFilter;
import br.com.saude.api.model.entity.po.FonteGeradora;

public class FonteGeradoraExampleBuilder extends GenericExampleBuilder<FonteGeradora, FonteGeradoraFilter> {

	public static FonteGeradoraExampleBuilder newInstance(FonteGeradoraFilter filter) {
		return new FonteGeradoraExampleBuilder(filter);
	}
	
	private FonteGeradoraExampleBuilder(FonteGeradoraFilter filter) {
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
