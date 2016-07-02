package br.edu.unirn.orm;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.query.Query;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.orm.dominio.Musica;
import br.edu.unirn.utils.AbstractTest;

public class ConsultaHqlTest extends AbstractTest {

	private List<String> getDenominacoes(List<Genero> generos){
		return generos.stream()
				.map(Genero::getDenominacao)
				.collect(toList());
	}
	@Test
	public void testConsultaHql_RecuperarTodosOsGeneros(){
		doWithSession( session -> {
			//Query query = session.createQuery("from Genero");
			Query<Genero> query = session.createQuery("from Genero",Genero.class);
			List<Genero> generos = query.getResultList();
			
			List<String> denominacoes = generos.stream()
							.map(Genero::getDenominacao)
							.collect(toList());
			
			assertTrue( denominacoes.size() == 8 );
			assertThat(denominacoes, contains("Symphonic Power Metal",
												"Gothic Metal",
												"Melodic Death",
												"Power Metal",
												"Heavy",
												"MPB",
												"Rock",
												"Death Metal"));	
		});
	}
	
	@Test
	public void testConsultaDeGeneroMetal(){
		
		doWithSession( session -> {
			
			List<Genero> generosMetal = session.createQuery("select g from Genero g "
					+ " where g.denominacao like ?",Genero.class)
						.setParameter(0, "%Metal%")
						.getResultList();
			
			List<String> denominacoes = getDenominacoes(generosMetal);
			
			assertTrue( denominacoes.size() == 4 );
			assertThat(denominacoes, contains("Symphonic Power Metal",
												"Gothic Metal",
												"Power Metal",
												"Death Metal"));
			
		});
		
		
		doWithSession( session -> {
			
			List<Genero> generosMetal = session.createQuery(
					"select g from Genero g "
					+ " where g.denominacao like :denominacao",Genero.class)
						.setParameter("denominacao", "%Metal%")
						.getResultList();
			
			List<String> denominacoes = getDenominacoes(generosMetal);
			
			assertTrue( denominacoes.size() == 4 );
			assertThat(denominacoes, contains("Symphonic Power Metal",
												"Gothic Metal",
												"Power Metal",
												"Death Metal"));
			
		});
	}
	
	@Test
	public void testHqlComJoin_BandasPorGenero(){
		
		doWithSession( session -> {
			List<Banda> bandas = session.createQuery(
							"select b from Banda b " +
							" join b.generos g " +
							"where g.denominacao = :genero",Banda.class)
							.setParameter("genero", "Power Metal")
							.getResultList();
			
			assertTrue(bandas.size() == 1);
			Banda banda = bandas.get(0);
			assertTrue(banda.getDenominacao().equals("Nightwish"));
			assertTrue(banda.getGeneros().size() == 2);
		});
		
		// Com fetch
		
		doWithSession( session -> {
			List<Banda> bandas = session.createQuery(
							"select b from Banda b " +
							" join fetch b.generos g " +
							"where g.denominacao = :genero",Banda.class)
							.setParameter("genero", "Power Metal")
							.getResultList();
			
			assertTrue(bandas.size() == 1);
			Banda banda = bandas.get(0);
			assertTrue(banda.getDenominacao().equals("Nightwish"));
			// Só recupera o genero pelo where
			assertTrue(banda.getGeneros().size() == 1);
		});
	}
	
	@Test
	public void testAgregacaoAlbunsComNumeroMusicaEDuracao(){
		
		doWithSession( session -> {
			
			Banda lunatica = session.load(Banda.class, 3L);
			
			List<Object[]> resultado = session.createQuery(
					"select cd, count(musica), sum(musica.duracao) from Banda b "
					+ " join b.albuns cd"
					+ " join cd.musicas musica"
					+ " where b = :banda"
					+ " group by cd", Object[].class)
				.setParameter("banda", lunatica)
				.getResultList();
			
			assertTrue( resultado.get(0)[0] instanceof CD );
			assertTrue( resultado.get(0)[1] instanceof Long );
			assertTrue( resultado.get(0)[2] instanceof Long );
			
			assertTrue( resultado.size() == 2);
			
			// Informações do CD 01
			assertTrue( ((CD)resultado.get(0)[0]).getTitulo()
							.equals("The Edge of Infinity"));
			// Número de faixas
			assertTrue( ((Long)resultado.get(0)[1]) == 10);
			// Duração do CD
			assertTrue( ((Long)resultado.get(0)[2]) == 43);
			
			// CD 2
			assertTrue( ((CD)resultado.get(1)[0]).getTitulo()
					.equals("New Shores"));
			assertTrue( ((Long)resultado.get(1)[1]) == 10);
			// Duração do CD
			assertTrue( ((Long)resultado.get(1)[2]) == 44);
	
		});
	}
	
	
	//wrapping the values in a type-safe java
	@Test
	public void testAgregacaoAlbunsComNumeroMusicaEDuracaoWrappingTypeSafeJavaObject(){
		
		doWithSession( session -> {
			
			Banda lunatica = session.load(Banda.class, 3L);
			
			List<EstatisticasBanda> resultado = session.createQuery(
					"select "
					+ "new br.edu.unirn.orm.EstatisticasBanda (cd.titulo,"
					+ " count(musica),"
					+ " sum(musica.duracao)) "
					+ " from Banda b "
					+ " join b.albuns cd"
					+ " join cd.musicas musica"
					+ " where b = :banda"
					+ " group by cd", EstatisticasBanda.class)
				.setParameter("banda", lunatica)
				.getResultList();
			 
			assertTrue( resultado.size() == 2);
			
			// Informações do CD 01
			EstatisticasBanda estatisticasCD1 = resultado.get(0);
			
			assertTrue( estatisticasCD1.getAlbum()
							.equals("The Edge of Infinity"));
			// Número de faixas
			assertTrue( estatisticasCD1.getQuantidadeDeMusicas() == 10);
			// Duração do CD
			assertTrue( estatisticasCD1.getDuracaoDoAlbum() == 43);
			
			// CD 2
			assertTrue( resultado.get(1).getAlbum()
					.equals("New Shores"));
			assertTrue( resultado.get(1).getQuantidadeDeMusicas() == 10);
			// Duração do CD
			assertTrue( resultado.get(1).getDuracaoDoAlbum() == 44);
	
		});
	}
	
	@Test
	public void testConsultaComResultadoUnico(){
		
		doWithSession( session -> {
			
			Banda banda = session.createQuery("from Banda where denominacao = :denominacao", Banda.class)
				.setParameter("denominacao", "Lunatica")
				.getSingleResult();
			
			assertTrue( banda.getId() == 3L);
		});
	}
	
	@Test(expected=NonUniqueResultException.class)
	public void testConsultaComResultadoUnicoNonUniqueResultException(){
		
		doWithSession( session -> {
			
			Banda banda = session.createQuery("from Banda where denominacao like :denominacao", Banda.class)
				.setParameter("denominacao", "%a%")
				.getSingleResult();
			
			assertTrue( banda.getId() == 3L);
		});
	}
	
	@Test
	public void testConsultaComPaginacao(){
		
		final Integer max= 5;
		final Integer offset = 10;
		
		doWithSession( session -> {
			List<Musica> musicas = session.createQuery(
					"from Musica",Musica.class)
					.setFirstResult(offset)
					.setMaxResults(max)
					.getResultList();
			
			assertTrue( musicas.size() == 5 );
			assertTrue( musicas.get(0).getId() == 11L);
			assertTrue( musicas.get(4).getId() == 15L);
		});
	}
	
	@Test
	public void testUpdateComCreateQuery(){
		
		doInTransaction( session -> {
			int numeroRegistrosAfetados = session.createQuery(
					"update Gravadora "
					+ "set nome = :novoNome "
					+ "where nome = :nomeAntigo")
			.setParameter("novoNome", "Independente")
			.setParameter("nomeAntigo", "Independete")
			.executeUpdate();
			
			assertTrue( numeroRegistrosAfetados == 1);
			
		});
	}
	
	
	
	
	 
}
