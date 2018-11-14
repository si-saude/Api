package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemAuditoriaAsoFilter;
import br.com.saude.api.model.entity.po.ItemAuditoriaAso;

public class ItemAuditoriaAsoBuilder extends GenericEntityBuilder<ItemAuditoriaAso, ItemAuditoriaAsoFilter> {

	
	public static ItemAuditoriaAsoBuilder newInstance(ItemAuditoriaAso itemAuditoriaAso) {
		return new ItemAuditoriaAsoBuilder(itemAuditoriaAso);
	}
	
	public static ItemAuditoriaAsoBuilder newInstance(List<ItemAuditoriaAso> itemAuditoriaAsos) {
		return new ItemAuditoriaAsoBuilder(itemAuditoriaAsos);
	}
	
	private ItemAuditoriaAsoBuilder(ItemAuditoriaAso itemAuditoriaAso) {
		super(itemAuditoriaAso);
	}
	
	private ItemAuditoriaAsoBuilder(List<ItemAuditoriaAso> itemAuditoriaAsos) {
		super(itemAuditoriaAsos);
	}

	@Override
	protected void initializeFunctions() {

	}

	@Override
	protected ItemAuditoriaAso clone(ItemAuditoriaAso itemAuditoriaAso) {
		ItemAuditoriaAso newItemAuditoriaAso = new ItemAuditoriaAso();
		
		newItemAuditoriaAso.setId(itemAuditoriaAso.getId());
		newItemAuditoriaAso.setConforme(itemAuditoriaAso.isConforme());
		newItemAuditoriaAso.setDescricao(itemAuditoriaAso.getDescricao());
		newItemAuditoriaAso.setVersion(itemAuditoriaAso.getVersion());
		newItemAuditoriaAso.setOrdem(itemAuditoriaAso.getOrdem());
		return newItemAuditoriaAso;
	}
	
	
	@Override
	public ItemAuditoriaAso cloneFromFilter(ItemAuditoriaAsoFilter filter) {
		return null;
	}
}
