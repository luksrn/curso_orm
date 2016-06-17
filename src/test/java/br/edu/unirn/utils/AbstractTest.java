package br.edu.unirn.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;

import br.edu.unirn.orm.SessionFactoryHolder;

public class AbstractTest {

	protected static SessionFactory sessionFactory = SessionFactoryHolder.getSessionFactory();
	
	@Before
	public void inicializarSessionFactory(){
		SessionFactoryHolder.inicializarSessionFactory();
		sessionFactory = SessionFactoryHolder.getSessionFactory();
	}
	
	@After
	public void  finalizarSessionFactoy(){
		sessionFactory.close();
	}
	
	public <R> R doInTransaction( HibernateSessionFunction<R> funcao ){
		Session session = null;
		Transaction tx = null;
		R resultado = null;
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			tx.begin();			 
			resultado = funcao.apply(session);
			tx.commit();
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){ tx.rollback(); }
			throw e;
		} finally {
			if ( session != null && session.isOpen()){ session.close();}
		}
		return resultado;
	}
	
	public void doInTransaction( HibernateSessionConsumer consumer ){
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			tx.begin();			 
			consumer.accept(session);
			tx.commit();
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){ tx.rollback(); }
			throw e;
		} finally {
			if ( session != null && session.isOpen()){ session.close();}
		}
	}
	
	public <R> R doWithSession( HibernateSessionFunction<R> funcao ){
		Session session = null;
		R resultado = null;
		try {
			session = sessionFactory.openSession();
			resultado = funcao.apply(session);
		} finally {
			if ( session != null && session.isOpen()){ session.close();}
		}
		return resultado;
	}
	
	public void doWithSession( HibernateSessionConsumer consumer ){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			consumer.accept(session);
		} finally {
			if ( session != null && session.isOpen()){ session.close();}
		} 
	}


}
