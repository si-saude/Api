package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.QuestionarioConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;

public class QuestionarioConhecimentoAlimentarExampleBuilder extends 
	GenericExampleBuilder<QuestionarioConhecimentoAlimentar,QuestionarioConhecimentoAlimentarFilter> {
	
	public static QuestionarioConhecimentoAlimentarExampleBuilder newInstance(QuestionarioConhecimentoAlimentarFilter filter) {
		return new QuestionarioConhecimentoAlimentarExampleBuilder(filter);
	}
	
	protected QuestionarioConhecimentoAlimentarExampleBuilder(QuestionarioConhecimentoAlimentarFilter filter) {
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
