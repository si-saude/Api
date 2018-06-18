package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AgenteRiscoFilter;
import br.com.saude.api.model.entity.po.AgenteRisco;

public class AgenteRiscoExampleBuilder extends GenericExampleBuilder<AgenteRisco, AgenteRiscoFilter> {

	public static AgenteRiscoExampleBuilder newInstance(AgenteRiscoFilter filter) {
		return new AgenteRiscoExampleBuilder(filter);
	}
	
	private AgenteRiscoExampleBuilder(AgenteRiscoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
		addCategoriaAgenteRisco();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addCategoriaAgenteRisco() {
		if(this.filter.getCategoriaAgenteRisco() != null)
			this.entity.setCategoriaAgenteRisco(Helper.filterLike(this.filter.getCategoriaAgenteRisco()));
	}

}
