package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemPerguntaFichaColetaFilter;
import br.com.saude.api.model.entity.po.ItemPerguntaFichaColeta;

public class ItemPerguntaFichaColetaBuilder 
	extends GenericEntityBuilder<ItemPerguntaFichaColeta, ItemPerguntaFichaColetaFilter> {

	public static ItemPerguntaFichaColetaBuilder newInstance(ItemPerguntaFichaColeta itemPerguntaFichaColeta) {
		return new ItemPerguntaFichaColetaBuilder(itemPerguntaFichaColeta);
	}
	
	public static ItemPerguntaFichaColetaBuilder newInstance(List<ItemPerguntaFichaColeta> itemPerguntaFichaColetas) {
		return new ItemPerguntaFichaColetaBuilder(itemPerguntaFichaColetas);
	}
	
	private ItemPerguntaFichaColetaBuilder(ItemPerguntaFichaColeta itemPerguntaFichaColeta) {
		super(itemPerguntaFichaColeta);
	}
	
	private ItemPerguntaFichaColetaBuilder(List<ItemPerguntaFichaColeta> itemPerguntaFichaColetas) {
		super(itemPerguntaFichaColetas);
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ItemPerguntaFichaColeta clone(ItemPerguntaFichaColeta itemPerguntaFichaColeta) {
		ItemPerguntaFichaColeta cloneItemPerguntaFichaColeta = new ItemPerguntaFichaColeta();
		
		cloneItemPerguntaFichaColeta.setId(itemPerguntaFichaColeta.getId());
		cloneItemPerguntaFichaColeta.setVersion(itemPerguntaFichaColeta.getVersion());
		cloneItemPerguntaFichaColeta.setLabel(itemPerguntaFichaColeta.getLabel());
		cloneItemPerguntaFichaColeta.setPath(itemPerguntaFichaColeta.getPath());
		
		if ( cloneItemPerguntaFichaColeta.getPergunta() != null )
			cloneItemPerguntaFichaColeta.setPergunta(
					PerguntaFichaColetaBuilder.newInstance(itemPerguntaFichaColeta.getPergunta()).getEntity());
		
		return cloneItemPerguntaFichaColeta;
	}

	@Override
	public ItemPerguntaFichaColeta cloneFromFilter(ItemPerguntaFichaColetaFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
