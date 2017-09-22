package br.com.saude.api.generic;

import java.util.List;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;


public abstract class GenericDao<T> {
	
	protected Class<T> entityType;
	
	@SuppressWarnings("unchecked")
	protected GenericDao(){
		this.entityType = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
	}
	
	public T save(T entity) throws Exception {
		return save(entity,null);
	}
	
	@SuppressWarnings("unchecked")
	protected T save(T entity, String beforeSessionCloseMethodName) throws Exception {
		T entityReturn = null;
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			entityReturn = (T)session.merge(entity);
			
			if(beforeSessionCloseMethodName != null)
				entityReturn = (T)invokeMethod(new Object[]{entity,session}, beforeSessionCloseMethodName, new Class[] {this.entityType,Session.class});
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			session.close();
		}
		
		return entityReturn;
	}
	
	private long getCount(Session session, List<Criterion> criterions) {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(entityType);
		
		for(Criterion criterion : criterions)
			criteria.add(criterion);		
		
		return (long)criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public void delete(Object id) {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			StringBuilder queryBuilder = new StringBuilder("DELETE ");
			queryBuilder.append(this.entityType.getSimpleName());
			queryBuilder.append(" WHERE id = :id");
			
			session.createQuery(queryBuilder.toString()).setParameter("id", id).executeUpdate();
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			session.close();
		}
	}
	
	public PagedList<T> getList(GenericExampleBuilder<?,?> exampleBuilder) throws Exception{
		return getList(exampleBuilder, null);
	}
	
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?,?> exampleBuilder) {
		return criteria;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected PagedList<T> getList(GenericExampleBuilder<?,?> exampleBuilder, String beforeSessionCloseMethodName) throws Exception {
		List<T> list;
		PagedList<T> pagedList = new PagedList<T>();
		List<Criterion> criterions = exampleBuilder.getCriterions();
		List<Triplet<String,CriteriaExample,JoinType>> criterias = exampleBuilder.getCriterias();
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType);
			criteria.setFirstResult((exampleBuilder.getPageNumber() - 1) * exampleBuilder.getPageSize());
			criteria.setMaxResults(exampleBuilder.getPageSize());
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);
			
			if(criterias != null)
				for(Triplet<String,CriteriaExample,JoinType> c: criterias) {
					Criteria example = criteria.createCriteria(c.getValue0(),c.getValue2());
					for(Criterion criterion : c.getValue1().getCriterions())
						example.add(criterion);
				}
			
			criteria = finishCriteria(criteria,exampleBuilder);
			list = criteria.list();
			
			if(beforeSessionCloseMethodName != null)
				for(T entity : list) {
					entity = (T)invokeMethod(new Object[]{entity}, beforeSessionCloseMethodName, new Class[] {this.entityType});	 			
				}
			
			pagedList.setTotal(getCount(session, criterions));
			pagedList.setList(list);
			pagedList.setPageNumber(exampleBuilder.getPageNumber());
			pagedList.setPageSize(exampleBuilder.getPageSize());
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return pagedList;
	}
	
	public T getFirst(List<Criterion> criterions) throws Exception{
		return getFirst(criterions, null);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected T getFirst(List<Criterion> criterions, String beforeSessionCloseMethodName) throws Exception {
		T entity;
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType);
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);					
			
			criteria = criteria.setMaxResults(1);
			entity = (T)criteria.uniqueResult();
			
			if(beforeSessionCloseMethodName != null)
				entity = (T)invokeMethod(new Object[]{entity}, beforeSessionCloseMethodName, new Class[] {this.entityType});	 			
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return entity;
	}
	
	public T getById(Object id) throws Exception {
		return getById(id, null);
	}
	
	@SuppressWarnings("unchecked")
	protected T getById(Object id, String beforeSessionCloseMethodName) throws Exception {
		Session session = HibernateHelper.getSession();
		T entity;
		
		try {
			entity = session.get(this.entityType, (Serializable)id);
			
			if(beforeSessionCloseMethodName != null)
				entity = (T)invokeMethod(new Object[]{entity}, beforeSessionCloseMethodName, new Class[] {this.entityType});
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private Object invokeMethod(Object[] objects, String methodName, Class[] types) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = this.getClass().getDeclaredMethod(methodName,types);
		method.setAccessible(true);
		return (T)method.invoke(this,objects);	
	}
}
