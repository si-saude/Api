package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.po.Profissiograma;

public class ProfissiogramaExampleBuilder 
		extends GenericExampleBuilder<Profissiograma, ProfissiogramaFilter> {

	public static ProfissiogramaExampleBuilder newInstance(ProfissiogramaFilter filter) {
		return new ProfissiogramaExampleBuilder(filter);
	}
	
	private ProfissiogramaExampleBuilder(ProfissiogramaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addConcluido();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNome();
		addConcluido();
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addConcluido() {
		this.entity.setConcluido(this.addBoolean("concluido", this.filter.getConcluido()));
	}
}
