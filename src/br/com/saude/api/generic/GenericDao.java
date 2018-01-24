package br.com.saude.api.generic;

import java.util.List;
import java.util.function.Function;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.javatuples.Pair;
import org.javatuples.Triplet;


public abstract class GenericDao<T> {
	
	protected Class<T> entityType;
	protected Function<T,T> functionLoad;
	protected Function<T,T> functionLoadAll;
	protected Function<Pair<T,Session>,T> functionBeforeSave;
	
	@SuppressWarnings("unchecked")
	protected GenericDao(){
		this.entityType = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
		this.initializeFunctions();
	}
	
	protected abstract void initializeFunctions();
	
	private Field getId(Class<?> entityClass) {
		try {
			return entityClass.getDeclaredField("id");
		} catch (NoSuchFieldException e) {
			return getId(entityClass.getSuperclass());
		}
	}
	
	public T save(T entity) throws Exception {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			if(this.functionBeforeSave != null)
				entity = this.functionBeforeSave.apply(new Pair<T,Session>(entity,session));
			
			Field id = getId(entity.getClass());
			id.setAccessible(true);
			id.set(entity, id.get(session.merge(entity)));
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
		
		return entity;
	}
	
	public List<T> saveList(List<T> entities) throws Exception {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			for (T t : entities) {
				if(this.functionBeforeSave != null)
					t = this.functionBeforeSave.apply(new Pair<T,Session>(t,session));
				
				Field id = getId(t.getClass());
				id.setAccessible(true);
				id.set(t, id.get(session.merge(t)));
			}
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
		
		return entities;
	}
	
	private long getCount(Session session, GenericExampleBuilder<?,?> exampleBuilder) {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(entityType);
		
		for(Criterion criterion : exampleBuilder.getCriterions())
			criteria.add(criterion);
		
		if(exampleBuilder.getCriterias() != null)
			for(Triplet<String,CriteriaExample,JoinType> c: exampleBuilder.getCriterias()) {
				Criteria example = criteria.createCriteria(c.getValue0(),c.getValue2());
				for(Criterion criterion : c.getValue1().getCriterions())
					example.add(criterion);
				example.add(c.getValue1().getExample());
			}
		
		criteria = finishCriteria(criteria,exampleBuilder);
		
		return (long)criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public void delete(Object id) {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			session.remove(session.get(this.entityType, (Serializable)id));
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
	
	public PagedList<T> getList(GenericExampleBuilder<?,?> exampleBuilder) throws Exception{
		return getList(exampleBuilder, null);
	}
	
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?,?> exampleBuilder) {
		return criteria;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected PagedList<T> getList(GenericExampleBuilder<?,?> exampleBuilder, Function<T,T> function) throws Exception {
		List<T> list;
		PagedList<T> pagedList = new PagedList<T>();
		List<Criterion> criterions = exampleBuilder.getCriterions();
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType);
			criteria.setFirstResult((exampleBuilder.getPageNumber() - 1) * exampleBuilder.getPageSize());
			criteria.setMaxResults(exampleBuilder.getPageSize());
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);
			
			criteria = loopCriterias(criteria, exampleBuilder.getCriterias());			
			criteria = finishCriteria(criteria,exampleBuilder);			
			list = criteria.list();
			
			if(function != null)
				for(T entity : list)
					entity = function.apply(entity); 			
			
			pagedList.setTotal(getCount(session, exampleBuilder));
			pagedList.setList(list);
			pagedList.setPageNumber(exampleBuilder.getPageNumber());
			pagedList.setPageSize(exampleBuilder.getPageSize());
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return pagedList;
	}
	
	private Criteria loopCriterias(Criteria criteria, List<Triplet<String,CriteriaExample,JoinType>> criterias) {
		if(criterias != null)
			for(Triplet<String,CriteriaExample,JoinType> c: criterias) {
				Criteria example = criteria.createCriteria(c.getValue0(),c.getValue2());
				for(Criterion criterion : c.getValue1().getCriterions())
					example.add(criterion);
				example.add(c.getValue1().getExample());
				
				example = loopCriterias(example, c.getValue1().getCriterias());
			}
		return criteria;
	}
	
	public T getFirst(List<Criterion> criterions) throws Exception{
		return getFirst(criterions, null);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected T getFirst(List<Criterion> criterions, Function<T,T> function) throws Exception {
		T entity;
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType);
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);					
			
			criteria = criteria.setMaxResults(1);
			entity = (T)criteria.uniqueResult();
			
			if(function != null)
				entity = function.apply(entity);	 			
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return entity;
	}
	
	public T getById(Object id) throws Exception {
		return getById(id, null);
	}
	
	protected T getById(Object id, Function<T,T> function) throws Exception {
		Session session = HibernateHelper.getSession();
		T entity;
		
		try {
			entity = session.get(this.entityType, (Serializable)id);
			
			if(function != null)
				entity = function.apply(entity);
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		return entity;
	}
}
