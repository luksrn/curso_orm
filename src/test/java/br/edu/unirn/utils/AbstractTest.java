package br.edu.unirn.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
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
			if ( tx != null && (
						tx.getStatus() == TransactionStatus.ACTIVE || 
						tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) 
			){ tx.rollback(); }
			e.printStackTrace();
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
			if ( tx != null && (
					tx.getStatus() == TransactionStatus.ACTIVE || 
					tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) 
					){ 
				tx.rollback(); 
			}
			e.printStackTrace();
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
		} catch (RuntimeException e){
			e.printStackTrace();
			throw e;
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
		} catch (RuntimeException e){
			e.printStackTrace();
			throw e;
		} finally {
			if ( session != null && session.isOpen()){ session.close();}
		} 
	}

    protected void executeSync(VoidCallable callable) {
    	executeSync(Collections.singleton(callable));
	}
    
	protected void executeSync(Collection<VoidCallable> callables) {
        try {
            List<Future<Void>> futures = executorService.invokeAll(callables);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
	}
	
	private final ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
        Thread bob = new Thread(r);
        bob.setName("Bob");
        return bob;
});

}
