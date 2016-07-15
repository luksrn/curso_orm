package br.edu.unirn.orm;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.utils.AbstractTest;

public class ConsultaNamedQueryTest extends AbstractTest {
	
	@Test
	public void findByNomeComNamedQuery(){
		
		doWithSession( session -> {
			Query query = session.createNamedQuery("findByNome");
			query.setParameter("nome", "%Floor%");
			
			Artista artista = (Artista)query.getSingleResult();
		
			Assert.assertTrue(artista.getId() == 12);
		});
	}
	
	@Test
	public void findByAtuacaoComNamedQuery(){
		doWithSession( session -> {
			
			TypedQuery<Artista> query = session.createNamedQuery(
										"findByAtuacao",Artista.class);
			
			query.setParameter("denominacao", "Vocals");
			
			List<Artista> artistas =  query.getResultList();
			
			Assert.assertTrue(artistas.size() == 5);
		});
	}

}
