package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RegraAtendimentoFilter;
import br.com.saude.api.model.entity.po.RegraAtendimento;

public class RegraAtendimentoExampleBuilder 
	extends GenericExampleBuilder<RegraAtendimento, RegraAtendimentoFilter> {

	public static RegraAtendimentoExampleBuilder newInstance(RegraAtendimentoFilter filter) {
		return new RegraAtendimentoExampleBuilder(filter);
	}
	
	private RegraAtendimentoExampleBuilder(RegraAtendimentoFilter filter) {
		super(filter);
	}
	

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
}
