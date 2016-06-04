package br.edu.unirn.orm;

import org.hibernate.SessionFactory;

public class LegacyHibernateBootstrap {
	
	private static SessionFactory sessionFactory;
	
	static {
		inicializarSessionFactory();
	}

	private static void inicializarSessionFactory() {
		
		
	}
	

	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public void shutdown(){
		sessionFactory.close();
	}
	
	

}
