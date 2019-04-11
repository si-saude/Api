package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ItemRefeicaoPlanoFilter;
import br.com.saude.api.model.entity.po.ItemRefeicaoPlano;

public class ItemRefeicaoPlanoBuilder extends GenericEntityBuilder<ItemRefeicaoPlano, ItemRefeicaoPlanoFilter> {
	private Function<Map<String,ItemRefeicaoPlano>,ItemRefeicaoPlano> loadAlimento;
	
	public static ItemRefeicaoPlanoBuilder newInstance(ItemRefeicaoPlano itemRefeicao) {
		return new ItemRefeicaoPlanoBuilder(itemRefeicao);
	}
	
	public static ItemRefeicaoPlanoBuilder newInstance(List<ItemRefeicaoPlano> itemRefeicaos) {
		return new ItemRefeicaoPlanoBuilder(itemRefeicaos);
	}
	
	private ItemRefeicaoPlanoBuilder(ItemRefeicaoPlano itemRefeicao) {
		super(itemRefeicao);
	}
	
	private ItemRefeicaoPlanoBuilder(List<ItemRefeicaoPlano> itemRefeicaos) {
		super(itemRefeicaos);
	}

	@Override
	protected void initializeFunctions() {
		this.loadAlimento = itens ->{
			if(itens.get("origem").getAlimento() != null) {
				itens.get("destino").setAlimento(
						AlimentoBuilder.newInstance(
								itens.get("origem").getAlimento()).loadNutricaoAlimentoMedidaAlimentar().getEntity());
			}
			return itens.get("destino");
		};
	}

	@Override
	protected ItemRefeicaoPlano clone(ItemRefeicaoPlano itemRefeicao) {
		ItemRefeicaoPlano cloneItemRefeicaoPlano = new ItemRefeicaoPlano();
		
		cloneItemRefeicaoPlano.setId(itemRefeicao.getId());
		cloneItemRefeicaoPlano.setVersion(itemRefeicao.getVersion());
		cloneItemRefeicaoPlano.setVe(itemRefeicao.getVe());
		cloneItemRefeicaoPlano.setQuantidade(itemRefeicao.getQuantidade());
		cloneItemRefeicaoPlano.setObservacao(itemRefeicao.getObservacao());
		
		if ( itemRefeicao.getAlimento() != null )
			cloneItemRefeicaoPlano.setAlimento(
					AlimentoBuilder.newInstance(itemRefeicao.getAlimento()).getEntity());
		if ( itemRefeicao.getAlimento() != null )
			cloneItemRefeicaoPlano.setMedidaCaseira(
					MedidaAlimentarBuilder.newInstance(itemRefeicao.getMedidaCaseira()).getEntity());
		
		return cloneItemRefeicaoPlano;
	}

	@Override
	public ItemRefeicaoPlano cloneFromFilter(ItemRefeicaoPlanoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ItemRefeicaoPlanoBuilder loadAlimento() {
		return (ItemRefeicaoPlanoBuilder) this.loadProperty(this.loadAlimento);
	}
}
