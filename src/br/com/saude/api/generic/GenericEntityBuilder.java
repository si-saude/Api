package br.com.saude.api.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericEntityBuilder<T,F extends GenericFilter> {
	protected T entity;
	protected T newEntity;
	protected List<T> entityList;
	protected List<T> newEntityList;
	
	protected GenericEntityBuilder(T entity) {
		this.entity = entity;
		this.newEntity = clone(entity);
	}
	
	protected GenericEntityBuilder(List<T> entityList) {
		this.entityList = entityList;
		this.newEntityList = clone(entityList);
	}
	
	public T getEntity(){
		return this.newEntity;
	}
	
	public List<T> getEntityList(){
		return this.newEntityList;
	}
	
	protected abstract T clone(T entity);
	
	public abstract T cloneFromFilter(F filter);
	
	private List<T> clone(List<T> entityList){
		List<T> list = new ArrayList<T>();
		
		for(T entity : entityList)
			list.add(clone(entity));
		
		return list;
	}
}
