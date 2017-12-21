package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.po.Convocacao;

public class ConvocacaoExampleBuilder extends GenericExampleBuilder<Convocacao, ConvocacaoFilter> {

	public static ConvocacaoExampleBuilder newInstance(ConvocacaoFilter filter) {
		return new ConvocacaoExampleBuilder(filter);
	}
	
	private ConvocacaoExampleBuilder(ConvocacaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addTipo();
		addTitulo();
		addProfissiograma();
		addInicio();
		addFim();
	}

	protected void createExampleByTitulo() throws InstantiationException, IllegalAccessException {
		addTituloEq();
	}
	
	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	public GenericExampleBuilder<Convocacao, ConvocacaoFilter> exampleByTitulo()
			throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleByTitulo();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	private void addTipo() {
		if(this.filter.getTipo() != null && !this.filter.getTipo().equals(""))
			this.entity.setTipo(Helper.filterLike(this.filter.getTipo()));
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	private void addTituloEq() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(this.filter.getTitulo());
	}
	
	private void addProfissiograma() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissiograma()!=null) {
			CriteriaExample criteriaExample = ProfissiogramaExampleBuilder
					.newInstance(this.filter.getProfissiograma()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissiograma", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addInicio() {
		addData("inicio", this.filter.getInicio());
	}
	
	private void addFim() {
		addData("fim", this.filter.getFim());
	}
}
