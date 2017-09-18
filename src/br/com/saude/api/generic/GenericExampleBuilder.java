package br.com.saude.api.generic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public abstract class GenericExampleBuilder<T,F> {
	protected T entity;
	protected F filter;
	protected List<Criterion> criterions;
	
	protected GenericExampleBuilder(F filter) {
		this.filter = filter;
	}
	
	public Criteria setPagination(Criteria criteria) {
		return criteria;
	}
	
	public abstract GenericExampleBuilder<?, ?> example();

	public List<Criterion> getCriterions() {
		return criterions;
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
}
