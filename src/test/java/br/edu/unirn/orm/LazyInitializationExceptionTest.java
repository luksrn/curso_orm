package br.edu.unirn.orm;

import org.hibernate.LazyInitializationException;
import org.junit.Test;

import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.utils.AbstractTest;

public class LazyInitializationExceptionTest extends AbstractTest {
	
	@Test(expected=LazyInitializationException.class)
	public void testLazyException(){
		
		CD cd = doWithSession( session -> {
			return session.get(CD.class,10L);			
		});
		
		cd.getMusicas().size();
	}

}
