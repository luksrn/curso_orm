package br.edu.unirn.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
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
import br.edu.unirn.orm.dominio.Review;
import br.edu.unirn.orm.dominio.ReviewBanda;
import br.edu.unirn.orm.dominio.ReviewCD;
import br.edu.unirn.orm.hibernate.GeneroSexualType;
import br.edu.unirn.orm.hibernate.GeneroSexualTypeDescriptor;

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
		MetadataSources metadataSources = new MetadataSources(sr);
		
		metadataSources.addAnnotatedClass( Artista.class )
			.addAnnotatedClass(Atuacao.class)
			.addAnnotatedClass(ArtistaAtuacao.class)
			.addAnnotatedClass(Genero.class)
			.addAnnotatedClass(Banda.class)
			.addAnnotatedClass(Gravadora.class)
			.addAnnotatedClass(CD.class)
			.addAnnotatedClass(Musica.class)
			.addAnnotatedClass(Review.class)
			.addAnnotatedClass(ReviewCD.class)
			.addAnnotatedClass(ReviewBanda.class);
		
		// define outras configurações 
		MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		
		metadataBuilder.applyBasicType(GeneroSexualType.INSTANCE, 
										GeneroSexualType.INSTANCE.getName());

		Metadata metadata = metadataBuilder.build();
		
		return metadata;
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
