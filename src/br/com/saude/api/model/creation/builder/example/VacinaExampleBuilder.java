package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.VacinaFilter;
import br.com.saude.api.model.entity.po.Vacina;

public class VacinaExampleBuilder extends GenericExampleBuilder<Vacina,VacinaFilter> {

	public static VacinaExampleBuilder newInstance(VacinaFilter filter) {
		return new VacinaExampleBuilder(filter);
	}
	
	private VacinaExampleBuilder(VacinaFilter filter) {
		super(filter);
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addDoses() {
		if(this.filter.getDoses() > 0)
			this.entity.setDoses(this.filter.getDoses());
	}
	
	private void addReforco() {
		if(this.filter.getReforco() > 0)
			this.entity.setReforco(this.filter.getReforco());
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
		addDoses();
		addReforco();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
