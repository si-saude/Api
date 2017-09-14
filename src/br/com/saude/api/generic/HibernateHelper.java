package br.com.saude.api.generic;

import org.hibernate.Session;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateHelper {
	
	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null)
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");
			}catch (Throwable ex) {
				throw new ExceptionInInitializerError(ex);
			}
		return entityManagerFactory;
	}
	
	private static EntityManager getEntityManager() {
		if(entityManager == null || !entityManager.isOpen())
				entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}
	
	public static Session getSession() {
		return getEntityManager().unwrap(Session.class);
	}
}
