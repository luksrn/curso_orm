package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.utils.AbstractTest;

public class EmbbededValuesTests extends AbstractTest {
	
	@Test
	public void testLeituraCampoEmbbeded() {
		
		doWithSession( session -> {
			Banda banda = session.get(Banda.class, 5L);
			
			assertTrue(banda.getPeriodoExistencia().getAnoInicio() == 2004 );
			assertTrue(banda.getPeriodoExistencia().getAnoFim() == null );
		});
	}

}
