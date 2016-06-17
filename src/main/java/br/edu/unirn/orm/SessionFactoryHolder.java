package br.edu.unirn.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.orm.dominio.ArtistaAtuacao;
import br.edu.unirn.orm.dominio.Atuacao;
import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.orm.dominio.Gravadora;
import br.edu.unirn.orm.dominio.Musica;

public class SessionFactoryHolder {
	
	private static SessionFactory sessionFactory;
	
	static {
		inicializarSessionFactory();
	}

	public static void inicializarSessionFactory(){
		
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
		
		sources.addAnnotatedClass( Artista.class )
			.addAnnotatedClass(Atuacao.class)
			.addAnnotatedClass(ArtistaAtuacao.class)
			.addAnnotatedClass(Genero.class)
			.addAnnotatedClass(Banda.class)
			.addAnnotatedClass(Gravadora.class)
			.addAnnotatedClass(CD.class)
			.addAnnotatedClass(Musica.class);
		
		 
		Metadata metadata = sources.buildMetadata();
		return metadata;
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
