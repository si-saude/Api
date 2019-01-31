package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemRefeicaoFilter;
import br.com.saude.api.model.entity.po.ItemRefeicao;

public class ItemRefeicaoBuilder extends GenericEntityBuilder<ItemRefeicao, ItemRefeicaoFilter> {
	private Function<Map<String,ItemRefeicao>,ItemRefeicao> loadAlimento;
	
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
		this.loadAlimento = itens ->{
			if(itens.get("origem").getAlimento() != null) {
				itens.get("destino").setAlimento(
						AlimentoBuilder.newInstance(
								itens.get("origem").getAlimento()).loadSubstituicoes().getEntity());
			}
			return itens.get("destino");
		};
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
	public ItemRefeicaoBuilder loadAlimento() {
		return (ItemRefeicaoBuilder) this.loadProperty(this.loadAlimento);
	}
}
