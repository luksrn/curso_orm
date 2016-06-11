package br.edu.unirn.orm;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateBootstrapTest {

	protected static SessionFactory sessionFactory;
	
	@BeforeClass
	public static void inicializacao(){
		sessionFactory = SessionFactoryHolder.getSessionFactory();
	}

	@Test
	public void testInicializacaoSucesso(){
		Assert.assertTrue( sessionFactory.isOpen() );		
		
	}
	
	@AfterClass
	public static void finalizar(){
		sessionFactory.close();
	}
}
