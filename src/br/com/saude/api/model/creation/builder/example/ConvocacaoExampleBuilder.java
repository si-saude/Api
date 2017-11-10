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
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addTipo() {
		if(this.filter.getTipo() != null)
			this.entity.setTipo(Helper.filterLike(this.filter.getTipo()));
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	private void addProfissiograma() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissiograma()!=null) {
			CriteriaExample criteriaExample = ProfissiogramaExampleBuilder
					.newInstance(this.filter.getProfissiograma()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissiograma", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
