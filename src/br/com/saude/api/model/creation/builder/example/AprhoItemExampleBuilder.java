package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoItemFilter;
import br.com.saude.api.model.entity.po.AprhoItem;

public class AprhoItemExampleBuilder
	extends GenericExampleBuilder<AprhoItem, AprhoItemFilter>{

	public static AprhoItemExampleBuilder newInstance(AprhoItemFilter filter) {
		return new AprhoItemExampleBuilder(filter);
	}
	
	private AprhoItemExampleBuilder(AprhoItemFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addAprho();		
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

}
