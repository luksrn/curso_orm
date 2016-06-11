package br.edu.unirn.orm;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import br.edu.unirn.orm.dominio.Artista;

public class SessionFactoryHolder {
	
	private static SessionFactory sessionFactory;
	
	static {
		inicializarSessionFactory();
	}

	private static void inicializarSessionFactory(){
		
		ServiceRegistry serviceRegistry = construirServiceRegistry();
		
		Metadata metadata = construirMetaData(serviceRegistry);
		
		sessionFactory = metadata.buildSessionFactory();
	}

	/**
	 *  Para construir o {@link ServiceRegistry}, podemos utilizar:
	 *  
	 *  Para {@link BootstrapServiceRegistry} o builder {@link BootstrapServiceRegistryBuilder}.
	 *  
	 *  e para {@link StandardServiceRegistry} builder o {@link StandardServiceRegistryBuilder}
	 *  
	 * @return
	 */
	private static ServiceRegistry construirServiceRegistry(){
		
		ServiceRegistry standarServiceRegistry = 
				new StandardServiceRegistryBuilder()
					.configure()
					.build();
		
		return standarServiceRegistry;
	}
	
	private static Metadata construirMetaData(ServiceRegistry sr){
		MetadataSources sources = new MetadataSources(sr);
		
		sources.addAnnotatedClass( Artista.class );
		
		return sources.buildMetadata();
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
