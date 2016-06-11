package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;

/**
 * Básico da CRUD
 * 
 * @author Lucas Farias
 *
 */
public class CRUDBasicoComHibernateTest extends AbstractTest {
	
	@Test
	public void cadastroArtistaTest()  {
		 
		doInTransaction( session -> {
			
			Artista safadao = new Artista();
			safadao.setNome("Wesley Safadão");
			safadao.setDataNascimento( asData("06/09/1988") );
			safadao.setSexo("M");
			safadao.setBiografia("Wesley Oliveira da Silva, mais conhecido como Wesley Safadão, é um cantor, produtor e empresário brasileiro de forró eletrônico");
			
			session.save(safadao);
			Assert.assertTrue( safadao.getId() != null );
			
			return safadao;
		});
		
	}
	
	@Test
	public void leituraRegistroDoBancoDeDados(){
	
		doWithSession( session -> {
			Artista artista = session.get(Artista.class, 10000L);
			// Hibernate: select artista0_.ID_ARTISTA as ID_ARTIS1_0_0_, artista0_.NOME as NOME2_0_0_, artista0_.SEXO as SEXO3_0_0_, artista0_.DATA_NASCIMENTO as DATA_NAS4_0_0_, artista0_.BIOGRAFIA as BIOGRAFI5_0_0_ from ARTISTA artista0_ where artista0_.ID_ARTISTA=?
			
			assertTrue( artista.getId() == 10000L );
			assertTrue( artista.getNome().equals("Caetano Veloso"));
			assertTrue( artista.getBiografia().toString() != null );
		});
	}
	
	@Test
	public void atualizarRegistroBancoDadosTest(){
		
		doInTransaction( session -> {

			Artista artista = session.get(Artista.class, 50000L);
			
			assertTrue( artista.getNome().equals("Ivete Zangalo"));	
			
			artista.setNome("Ivete Sangalo");
			
			session.update(artista);

			Artista ivete = session.get(Artista.class, 50000L);
			
			assertTrue( ivete.getNome().equals("Ivete Sangalo"));	
		});
	}
	
	@Test
	public void remocaoRegistroBancoDadosTest(){
		doInTransaction( session -> {
			Artista artista = session.get(Artista.class, 30000L); 		 
			session.delete(artista);
		});
	}
	
	 
	public void testSituacaoDeRollback(){
		
		doInTransaction( session -> {
			Artista caetano = session.get(Artista.class, 10000L); 		
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
			session.delete(caetano);
		
			Artista robertoCarlos = session.find(Artista.class, 20000L);
			robertoCarlos.setNome(null); // Campo é not-null
			session.save(robertoCarlos);
		});
		
		doWithSession( session -> {
			Artista caetano = session.get(Artista.class, 10000L); 	
			
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
		});
	}
	
	private static Date asData(String data){
		try{
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch(Exception e){
			return null;
		}
	}
}
