package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.unirn.orm.dominio.Banda;
import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.utils.AbstractTest;

/**
 * Exemplos de operações Many to Many
 * 
 * @author Lucas Farias
 *
 */
public class ManyToManyCrudComHibernateTest extends AbstractTest {

	@Test
	public void leituraDeRelacaoManyToMany(){
		doWithSession( session -> {
			Banda banda = session.find(Banda.class, 1L);
			
			assertTrue( banda.getDenominacao().equals("Nightwish"));
			
			assertTrue( banda.getGeneros().size() == 2 );
		});
	}
	
	
	@Test
	public void inclusaoDeGeneroComGet(){
		doInTransaction( session -> {
			Banda banda = session.find(Banda.class, 3L);
			
			assertTrue( banda.getDenominacao().equals("Lunatica"));
			
			assertTrue( banda.getGeneros().size() == 2 );
			
			Genero generoPowerMetal = session.get(Genero.class, 4L);
			 
			banda.getGeneros().add(generoPowerMetal);
			
			session.save(banda);
		});
		
		doWithSession( session -> {
			Banda banda = session.find(Banda.class, 3L);
			
			assertTrue( banda.getDenominacao().equals("Lunatica"));
			assertTrue( banda.getGeneros().size() == 3 );
			assertTrue( banda.getGeneros().stream()
									.anyMatch( g -> g.getDenominacao().equals("Power Metal")));
		});
	}
	
	
	
	
	@Test
	public void inclusaoDeGeneroComDirtyChecking(){
		doInTransaction( session -> {
			Banda banda = session.find(Banda.class, 2L);
			
			assertTrue( banda.getDenominacao().equals("Avantasia"));
			
			assertTrue( banda.getGeneros().size() == 1 );
			
			Genero genero = new Genero();
			genero.setDenominacao("Melodic Power Metal");
			
			banda.getGeneros().add(genero);
			
			session.persist(banda);
		});
	}
}
