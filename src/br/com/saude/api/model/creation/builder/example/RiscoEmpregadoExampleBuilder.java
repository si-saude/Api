package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;

public class RiscoEmpregadoExampleBuilder extends GenericExampleBuilder<RiscoEmpregado, RiscoEmpregadoFilter> {

	public static RiscoEmpregadoExampleBuilder newInstance(RiscoEmpregadoFilter filter) {
		return new RiscoEmpregadoExampleBuilder(filter);
	}
	
	private RiscoEmpregadoExampleBuilder(RiscoEmpregadoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addRiscoPotencial();
		addProfissional();
		addData();
		addStatus();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addData() {
		this.addData("data", this.filter.getData());
	}

	private void addRiscoPotencial() throws InstantiationException, IllegalAccessException {
		if (this.filter.getRiscoPotencial() != null) {
			CriteriaExample criteriaExample = RiscoPotencialExampleBuilder
					.newInstance(this.filter.getRiscoPotencial()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("riscoPotencial", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addProfissional() throws InstantiationException, IllegalAccessException {
		if (this.filter.getProfissional() != null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getProfissional()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("profissional", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addStatus() {
		if(this.filter.getStatus()!= null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
	}
}
