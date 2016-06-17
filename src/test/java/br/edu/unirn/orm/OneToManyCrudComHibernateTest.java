package br.edu.unirn.orm;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.Musica;
import br.edu.unirn.utils.AbstractTest;
/**
 * Exemplo de operações one to many
 * 
 * @author Lucas Farias
 *
 */
public class OneToManyCrudComHibernateTest extends AbstractTest {
	
	@Test
	public void testLeituraDeOneToMany(){
		
		doWithSession( session -> {
			CD theEdgeOfInfinity = session.get(CD.class, 10L);
			
			assertTrue(theEdgeOfInfinity.getMusicas().size()==10);
		});
	}
	
	@Test
	public void testCadastroAdicionandoMusicaAoCd(){
		doInTransaction(session -> {
			CD theEdgeOfInfinity = session.get(CD.class, 10L);
			
			Musica musica = new Musica();
			musica.setNumero(11);
			musica.setTitulo("Soundtrack");
			musica.setDuracao(Duration.ofMinutes(4));
			musica.setAlbum(theEdgeOfInfinity);
			
			theEdgeOfInfinity.getMusicas().add(musica);
			
			//session.persist(theEdgeOfInfinity);
			session.save(musica);
		});
		
		doWithSession( session -> {
			assertTrue(session.get(CD.class, 10L).getMusicas().size() == 11 );
		});
	}

}
