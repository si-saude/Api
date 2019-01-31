package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.PlanoAlimentarFilter;
import br.com.saude.api.model.entity.po.PlanoAlimentar;

public class PlanoAlimentarExampleBuilder  extends GenericExampleBuilder<PlanoAlimentar,PlanoAlimentarFilter> {
	public static PlanoAlimentarExampleBuilder newInstance(PlanoAlimentarFilter filter) {
		return new PlanoAlimentarExampleBuilder(filter);
	}
	
	protected PlanoAlimentarExampleBuilder(PlanoAlimentarFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addAtendimento();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addAtendimento();
	}
	
	private void addAtendimento() throws InstantiationException, IllegalAccessException {
		if(this.filter.getAtendimento()!=null) {
			CriteriaExample criteriaExample = AtendimentoExampleBuilder
					.newInstance(this.filter.getAtendimento()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("atendimento", criteriaExample, JoinType.INNER_JOIN));
		}
	}

}
