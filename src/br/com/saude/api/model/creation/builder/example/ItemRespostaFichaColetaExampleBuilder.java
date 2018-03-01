package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ItemRespostaFichaColetaFilter;
import br.com.saude.api.model.entity.po.ItemRespostaFichaColeta;

public class ItemRespostaFichaColetaExampleBuilder 
	extends GenericExampleBuilder<ItemRespostaFichaColeta,ItemRespostaFichaColetaFilter> {

	public static ItemRespostaFichaColetaExampleBuilder newInstance(ItemRespostaFichaColetaFilter filter) {
		return new ItemRespostaFichaColetaExampleBuilder(filter);
	}
	
	private ItemRespostaFichaColetaExampleBuilder(ItemRespostaFichaColetaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addConteudo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {}
	
	private void addConteudo() {
		if(this.filter.getConteudo() != null)
			this.entity.setConteudo(Helper.filterLike(this.filter.getConteudo()));
	}
}