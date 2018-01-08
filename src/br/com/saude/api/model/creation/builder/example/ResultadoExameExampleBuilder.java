package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameExampleBuilder extends GenericExampleBuilder<ResultadoExame, ResultadoExameFilter> {

	public static ResultadoExameExampleBuilder newInstance(ResultadoExameFilter filter) {
		return new ResultadoExameExampleBuilder(filter);
	}
	
	protected ResultadoExameExampleBuilder(ResultadoExameFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpregadoConvocacao();
		addExame();
		addData();
		addConforme();
		addTipo();
		addLocal();
		addAcao();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addExame() throws InstantiationException, IllegalAccessException {
		if (this.filter.getExame() != null) {
			CriteriaExample criteriaExample = ExameExampleBuilder
					.newInstance(this.filter.getExame()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("exame", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addData() {
		this.addData("data", this.filter.getData());
	}
	
	private void addConforme() {
		this.entity.setConforme(this.addBoolean("conforme", this.filter.getConforme()));
	}
	
	private void addTipo() {
		if ( this.filter.getTipo() != null ) {
			this.entity.setTipo(Helper.filterLike(this.filter.getTipo()));
		}
	}
	
	private void addAcao() {
		if ( this.filter.getAcao() != null ) {
			this.entity.setAcao(Helper.filterLike(this.filter.getAcao()));
		}
	}
	
	private void addLocal() {
		if ( this.filter.getLocal() != null ) {
			this.entity.setLocal(Helper.filterLike(this.filter.getLocal()));
		}
	}
	
	private void addEmpregadoConvocacao() throws InstantiationException, IllegalAccessException {
		if ( this.filter.getEmpregadoConvocacao() != null ) {
			CriteriaExample criteriaExample = EmpregadoConvocacaoExampleBuilder
					.newInstance(this.filter.getEmpregadoConvocacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("empregadoConvocacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
}
