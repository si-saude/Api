package br.com.saude.api.generic;

import java.util.List;

public abstract class GenericEntityBuilder<T, ConcreteClass> {
	protected T entity;
	protected T newEntity;
	protected List<T> entityList;
	protected List<T> newEntityList;
	
	protected GenericEntityBuilder(T entity) {
		this.entity = entity;
	}
	
	protected GenericEntityBuilder(List<T> entityList) {
		this.entityList = entityList;
	}
	
	public T getEntity(){
		return this.newEntity;
	}
	
	public List<T> getEntityList(){
		return this.newEntityList;
	}
	
	protected abstract T clone(T entity);
	
	protected abstract List<T> clone(List<T> entityList);
	
	@SuppressWarnings("unchecked")
	public ConcreteClass clone(){
		
		if(this.entity != null)
			this.newEntity = clone(this.entity);
		
		if(this.entityList != null)
			this.newEntityList = clone(this.entityList);
		
		return (ConcreteClass)this;
	}
}
