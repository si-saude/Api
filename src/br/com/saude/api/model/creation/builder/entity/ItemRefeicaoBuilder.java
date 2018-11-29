package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemRefeicaoFilter;
import br.com.saude.api.model.entity.po.ItemRefeicao;

public class ItemRefeicaoBuilder extends GenericEntityBuilder<ItemRefeicao, ItemRefeicaoFilter> {
	public static ItemRefeicaoBuilder newInstance(ItemRefeicao itemRefeicao) {
		return new ItemRefeicaoBuilder(itemRefeicao);
	}
	
	public static ItemRefeicaoBuilder newInstance(List<ItemRefeicao> itemRefeicaos) {
		return new ItemRefeicaoBuilder(itemRefeicaos);
	}
	
	private ItemRefeicaoBuilder(ItemRefeicao itemRefeicao) {
		super(itemRefeicao);
	}
	
	private ItemRefeicaoBuilder(List<ItemRefeicao> itemRefeicaos) {
		super(itemRefeicaos);
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ItemRefeicao clone(ItemRefeicao itemRefeicao) {
		ItemRefeicao cloneItemRefeicao = new ItemRefeicao();
		
		cloneItemRefeicao.setId(itemRefeicao.getId());
		cloneItemRefeicao.setVersion(itemRefeicao.getVersion());
		cloneItemRefeicao.setVe(itemRefeicao.getVe());
		cloneItemRefeicao.setQuantidade(itemRefeicao.getQuantidade());
		
		if ( itemRefeicao.getAlimento() != null )
			cloneItemRefeicao.setAlimento(
					AlimentoBuilder.newInstance(itemRefeicao.getAlimento()).getEntity());
		if ( itemRefeicao.getAlimento() != null )
			cloneItemRefeicao.setMedidaCaseira(
					MedidaAlimentarBuilder.newInstance(itemRefeicao.getMedidaCaseira()).getEntity());
		
		return cloneItemRefeicao;
	}

	@Override
	public ItemRefeicao cloneFromFilter(ItemRefeicaoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
