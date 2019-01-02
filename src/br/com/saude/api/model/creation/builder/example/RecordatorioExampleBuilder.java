package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.RecordatorioFilter;
import br.com.saude.api.model.entity.po.Recordatorio;

public class RecordatorioExampleBuilder  extends GenericExampleBuilder<Recordatorio,RecordatorioFilter> {
	public static RecordatorioExampleBuilder newInstance(RecordatorioFilter filter) {
		return new RecordatorioExampleBuilder(filter);
	}
	
	protected RecordatorioExampleBuilder(RecordatorioFilter filter) {
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
