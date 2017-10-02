package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CurriculoFilter;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoExampleBuilder extends GenericExampleBuilder<Curriculo,CurriculoFilter> {

	public static CurriculoExampleBuilder newInstance(CurriculoFilter filter) {
		return new CurriculoExampleBuilder(filter);
	}
	
	private CurriculoExampleBuilder(CurriculoFilter filter) {
		super(filter);
	}
	
	private void addHistorico() {
		if(this.filter.getHistorico() != null)
			this.entity.setHistorico(Helper.filterLike(this.filter.getHistorico()));
	}
	
	private void addFormacao() {
		if(this.filter.getFormacao() != null)
			this.entity.setFormacao(Helper.filterLike(this.filter.getFormacao()));
	}
	
	private void addAtuacao() {
		if(this.filter.getAtuacao() != null)
			this.entity.setAtuacao(Helper.filterLike(this.filter.getAtuacao()));
	}

	@Override
	protected void createExample() {
		addHistorico();
		addFormacao();
		addAtuacao();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}