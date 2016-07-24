package br.edu.unirn.orm;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 * 
 * @author lucas.oliveira
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractHibernateDAO<T,ID extends Serializable> 
				implements DAO<T, ID> {
	
	private SessionFactory sessionFactory = SessionFactoryHolder.getSessionFactory();
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
}
