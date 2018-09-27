package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ItemAuditoriaAtestadoFilter;
import br.com.saude.api.model.entity.po.ItemAuditoriaAtestado;

public class ItemAuditoriaAtestadoExampleBuilder extends GenericExampleBuilder<ItemAuditoriaAtestado,ItemAuditoriaAtestadoFilter> {
	
	public static ItemAuditoriaAtestadoExampleBuilder newInstance(ItemAuditoriaAtestadoFilter filter) {
		return new ItemAuditoriaAtestadoExampleBuilder(filter);
	}
	
	private ItemAuditoriaAtestadoExampleBuilder(ItemAuditoriaAtestadoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addId();
		addDescricao();
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
		
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
}
