package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemIndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.ItemIndicadorConhecimentoAlimentar;

public class ItemIndicadorConhecimentoAlimentarBuilder extends GenericEntityBuilder<ItemIndicadorConhecimentoAlimentar,ItemIndicadorConhecimentoAlimentarFilter> {
	public static ItemIndicadorConhecimentoAlimentarBuilder newInstance(ItemIndicadorConhecimentoAlimentar itemIndicadorConhecimentoAlimentar) {
		return new ItemIndicadorConhecimentoAlimentarBuilder(itemIndicadorConhecimentoAlimentar);
	}
	
	public static ItemIndicadorConhecimentoAlimentarBuilder newInstance(List<ItemIndicadorConhecimentoAlimentar> itemIndicadorConhecimentoAlimentares) {
		return new ItemIndicadorConhecimentoAlimentarBuilder(itemIndicadorConhecimentoAlimentares);
	}
	
	private ItemIndicadorConhecimentoAlimentarBuilder(List<ItemIndicadorConhecimentoAlimentar> itemIndicadorConhecimentoAlimentares) {
		super(itemIndicadorConhecimentoAlimentares);
	}

	private ItemIndicadorConhecimentoAlimentarBuilder(ItemIndicadorConhecimentoAlimentar itemIndicadorConhecimentoAlimentar) {
		super(itemIndicadorConhecimentoAlimentar);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected ItemIndicadorConhecimentoAlimentar clone(ItemIndicadorConhecimentoAlimentar itemIndicadorConhecimentoAlimentar) {
		ItemIndicadorConhecimentoAlimentar cloneItemIndicadorConhecimentoAlimentar = new ItemIndicadorConhecimentoAlimentar();
		
		cloneItemIndicadorConhecimentoAlimentar.setId(itemIndicadorConhecimentoAlimentar.getId());
		cloneItemIndicadorConhecimentoAlimentar.setVersion(itemIndicadorConhecimentoAlimentar.getVersion());
		cloneItemIndicadorConhecimentoAlimentar.setDescricao(itemIndicadorConhecimentoAlimentar.getDescricao());
		cloneItemIndicadorConhecimentoAlimentar.setIndicadorConhecimentoAlimentar(
			IndicadorConhecimentoAlimentarBuilder.newInstance(itemIndicadorConhecimentoAlimentar.getIndicadorConhecimentoAlimentar()).getEntity());
		
		return cloneItemIndicadorConhecimentoAlimentar;
	}

	@Override
	public ItemIndicadorConhecimentoAlimentar cloneFromFilter(ItemIndicadorConhecimentoAlimentarFilter filter) {
		return null;
	}

}
