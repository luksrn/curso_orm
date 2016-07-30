package br.edu.unirn.orm;

import java.util.Collections;

import org.junit.Test;

import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.Musica;
import br.edu.unirn.utils.AbstractTest;

public class EntityGraphTest extends AbstractTest{

	@Test
	public void testFetchCdAndMusicas(){
		doInTransaction( session -> {
			CD cd = session.find(
				    CD.class,
				    11L,
				    Collections.singletonMap(
				        "javax.persistence.fetchgraph",
				        session.getEntityGraph( "cdComMusicas" )
				    ));
			
			for( Musica m : cd.getMusicas() ){
				System.out.println( m.getTitulo() );
			}
		});
	}
}
