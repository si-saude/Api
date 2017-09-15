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
	
	public abstract List<Criterion> getExample();
	
//	public Criteria setPagination(Criteria criteria) {
//	int pageNumber = this.filter.getPageNumber();
//	int pageSize = this.filter.getPageSize();
//	
//	if(pageNumber == 0)
//		pageNumber = 1;
//	
//	if(pageSize == 0)
//		pageSize = 1;
//	
//	criteria = criteria.setFirstResult(pageNumber*pageSize);
//	
//	return criteria;
//}
}
