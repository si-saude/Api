package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemResultadoExameFilter;
import br.com.saude.api.model.entity.po.ItemResultadoExame;

public class ItemResultadoExameBuilder extends GenericEntityBuilder<ItemResultadoExame,ItemResultadoExameFilter> {

	private Function<Map<String, ItemResultadoExame>,ItemResultadoExame> loadResultadoExame;
	
	public static ItemResultadoExameBuilder newInstance(ItemResultadoExame item) {
		return new ItemResultadoExameBuilder(item);
	}
	
	public static ItemResultadoExameBuilder newInstance(List<ItemResultadoExame> itens) {
		return new ItemResultadoExameBuilder(itens);
	}
	
	private ItemResultadoExameBuilder(ItemResultadoExame item) {
		super(item);
	}

	private ItemResultadoExameBuilder(List<ItemResultadoExame> itens) {
		super(itens);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadResultadoExame = itens -> {
			if ( itens.get("origem").getResultadoExame() != null ) {
				itens.get("destino").setResultadoExame( ResultadoExameBuilder.
						newInstance(itens.get("origem").getResultadoExame()).
						getEntity() );
			}
			return itens.get("destino");
		};
		
	}
	
	public ItemResultadoExameBuilder loadResultadoExame() {
		return (ItemResultadoExameBuilder) this.loadProperty(this.loadResultadoExame);
	}

	@Override
	protected ItemResultadoExame clone(ItemResultadoExame item) {
		ItemResultadoExame newItem = new ItemResultadoExame();
		
		newItem.setId(item.getId());
		newItem.setResultado(item.getResultado());
		newItem.setCodigo(item.getCodigo());
		newItem.setTitulo(item.getTitulo());
		newItem.setVersion(item.getVersion());
		
		return newItem;
	}

	@Override
	public ItemResultadoExame cloneFromFilter(ItemResultadoExameFilter filter) {
		return null;
	}

}
