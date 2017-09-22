package br.com.saude.api.generic;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

public abstract class GenericExampleBuilder<T,F> {
	protected T entity;
	protected F filter;
	protected List<Criterion> criterions;
	protected List<Triplet<String,CriteriaExample,JoinType>> criterias;
	
	protected GenericExampleBuilder(F filter) {
		this.filter = filter;
	}
	
	public GenericExampleBuilder<T, F> example(){
		if(this.filter!=null) {
			createExample();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	protected abstract void createExample();
	
	protected Example getExample() {
		return Example.create(this.entity).enableLike().ignoreCase();
	}

	public List<Criterion> getCriterions() {
		return criterions;
	}
	
	public List<Triplet<String,CriteriaExample,JoinType>> getCriterias() {
		return criterias;
	}
	
	public int getPageNumber() {
		return this.filter != null ?
				((GenericFilter)this.filter).getPageNumber()
				: 0;
	}
	
	public int getPageSize() {
		return this.filter != null ?
				((GenericFilter)this.filter).getPageSize()
				: 0;
	}
	
	public CriteriaExample getCriteriaExample() {
		if(this.filter != null) {
			CriteriaExample criteriaExample = new CriteriaExample();
			createExample();
			criteriaExample.setCriterions(this.criterions);
			criteriaExample.setExample(getExample());
			return criteriaExample;
		}
		return null;
	}

	public F getFilter() {
		return filter;
	}
}
