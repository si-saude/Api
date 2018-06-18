package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.po.AprhoEmpregado;


public class AprhoEmpregadoExampleBuilder
	extends GenericExampleBuilder<AprhoEmpregado, AprhoEmpregadoFilter>{

	public static AprhoEmpregadoExampleBuilder newInstance(AprhoEmpregadoFilter filter) {
		return new AprhoEmpregadoExampleBuilder(filter);
	}
	
	private AprhoEmpregadoExampleBuilder(AprhoEmpregadoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addAprho();				
		addEmpregado();	
		addAtual();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addAprho() throws InstantiationException, IllegalAccessException {
		if(this.filter.getAprho()!=null) {
			CriteriaExample criteriaExample = AprhoExampleBuilder
					.newInstance(this.filter.getAprho()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("aprho", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	protected void addAtual() {
		this.entity.setAtual(this.addBoolean("atual", this.filter.getAtual()));
	}
	

}
