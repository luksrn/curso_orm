package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class JPAHibernateTest {
	
	@Test
	public void testSeSessionFactoryEhUmaEntityManagerFactory(){
		
		assertTrue(EntityManagerFactory.class
						.isAssignableFrom(SessionFactory.class));
	}
	
	@Test
	public void testSeSessionEhUmaEntityManager(){
		assertTrue(EntityManager.class
						.isAssignableFrom(Session.class));
		 
	}
}
