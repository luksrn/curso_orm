package br.edu.unirn.orm;

import static org.junit.Assert.*;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.utils.AbstractTest;

@SuppressWarnings("all")
public class LoadVsGetTest extends AbstractTest {
	
	@Test
	public void testTipoRetorno(){
		
		doWithSession( session -> {
			Artista artista1 = session.get(Artista.class, 1L);
			
			Artista artista2 = session.load(Artista.class, 2L);
			 
			assertTrue(artista1.getClass() != artista2.getClass());
			assertTrue(artista1.getClass().getSimpleName().equals("Artista"));
			assertTrue(artista2.getClass().getSimpleName().startsWith("Artista_$$_jvst"));
		});
	}

}
