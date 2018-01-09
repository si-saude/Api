package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.po.Atendimento;

public class AtendimentoExampleBuilder extends GenericExampleBuilder<Atendimento, AtendimentoFilter> {

	public static AtendimentoExampleBuilder newInstance(AtendimentoFilter filter) {
		return new AtendimentoExampleBuilder(filter);
	}
	
	private AtendimentoExampleBuilder(AtendimentoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addTarefa();
		addFilaEsperaOcupacional();
		addFilaAtendimentoOcupacional();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addFilaEsperaOcupacional() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFilaEsperaOcupacional()!=null) {
			CriteriaExample criteriaExample = FilaEsperaOcupacionalExampleBuilder
					.newInstance(this.filter.getFilaEsperaOcupacional()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("filaEsperaOcupacional", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addFilaAtendimentoOcupacional() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFilaAtendimentoOcupacional()!=null) {
			CriteriaExample criteriaExample = FilaAtendimentoOcupacionalExampleBuilder
					.newInstance(this.filter.getFilaAtendimentoOcupacional()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("filaAtendimentoOcupacional", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addTarefa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getTarefa()!=null) {
			CriteriaExample criteriaExample = TarefaExampleBuilder
					.newInstance(this.filter.getTarefa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tarefa", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
