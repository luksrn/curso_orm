package br.edu.unirn.orm;

import java.util.Date;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
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
	@Ignore
	public void testInsercao100_000RegistrosStatelessSession(){
		
		StatelessSession statelessSession = SessionFactoryHolder
							.getSessionFactory().openStatelessSession();
		Transaction tx =  statelessSession.getTransaction();
		
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
				statelessSession.insert(artista);
			}
			
			tx.commit();
			statelessSession.close();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
		
		// Realiza uma contagem...
		tx =  SessionFactoryHolder.getSessionFactory().getCurrentSession().getTransaction();
		statelessSession = SessionFactoryHolder.getSessionFactory().openStatelessSession();
		try {
			tx.begin();
		
			ArtistaDAO dao = new ArtistaDAO();
			
			Assert.assertTrue(dao.contar() > 1_000_000);
			
			ScrollableResults scrollableResult = dao.getCurrentSession()
												.createQuery("from Artista")				
												.setCacheMode(CacheMode.IGNORE)
												.scroll(ScrollMode.FORWARD_ONLY);
			 
			while( scrollableResult.next() ){
				Artista a = (Artista)scrollableResult.get(0);				 
				a.setNome( a.getNome() + " NomeAtualizado");
				statelessSession.update(a);
			}
		 
			// Deve-se fechar o scrollable result para  evitar memory leak
			scrollableResult.close();
			
			tx.commit();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		} 
		
		
		// leitura e update de 1 milhão de registros utilizando Stateless Session + Scrollable Results
		
	}
}
