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
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			entity = (T)session.merge(entity);
			transaction.commit();
			
			if(beforeSessionCloseMethodName != null)
				entity = invokeMethod(entity, beforeSessionCloseMethodName);
		}catch(Exception ex) {
			throw ex;
		}finally {
			session.close();
		}
		
		return entity;
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
	
	public List<T> getList(List<Criterion> criterions) throws Exception{
		return getList(criterions, null);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<T> getList(List<Criterion> criterions, String beforeSessionCloseMethodName) throws Exception {
		List<T> list;
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType);
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);					
			
			list = criteria.list();
			
			if(beforeSessionCloseMethodName != null)
				for(T entity : list) {
					entity = invokeMethod(entity, beforeSessionCloseMethodName);	 			
				}
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return list;
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
				entity = invokeMethod(entity, beforeSessionCloseMethodName);	 			
			
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
	
	protected T getById(Object id, String beforeSessionCloseMethodName) throws Exception {
		Session session = HibernateHelper.getSession();
		T entity;
		
		try {
			entity = session.get(this.entityType, (Serializable)id);
			
			if(beforeSessionCloseMethodName != null)
				entity = invokeMethod(entity, beforeSessionCloseMethodName);
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings({ "unchecked"})
	private T invokeMethod(T entity, String methodName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = this.getClass().getDeclaredMethod(methodName, new Class[] {this.entityType});
		method.setAccessible(true);
		return (T)method.invoke(this,new Object[] {entity});	
	}
}
