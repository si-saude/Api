package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoFisicaFilter;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;

public class AvaliacaoFisicaExampleBuilder extends GenericExampleBuilder<AvaliacaoFisica, AvaliacaoFisicaFilter> {
	public static AvaliacaoFisicaExampleBuilder newInstance(AvaliacaoFisicaFilter filter) {
		return new AvaliacaoFisicaExampleBuilder(filter);
	}
	
	private AvaliacaoFisicaExampleBuilder(AvaliacaoFisicaFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addAtendimento();
		addAcaoIniciarExercicioFisico();
		addInteresseProgramaFisico();
		addPraticaExercicioFisico();
	}
	
	@Override
	protected void createExampleSelectList() { }

	
	private void addAtendimento() throws InstantiationException, IllegalAccessException {
		if(this.filter.getAtendimento()!=null) {
			CriteriaExample criteriaExample = AtendimentoExampleBuilder
					.newInstance(this.filter.getAtendimento()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("atendimento", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addPraticaExercicioFisico() {
		this.entity.setPraticaExercicioFisico(this.addBoolean("praticaExercicioFisico", this.filter.getPraticaExercicioFisico()));
	}
	
	protected void addInteresseProgramaFisico() {
		this.entity.setInteresseProgramaFisico(this.addBoolean("interesseProgramaFisico", this.filter.getInteresseProgramaFisico()));
	}
	
	protected void addAcaoIniciarExercicioFisico() {
		this.entity.setAcaoIniciarExercicioFisico(this.addBoolean("acaoIniciarExercicioFisico", this.filter.getAcaoRealizarExercicioFisico()));
	}
}
