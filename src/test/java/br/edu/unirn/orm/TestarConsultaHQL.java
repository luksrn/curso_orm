package br.edu.unirn.orm;

import java.util.List;

import org.hibernate.query.Query;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.orm.dominio.Musica;
import br.edu.unirn.utils.AbstractTest;

public class TestarConsultaHQL extends AbstractTest {
	
	@Test
	public void testConsultaTodosOsGeneros(){
		
		doWithSession( session -> {
			
			Query<Genero> query = session.createQuery(
					"select g from Genero g", Genero.class);
			
			List<Genero> generos = query.getResultList();
			
			for ( Genero g : generos ){
				System.out.println( g.getDenominacao() );
			}
		});
	}
	
	
	@Test
	public void testarConsultaPassandoParametro(){
		
		doWithSession( session -> {
			
			List<Genero> generos = session.createQuery(
					"select g from Genero g "
					+ " where upper(g.denominacao) like :denominacao",Genero.class)
					.setParameter("denominacao", "%Metal%".toUpperCase())
					.getResultList();
			
			
			for ( Genero g : generos) {
				System.out.println( g.getDenominacao() );
			}
			
		});
		
		
		
		
	}
	
	@Test
	public void consultarBandaEGeneros(){
		
		doWithSession( session -> {
			
			Query<Banda> query = session.createQuery(
					"select b from Banda b"
					+ " join fetch b.generos", Banda.class);
			
			List<Banda> bandas = query.getResultList();
			
			for( Banda b : bandas ){
				System.out.println(
						"Nome da Banda: " + b.getDenominacao() );
				
				System.out.println("Generos:");
				
				for ( Genero g : b.getGeneros() ){
					System.out.println( g.getDenominacao() );
				}
			}
		});
	}
	
	@Test
	public void consultarBandaComFuncoesDeAgregacao(){
		
		doWithSession( session -> {
			
			// Banda exemplo (parametro)
			Banda banda = session.get(Banda.class, 3L);
			
			// Mostrar o CD, N Musicas e Duração do CD
			Query<Object[]> query = session.createQuery(
					"select cd, count(m), sum(m.duracao)  from CD cd "
					+ " join cd.musicas m "
					+ " join cd.banda b "
					+ " where b = :banda "
					+ " group by cd ", Object[].class);
			
			query.setParameter("banda", banda);
			
			List<Object[]> resultados = query.getResultList();
			
			for ( Object[] linha : resultados ){
				System.out.println("=======");
				System.out.println("CD = " + ((CD)linha[0]).getTitulo() );
				System.out.println(" Numero de Música: " + linha[1]);
				System.out.println(" Duração do CD: " + linha[2]);
			}
			
		});
	}
	
	
	
	@Test
	public void consultarBandaComFuncoesDeAgregacaoComAdaptador(){
		
		doWithSession( session -> {
			
			// Banda exemplo (parametro)
			Banda banda = session.get(Banda.class, 3L);
			
			// Mostrar o CD, N Musicas e Duração do CD
			Query<EstatisticaCD> query = session.createQuery(
					"select new br.edu.unirn.orm.EstatisticaCD"
					+ "	(cd.titulo, count(m), sum(m.duracao))  from CD cd "
					+ " join cd.musicas m "
					+ " join cd.banda b "
					+ " where b = :banda "
					+ " group by cd ", EstatisticaCD.class);
			
			query.setParameter("banda", banda);
			List<EstatisticaCD> resultados = query.getResultList();
			for ( EstatisticaCD linha : resultados ){
				System.out.println("=======");
				System.out.println("CD = " + linha.getAlbum() );
				System.out.println(" Numero de Música: " + linha.getQuantidadeDeMusicas());
				System.out.println(" Duração do CD: " + linha.getDuracaoDoAlbum());
			}
			
		});
	}
	
	
	@Test
	public void consultaComPaginacao(){
		
		
		Integer offset = 5;
		Integer max = 10;
		doWithSession( session -> {
			Query<Musica> query = session.createQuery(
					"from Musica",Musica.class);
			
			query.setFirstResult(offset);
			query.setMaxResults(max);
			
			List<Musica> musicas = query.getResultList();
			
			System.out.println("Tamanho = " + musicas.size() );
			/*
			for ( Musica m : musicas ){
				System.out.println( m.getTitulo() );
			}*/
			/*
			musicas.forEach( item -> {
				System.out.println("--" + item.getTitulo());
			});*/
			
			
			/*musicas.forEach( item -> 
				System.out.println("--" + item.getTitulo())
			);*/
			
			musicas.forEach( System.out::println );
		});
	}
	
		
	
	

}
