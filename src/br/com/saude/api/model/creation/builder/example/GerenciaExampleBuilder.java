package br.com.saude.api.model.creation.builder.example;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaExampleBuilder extends GenericExampleBuilder<Gerencia,GerenciaFilter> {

	public static GerenciaExampleBuilder newInstance(GerenciaFilter filter) {
		return new GerenciaExampleBuilder(filter);
	}
	
	private GerenciaExampleBuilder(GerenciaFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addGerente() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGerente()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getGerente()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("gerente", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addSecretario1() throws InstantiationException, IllegalAccessException {
		if(this.filter.getSecretario1()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getSecretario1()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("secretario1", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addSecretario2() throws InstantiationException, IllegalAccessException {
		if(this.filter.getSecretario2()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getSecretario2()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("secretario2", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addAusentePeriodico() {
		this.entity.setAusentePeriodico(this.addBoolean("ausentePeriodico", this.filter.getAusentePeriodico()));
	}
	
	public GerenciaExampleBuilder exampleNotIn(List<Integer> ids) throws InstantiationException, IllegalAccessException{
		if(this.filter!=null) {
			initialize();
			createExample();
			this.criterions.add(getExample());
			ids.forEach(id->this.criterions.add(Restrictions.ne("id", (int)id)));
		}
		return this;
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigo();
		addDescricao();
		addGerente();
		addSecretario1();
		addSecretario2();
		addAusentePeriodico();
	}
	
	@Override
	protected void createExampleSelectList() {
		addNeId();
		addAusentePeriodico();
	}
}
