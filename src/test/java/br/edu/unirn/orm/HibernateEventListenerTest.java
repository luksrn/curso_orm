package br.edu.unirn.orm;

import java.util.Date;

import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.utils.AbstractTest;

public class HibernateEventListenerTest extends AbstractTest {

	@Test
	public void testHibernateEventListener(){
		
		doInTransaction( session -> {			
			Artista artista = session.get(Artista.class, 1L);
			session.delete(artista);
		});
	}
	
	@Test
	public void testHibernateEventListenerUpdate(){
		
		doInTransaction( session -> {			
			Artista artista = session.get(Artista.class, 1L);
			artista.setNome("Nome Alterado!");
			artista.getDetalhes().setDataNascimento(new Date());
			session.update(artista);
		});
	}
}
