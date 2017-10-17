package br.com.saude.api.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
	
	protected abstract void initializeFunctions();
	protected abstract T clone(T entity);
	
	public abstract T cloneFromFilter(F filter);
	
	private List<T> clone(List<T> entityList){
		List<T> list = new ArrayList<T>();
		
		for(T entity : entityList)
			list.add(clone(entity));
		
		return list;
	}
	
	protected GenericEntityBuilder<T,F> loadProperty(Function<Map<String,T>,T> function) {
		Map<String,T> map = new HashMap<String,T>();
		
		if(this.entity != null) {
			map.put("origem", this.entity);
			map.put("destino", this.newEntity);
			this.newEntity = function.apply(map);
		}else {
			for(T entity:this.entityList) {
				T newEntity = this.newEntityList.stream()
						.filter(e->{
							try {
								return e.getClass().getDeclaredField("id").get(e) == 
											entity.getClass().getDeclaredField("id").get(entity);
							} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
									| SecurityException e1) {
								e1.printStackTrace();
							}
							return false;
						})
						.iterator().next();
				map.put("origem", entity);
				map.put("destino", newEntity);
				newEntity = function.apply(map);
			}
		}
		
		return this;
	}
}
