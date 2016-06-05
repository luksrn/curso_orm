package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;

/**
 * Básico da CRUD
 * 
 * @author Lucas Farias
 *
 */
public class CRUDBasicoComHibernateTest {
	
	private SessionFactory sessionFactory = HibernateBootstrap.getSessionFactory();
	
	@Test
	public void cadastroArtistaTest()  {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			
			tx.begin();
			
			
			Artista safadao = new Artista();
			safadao.setNome("Wesley Safadão");
			safadao.setDataNascimento( asData("06/09/1988") );
			safadao.setSexo("M");
			safadao.setBiografia("Wesley Oliveira da Silva, mais conhecido como Wesley Safadão, é um cantor, produtor e empresário brasileiro de forró eletrônico");
			
			session.save(safadao);
			//Hibernate: select max(ID_ARTISTA) from ARTISTA
			//Hibernate: insert into ARTISTA (NOME, SEXO, DATA_NASCIMENTO, BIOGRAFIA, ID_ARTISTA) values (?, ?, ?, ?, ?)
			
			tx.commit();
			
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){
				tx.rollback();
			}
			throw e;
		} finally {
			if ( session != null ){
				session.close();
			}
		}
		
	}
	
	@Test
	public void leituraRegistroDoBancoDeDados(){
		
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			 

			Artista artista = session.get(Artista.class, 1L);
			// Hibernate: select artista0_.ID_ARTISTA as ID_ARTIS1_0_0_, artista0_.NOME as NOME2_0_0_, artista0_.SEXO as SEXO3_0_0_, artista0_.DATA_NASCIMENTO as DATA_NAS4_0_0_, artista0_.BIOGRAFIA as BIOGRAFI5_0_0_ from ARTISTA artista0_ where artista0_.ID_ARTISTA=?
			
			assertTrue( artista.getId() == 1L );
			assertTrue( artista.getNome().equals("Caetano Veloso"));
			
		} finally {
			if ( session != null ){
				session.close();
			}
		}
		
	}
	
	@Test
	public void atualizarRegistroBancoDadosTest(){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			
			tx.begin();
			
			Artista artista = session.get(Artista.class, 5L);
			
			assertTrue( artista.getNome().equals("Ivete Zangalo"));	
			
			artista.setNome("Ivete Sangalo");
			
			session.update(artista);
			// Hibernate: update ARTISTA set NOME=?, SEXO=?, DATA_NASCIMENTO=?, BIOGRAFIA=? where ID_ARTISTA=?
			
			tx.commit();
			

			Artista ivete = session.get(Artista.class, 5L);
			
			assertTrue( ivete.getNome().equals("Ivete Sangalo"));	
			
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){
				tx.rollback();
			}
			throw e;
		} finally {
			if ( session != null ){
				session.close();
			}
		}
		
 
	}
	
	@Test
	public void remocaoRegistroBancoDadosTest(){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			
			tx.begin();
			
			Artista artista = session.get(Artista.class, 3L); 		 
			session.delete(artista);
			//Hibernate: select artista0_.ID_ARTISTA as ID_ARTIS1_0_0_, artista0_.NOME as NOME2_0_0_, artista0_.SEXO as SEXO3_0_0_, artista0_.DATA_NASCIMENTO as DATA_NAS4_0_0_, artista0_.BIOGRAFIA as BIOGRAFI5_0_0_ from ARTISTA artista0_ where artista0_.ID_ARTISTA=?
			//Hibernate: delete from ARTISTA where ID_ARTISTA=?
			
			tx.commit();
			
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){
				tx.rollback();
			}
			throw e;
		} finally {
			if ( session != null ){
				session.close();
			}
		}
		 	
	}
	
	
	@Test(expected=PersistenceException.class)
	public void testSituacaoDeRollback(){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			
			tx.begin();
			
			Artista caetano = session.get(Artista.class, 1L); 	
			
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
			session.delete(caetano);
			
			
			Artista robertoCarlos = session.find(Artista.class, 2L);
			robertoCarlos.setNome(null); // Campo é not-null
			
			session.save(robertoCarlos);
					
			//Hibernate: select artista0_.ID_ARTISTA as ID_ARTIS1_0_0_, artista0_.NOME as NOME2_0_0_, artista0_.SEXO as SEXO3_0_0_, artista0_.DATA_NASCIMENTO as DATA_NAS4_0_0_, artista0_.BIOGRAFIA as BIOGRAFI5_0_0_ from ARTISTA artista0_ where artista0_.ID_ARTISTA=?
			//Hibernate: delete from ARTISTA where ID_ARTISTA=?
			
			tx.commit();
			
		} catch (RuntimeException e){
			if ( tx != null && tx.isActive() ){
				tx.rollback();
			}
			throw e;
		} finally {
			
			Artista caetano = session.get(Artista.class, 1L); 	
			
			Assert.assertTrue( caetano.getNome().equals("Caetano Veloso"));
			
			if ( session != null ){
				session.close();
			}
		}
		 	
	}
	
	private static Date asData(String data){
		try{
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch(Exception e){
			return null;
		}
	}
}
