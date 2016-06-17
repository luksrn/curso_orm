package br.edu.unirn.orm;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.orm.dominio.ArtistaAtuacao;
import br.edu.unirn.orm.dominio.Atuacao;
import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.utils.AbstractTest;
import br.edu.unirn.utils.DataHelper;

public class ManyToManyComAtributosExtrasTest extends AbstractTest  implements DataHelper {
	
	@Test
	public void testCreateComCriacaoManual(){
		
		doInTransaction( session -> {

			Banda lunatica = session.load(Banda.class, 3L);
			
			Atuacao bateria = session.load(Atuacao.class, 3L);
			
			Artista emilio = session.load(Artista.class, 20L);
			
			ArtistaAtuacao artistaAtuacao = 
					new ArtistaAtuacao(emilio,
							bateria,
							lunatica, 
							LocalDate.of(2001, 1, 1),
							LocalDate.of(2016, 1, 1));
			
			session.persist(artistaAtuacao);
		});
		 
	}
	
	
	@Test
	public void testCreateComHelperMethod(){
		
		doInTransaction( session -> {

			Banda nightwish = session.load(Banda.class, 1L);
			
			Atuacao vocal = session.load(Atuacao.class, 4L);
			
			Artista tarja = session.load(Artista.class, 7L);
			
			tarja.adicionarAtuacao(vocal, nightwish, 1996, 2005);
			
			session.persist(tarja); // Usa cascade
		});
		 
	}
	
	@Test
	public void testLeituraInformacoesDeMembros(){
		
		doWithSession( session -> {

			Banda night = session.load(Banda.class, 1L);
			
			List<String> artistas = night.getArtistasAtuacao()
										.stream()
										.map(a -> a.getArtista().getNome())
										.sorted()
										.collect(toList());
			 		
			assertTrue( artistas.size() == 5);
			assertEquals("Emppu Vourinen", artistas.get(0)); // vinculo A
			assertEquals("Emppu Vourinen", artistas.get(1)); // vinculo B
			assertEquals("Tarja Turunen", artistas.get(2));
			assertEquals("Tuomas Holopainen", artistas.get(3));
			assertEquals("Tuomas Holopainen", artistas.get(4));
			
		});
	}
	
	
	@Test
	public void testRemocaoRelacaoManyToManyEntreBandaArtista(){
		
		doInTransaction( session -> {

			Banda night = session.load(Banda.class, 1L);
			
			ArtistaAtuacao tarja = night.getArtistasAtuacao()
										.stream()
										.filter(a -> a.getNomeArtista().equals("Tarja Turunen"))
										.findFirst().orElseThrow(() -> new RuntimeException());
			 		
			// Remoção usando cascade orphanRemoval=true
			night.getArtistasAtuacao().remove(tarja);
			session.persist(night);
		});
		

		doWithSession( session -> {
			Banda night = session.get(Banda.class, 1L);
			
			Optional<ArtistaAtuacao> atuacaoBanda = night.getArtistasAtuacao()
										.stream()
										.filter(a -> a.getNomeArtista().equals("Tarja Turunen"))
										.findFirst();
			
			Artista artistaTarja = session.get(Artista.class, 7L);
			
			assertTrue( atuacaoBanda.isPresent() == false );
			assertTrue( artistaTarja.getId() == 7L ); // Registro existe!
			
		});
	}
	
	@Test
	public void testRemoverArtistaComRelacaoComBanda(){
		doInTransaction( session -> {
			Artista artistaTarja = session.get(Artista.class, 7L);
			session.remove(artistaTarja);
		});
	}

}
