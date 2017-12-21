package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;

public class FeriadoExampleBuilder extends GenericExampleBuilder<Feriado,FeriadoFilter> {

	public static FeriadoExampleBuilder newInstance(FeriadoFilter filter) {
		return new FeriadoExampleBuilder(filter);
	}
	
	protected FeriadoExampleBuilder(FeriadoFilter filter) {
		super(filter);
	}
	
	private void addTitulo() {
		if ( this.filter.getTitulo() != null )
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	private void addData() {
		this.addData("data", this.filter.getData());
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addTitulo();
		addData();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}

	
	
}
