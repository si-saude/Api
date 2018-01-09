package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;

public class EmpregadoConvocacaoExampleBuilder
	extends GenericExampleBuilder<EmpregadoConvocacao, EmpregadoConvocacaoFilter>{

	public static EmpregadoConvocacaoExampleBuilder newInstance(EmpregadoConvocacaoFilter filter) {
		return new EmpregadoConvocacaoExampleBuilder(filter);
	}
	
	private EmpregadoConvocacaoExampleBuilder(EmpregadoConvocacaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpregado();
		addConvocacao();
		addAuditado();
		addConvocado();
		addResultadoAuditado();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addConvocacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getConvocacao()!=null) {
			CriteriaExample criteriaExample = ConvocacaoExampleBuilder
					.newInstance(this.filter.getConvocacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("convocacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addAuditado() {
		this.entity.setAuditado(this.addBoolean("auditado", this.filter.getAuditado()));
	}
	
	protected void addConvocado() {
		this.entity.setConvocado(this.addBoolean("convocado", this.filter.getConvocado()));
	}
	
	protected void addResultadoAuditado() {
		this.entity.setResultadoAuditado(this.addBoolean("resultadoAuditado", this.filter.getResultadoAuditado()));
	}
}
