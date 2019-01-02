package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AtividadeFisicaFilter;
import br.com.saude.api.model.entity.po.AtividadeFisica;

public class AtividadeFisicaExampleBuilder extends GenericExampleBuilder<AtividadeFisica, AtividadeFisicaFilter> {
	public static AtividadeFisicaExampleBuilder newInstance(AtividadeFisicaFilter filter) {
		return new AtividadeFisicaExampleBuilder(filter);
	}
	
	private AtividadeFisicaExampleBuilder(AtividadeFisicaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDomingo();
		addSegunda();
		addTerca();
		addQuarta();
		addQuinta();
		addSexta();
		addSabado();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
	protected void addDomingo() {
		this.entity.setDomingo(this.addBoolean("domingo", this.filter.getDomingo()));
	}
	
	protected void addSegunda() {
		this.entity.setSegunda(this.addBoolean("segunda", this.filter.getSegunda()));
	}
	
	protected void addTerca() {
		this.entity.setTerca(this.addBoolean("terca", this.filter.getTerca()));
	}
	
	protected void addQuarta() {
		this.entity.setQuarta(this.addBoolean("quarta", this.filter.getQuarta()));
	}
	
	protected void addQuinta() {
		this.entity.setQuinta(this.addBoolean("quinta", this.filter.getQuinta()));
	}
	
	protected void addSexta() {
		this.entity.setSexta(this.addBoolean("sexta", this.filter.getSexta()));
	}
	
	protected void addSabado() {
		this.entity.setSabado(this.addBoolean("sabado", this.filter.getSabado()));
	}
}
