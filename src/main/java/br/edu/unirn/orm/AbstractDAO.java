package br.edu.unirn.orm;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	
	public <R> R doWithCurrentSession( HibernateSessionFunction<R> funcao ){
		Session session = sessionFactory.getCurrentSession();
		R resultado = funcao.apply(session);		
		return resultado;
	}
	
	public void doWithCurrentSession( HibernateSessionConsumer consumer ){		
		Session session = sessionFactory.getCurrentSession();
		consumer.accept(session);		
	}
}
