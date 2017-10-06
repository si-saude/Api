package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public abstract class IndicadorRiscoExampleBuilder<T extends IndicadorRisco> 
											extends GenericExampleBuilder<T,IndicadorRiscoFilter> {

	protected IndicadorRiscoExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
	
	protected void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	protected void addIndice0() {
		if(this.filter.getIndice0() != null)
			this.entity.setIndice0(Helper.filterLike(this.filter.getIndice0()));
	}
	
	protected void addIndice1() {
		if(this.filter.getIndice1() != null)
			this.entity.setIndice1(Helper.filterLike(this.filter.getIndice1()));
	}
	
	protected void addIndice2() {
		if(this.filter.getIndice2() != null)
			this.entity.setIndice2(Helper.filterLike(this.filter.getIndice2()));
	}
	
	protected void addIndice3() {
		if(this.filter.getIndice3() != null)
			this.entity.setIndice3(Helper.filterLike(this.filter.getIndice3()));
	}
	
	protected void addIndice4() {
		if(this.filter.getIndice4() != null)
			this.entity.setIndice4(Helper.filterLike(this.filter.getIndice4()));
	}
	
	protected void addIndice5() {
		if(this.filter.getIndice5() != null)
			this.entity.setIndice5(Helper.filterLike(this.filter.getIndice5()));
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addIndice0();
		addIndice1();
		addIndice2();
		addIndice3();
		addIndice4();
		addIndice5();
	}

	@Override
	protected void createExampleSelectList() {
		
	}

}
