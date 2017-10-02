package br.com.saude.api.generic;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

public abstract class GenericExampleBuilder<T,F extends GenericFilter> {
	protected T entity;
	protected F filter;
	protected List<Criterion> criterions;
	protected List<Triplet<String,CriteriaExample,JoinType>> criterias;
	
	protected GenericExampleBuilder(F filter) {
		this.filter = filter;
	}
	
	public GenericExampleBuilder<T, F> example() throws InstantiationException, IllegalAccessException{
		if(this.filter!=null) {
			initialize();
			createExample();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	public GenericExampleBuilder<T, F> exampleSelectList() throws InstantiationException, IllegalAccessException{
		if(this.filter!=null) {
			initialize();
			createExampleSelectList();
			this.filter.setPageNumber(1);
			this.filter.setPageSize(Integer.MAX_VALUE);
			this.criterions.add(getExample());
		}
		return this;
	}
	
	protected abstract void createExample() throws InstantiationException, IllegalAccessException;
	
	protected abstract void createExampleSelectList();
	
	@SuppressWarnings("unchecked")
	protected void initialize() throws InstantiationException, IllegalAccessException {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = (((Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[0])).newInstance();
	}
	
	protected Example getExample() {
		return Example.create(this.entity).enableLike().ignoreCase().excludeZeroes();
	}

	public List<Criterion> getCriterions() {
		return criterions;
	}
	
	public List<Triplet<String,CriteriaExample,JoinType>> getCriterias() {
		return criterias;
	}
	
	public int getPageNumber() {
		return this.filter != null ?
				this.filter.getPageNumber()
				: 0;
	}
	
	public int getPageSize() {
		return this.filter != null ?
				this.filter.getPageSize()
				: 0;
	}
	
	public CriteriaExample getCriteriaExample() throws InstantiationException, IllegalAccessException {
		if(this.filter != null) {
			CriteriaExample criteriaExample = new CriteriaExample();
			initialize();
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
