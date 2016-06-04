package br.edu.unirn.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateBootstrap {
	
	private static SessionFactory sessionFactory;
	
	static {
		inicializarSessionFactory();
	}


	/**
	 * A inicialização Nativa do Hibernate 5 ocorre em duas fases:
	 * 
	 *  1 - A criação do ServiceRegistry
	 * 
	 *  2 - A criação do MetaData
	 *  
	 *  Com o MetaData é possível construir a SessionFactory.
	 */
	private static void inicializarSessionFactory(){
		
		ServiceRegistry serviceRegistry = construirServiceRegistry();
		
		Metadata metadata = construirMetaData(serviceRegistry);
		
		sessionFactory = metadata.buildSessionFactory();
	}

	/**
	 * 
	 *  Para{@link BootstrapServiceRegistry} pode ser construido utilizando o Builder {@link BootstrapServiceRegistryBuilder}.
	 *  
	 *  {@link StandardServiceRegistry} pode ser construido utilizando o {@link StandardServiceRegistryBuilder}
	 *  
	 * @return
	 */
	private static ServiceRegistry construirServiceRegistry(){
		
		ServiceRegistry standarServiceRegistry = 
				new StandardServiceRegistryBuilder()
					.configure() // Busca no root Classpath
					.build();
		
		return standarServiceRegistry;
	}
	
	/**
	 * A classe {@link Metadata} contém informações do modelo de dominio e seu mapeamento
	 * para o banco de dados! A primeira coisa que precisamos para construir o {@link Metadata}
	 * são as informações de mapeamento (Classes anotadas, hbm.xml, orm.xml).
	 * 
	 * Para este proposito, utilizamos a classe {@link MetadataSources}
	 * 
	 * @return
	 */
	private static Metadata construirMetaData(ServiceRegistry sr){
		MetadataSources sources = new MetadataSources(sr);
		return sources.buildMetadata();
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
