package br.edu.unirn.orm;

import java.util.Date;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.orm.dominio.ArtistaDetalhe;
import br.edu.unirn.orm.dominio.GeneroSexual;
import br.edu.unirn.orm.dominio.dao.ArtistaDAO;
import br.edu.unirn.utils.AbstractTest;

public class BatchEStatelessSessionTest extends AbstractTest {

	/**
	 * Este teste provavelmente dá out of memory.
	 */
	@Test
	@Ignore
	public void testInsercao100_000RegistrosStatefullSession(){
		

		Transaction tx =  SessionFactoryHolder.getSessionFactory().getCurrentSession().getTransaction();
		
		try {
			tx.begin();
		
			ArtistaDAO dao = new ArtistaDAO();
			
			for (int i = 0; i < 1_000_000 ; i++){
				Artista artista = new Artista();
				artista.setNome("Artista " + i);
				artista.setDetalhes(new ArtistaDetalhe());
				artista.getDetalhes().setBiografia( "Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia..." + i);
				artista.getDetalhes().setGenero(GeneroSexual.MASCULINO);
				artista.getDetalhes().setDataNascimento(new Date());
				dao.salvar(artista);
			}
			
			tx.commit();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
	}
	
	
	@Test
	public void testInsercao100_000RegistrosStatelessSession(){
		
		StatelessSession session = SessionFactoryHolder.getSessionFactory().openStatelessSession();
		Transaction tx =  session.getTransaction();
		
		try {
			tx.begin();
		
			//ArtistaDAO dao = new ArtistaDAO();
			
			for (int i = 0; i < 1_000_000 ; i++){
				Artista artista = new Artista();
				artista.setNome("Artista " + i);
				artista.setDetalhes(new ArtistaDetalhe());
				artista.getDetalhes().setBiografia( "Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia...Biografia..." + i);
				artista.getDetalhes().setGenero(GeneroSexual.MASCULINO);
				artista.getDetalhes().setDataNascimento(new Date());
				// Comentando dao.salvar pois usa o Session!
				//dao.salvar(artista);
				session.insert(artista);
			}
			
			tx.commit();
			session.close();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
		
		
		tx =  SessionFactoryHolder.getSessionFactory().getCurrentSession().getTransaction();
		
		try {
			tx.begin();
		
			ArtistaDAO dao = new ArtistaDAO();
			
			Assert.assertTrue(dao.contar() > 1_000_000);
			tx.commit();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
	}
}
