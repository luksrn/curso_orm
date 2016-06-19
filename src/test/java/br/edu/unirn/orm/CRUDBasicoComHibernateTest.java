package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.orm.dominio.GeneroSexual;
import br.edu.unirn.utils.AbstractTest;
import br.edu.unirn.utils.DataHelper;

/**
 * Básico da CRUD
 * 
 * @author Lucas Farias
 *
 */
public class CRUDBasicoComHibernateTest extends AbstractTest implements DataHelper{
	
	@Test
	public void cadastroArtistaTest()  {
		doInTransaction( session -> {
			Artista safadao = new Artista();
			safadao.setNome("Wesley Safadão");
			safadao.setDataNascimento( asData("06/09/1988") );
			safadao.setGenero(GeneroSexual.MASCULINO);
			safadao.setBiografia("Wesley Oliveira da Silva, mais conhecido como Wesley Safadão, é um cantor, produtor e empresário brasileiro de forró eletrônico");
			
			session.persist(safadao);
			Assert.assertTrue( safadao.getId() != null );
		});
	}
	
	@Test
	public void leituraRegistroDoBancoDeDados(){
	
		doWithSession( session -> {
			Artista artista = session.get(Artista.class, 1L);
			// Hibernate: select artista0_.ID_ARTISTA as ID_ARTIS1_0_0_, artista0_.NOME as NOME2_0_0_, artista0_.SEXO as SEXO3_0_0_, artista0_.DATA_NASCIMENTO as DATA_NAS4_0_0_, artista0_.BIOGRAFIA as BIOGRAFI5_0_0_ from ARTISTA artista0_ where artista0_.ID_ARTISTA=?
			
			assertTrue( artista.getId() == 1L );
			assertTrue( artista.getNome().equals("Caetano Veloso"));
			assertTrue( artista.getGenero() == GeneroSexual.MASCULINO);
			assertTrue( artista.getBiografia().toString() != null );
		});
	}
	
	@Test
	public void atualizarRegistroBancoDadosTest(){
		
		doInTransaction( session -> {

			Artista artista = session.get(Artista.class, 5L);
			
			assertTrue( artista.getNome().equals("Ivete Zangalo"));	
			
			artista.setNome("Ivete Sangalo");
			
			session.update(artista);

			Artista ivete = session.get(Artista.class, 5L);
			
			assertTrue( ivete.getNome().equals("Ivete Sangalo"));	
		});
	}
	
	@Test
	public void remocaoRegistroBancoDadosTest(){
		doInTransaction( session -> {
			Artista artista = session.get(Artista.class, 3L); 		 
			session.delete(artista);
		});
		
		doWithSession( session -> {
			Artista artista = session.get(Artista.class, 3L); 		 
			assertTrue( artista == null );
		});
	}
	
	 
	public void testSituacaoDeRollback(){
		
		doInTransaction( session -> {
			Artista caetano = session.get(Artista.class, 1L); 		
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
			session.delete(caetano);
		
			Artista robertoCarlos = session.find(Artista.class, 2L);
			robertoCarlos.setNome(null); // Campo é not-null
			session.save(robertoCarlos);
		});
		
		doWithSession( session -> {
			Artista caetano = session.get(Artista.class, 1L); 	
			
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
		});
	}
	
	
}
