package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;

public class RiscoPotencialExampleBuilder extends GenericExampleBuilder<RiscoPotencial, RiscoPotencialFilter>{

	public static RiscoPotencialExampleBuilder newInstance(RiscoPotencialFilter filter) {
		return new RiscoPotencialExampleBuilder(filter);
	}
	
	private RiscoPotencialExampleBuilder(RiscoPotencialFilter filter) {
		super(filter);
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if (this.filter.getEmpregado() != null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEquipeResponsavel() throws InstantiationException, IllegalAccessException {
		if (this.filter.getEquipeResponsavel() != null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipeResponsavel()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("equipeResponsavel", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addData() {
		this.addData("data", this.filter.getData());
	}
	
	private void addInicioAgendamento() {
		this.addData("inicioAgendamento", this.filter.getInicioAgendamento());
	}
	
	protected void addAtual() {
		this.entity.setAtual(this.addBoolean("atual", this.filter.getAtual()));
	}
	
	private void addFimAgendamento() {
		this.addData("fimAgendamento", this.filter.getFimAgendamento());
	}
	
	private void addStatus() {
		if(this.filter.getStatus()!= null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addEmpregado();
		addEquipeResponsavel();
		addData();
		addInicioAgendamento();
		addFimAgendamento();
		addAtual();
		addStatus();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
}
