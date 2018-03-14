package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemRespostaFichaColetaFilter;
import br.com.saude.api.model.entity.po.ItemRespostaFichaColeta;

public class ItemRespostaFichaColetaBuilder extends GenericEntityBuilder<ItemRespostaFichaColeta, ItemRespostaFichaColetaFilter> {
	
	public static ItemRespostaFichaColetaBuilder newInstance(ItemRespostaFichaColeta itemRespostaFichaColeta) {
		return new ItemRespostaFichaColetaBuilder(itemRespostaFichaColeta);
	}
	
	public static ItemRespostaFichaColetaBuilder newInstance(List<ItemRespostaFichaColeta> itemRespostaFichaColetas) {
		return new ItemRespostaFichaColetaBuilder(itemRespostaFichaColetas);
	}
	
	private ItemRespostaFichaColetaBuilder(ItemRespostaFichaColeta itemRespostaFichaColeta) {
		super(itemRespostaFichaColeta);
	}
	
	private ItemRespostaFichaColetaBuilder(List<ItemRespostaFichaColeta> itemRespostaFichaColetas) {
		super(itemRespostaFichaColetas);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected ItemRespostaFichaColeta clone(ItemRespostaFichaColeta itemRespostaFichaColeta) {
		ItemRespostaFichaColeta cloneItemRespostaFichaColeta = new ItemRespostaFichaColeta();
		
		cloneItemRespostaFichaColeta.setId(itemRespostaFichaColeta.getId());
		cloneItemRespostaFichaColeta.setVersion(itemRespostaFichaColeta.getVersion());
		cloneItemRespostaFichaColeta.setConteudo(itemRespostaFichaColeta.getConteudo());
		
		if(itemRespostaFichaColeta.getItem() != null)
			cloneItemRespostaFichaColeta.setItem(
				ItemRespostaFichaColetaBuilder.newInstance(itemRespostaFichaColeta.getItem()).getEntity());
		
		return cloneItemRespostaFichaColeta;
	}

	@Override
	public ItemRespostaFichaColeta cloneFromFilter(ItemRespostaFichaColetaFilter filter) {
		return null;
	}
	
}
