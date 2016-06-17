package br.edu.unirn.orm;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.utils.AbstractTest;

public class HibernateBootstrapTest extends AbstractTest {
	
	@Test
	public void testInicializacaoSucesso(){
		Assert.assertTrue( SessionFactoryHolder.getSessionFactory().isOpen() );		
		
	}
	
}
