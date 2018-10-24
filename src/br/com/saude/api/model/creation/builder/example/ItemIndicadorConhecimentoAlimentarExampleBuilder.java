package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ItemIndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.ItemIndicadorConhecimentoAlimentar;

public class ItemIndicadorConhecimentoAlimentarExampleBuilder 
	extends GenericExampleBuilder<ItemIndicadorConhecimentoAlimentar, ItemIndicadorConhecimentoAlimentarFilter> {

	public static ItemIndicadorConhecimentoAlimentarExampleBuilder newInstance(ItemIndicadorConhecimentoAlimentarFilter filter) {
		return new ItemIndicadorConhecimentoAlimentarExampleBuilder(filter);
	}
	
	private ItemIndicadorConhecimentoAlimentarExampleBuilder(ItemIndicadorConhecimentoAlimentarFilter filter) {
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
		if(this.filter.getDescricao()!=null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}

}
