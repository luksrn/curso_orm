package br.edu.unirn.orm;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * 
 * @author lucas.oliveira
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractDAO<T,ID extends Serializable>  implements DAO<T, ID> {
	
	private SessionFactory sessionFactory = SessionFactoryHolder.getSessionFactory();
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
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
			if ( tx != null && (
						tx.getStatus() == TransactionStatus.ACTIVE || 
						tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) 
			){ tx.rollback(); }
			e.printStackTrace();
			throw e;
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
		} catch (Throwable e){
			if ( tx != null && (
					tx.getStatus() == TransactionStatus.ACTIVE || 
					tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) 
					){ 
				tx.rollback(); 
			}
			e.printStackTrace();
			throw e;
		}
	}
	
	public <R> R doWithSession( HibernateSessionFunction<R> funcao ){
		Session session = null;
		R resultado = null;
		try {
			session = sessionFactory.openSession();
			resultado = funcao.apply(session);
		} catch (RuntimeException e){
			e.printStackTrace();
			throw e;
		}
		return resultado;
	}
	
	public void doWithSession( HibernateSessionConsumer consumer ){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			consumer.accept(session);
		} catch (RuntimeException e){
			e.printStackTrace();
			throw e;
		}
	}
	

}
