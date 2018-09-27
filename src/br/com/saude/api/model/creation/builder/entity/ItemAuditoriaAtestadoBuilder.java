package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemAuditoriaAtestadoFilter;
import br.com.saude.api.model.entity.po.ItemAuditoriaAtestado;

public class ItemAuditoriaAtestadoBuilder extends GenericEntityBuilder<ItemAuditoriaAtestado,ItemAuditoriaAtestadoFilter> {
	
	public static ItemAuditoriaAtestadoBuilder newInstance(ItemAuditoriaAtestado itemAuditoriaAtestado) {
		return new ItemAuditoriaAtestadoBuilder(itemAuditoriaAtestado);
	}
	
	public static ItemAuditoriaAtestadoBuilder newInstance(List<ItemAuditoriaAtestado> itemAuditoriaAtestados) {
		return new ItemAuditoriaAtestadoBuilder(itemAuditoriaAtestados);
	}
	
	private ItemAuditoriaAtestadoBuilder(List<ItemAuditoriaAtestado> itemAuditoriaAtestados) {
		super(itemAuditoriaAtestados);
	}

	private ItemAuditoriaAtestadoBuilder(ItemAuditoriaAtestado itemAuditoriaAtestado) {
		super(itemAuditoriaAtestado);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected ItemAuditoriaAtestado clone(ItemAuditoriaAtestado itemAuditoriaAtestado) {
		ItemAuditoriaAtestado cloneItemAuditoriaAtestado = new ItemAuditoriaAtestado();
		
		cloneItemAuditoriaAtestado.setId(itemAuditoriaAtestado.getId());
		cloneItemAuditoriaAtestado.setVersion(itemAuditoriaAtestado.getVersion());
		cloneItemAuditoriaAtestado.setDescricao(itemAuditoriaAtestado.getDescricao());
		
		return cloneItemAuditoriaAtestado;
	}

	@Override
	public ItemAuditoriaAtestado cloneFromFilter(ItemAuditoriaAtestadoFilter filter) {
		return null;
	}
}
