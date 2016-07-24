package br.edu.unirn.orm;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.utils.AbstractTest;

public class GenericDAOTest extends AbstractTest {
	 
	
	/**
	 * Esta abordagem é considerada um antipattern pela documentação.
	 * 
	 * Log: org.hibernate.engine.internal.StatisticalLoggingSessionEventListener
	 */
	@Test
	public void testarComunicacaoSessionEmUmDAO_SessionPerRequest(){		
		
		Transaction tx =  SessionFactoryHolder.getSessionFactory().getCurrentSession().getTransaction();
		
		try {
			tx.begin();
			
			GenericDAO<Genero> generoDAO = new GenericDAO<>(Genero.class);
			
			Genero generoA = new Genero();
			generoA.setDenominacao("Genero A");
			generoDAO.salvar(generoA);
			
			// Efeito do Session Per Request!!!
			generoDAO.doWithCurrentSession( session -> {
				Assert.assertTrue( session.contains(generoA) );
			});
			generoDAO.deletar(generoA);
			
			List<Genero> generos = generoDAO.buscar();
			
			generos.forEach(Genero::getDenominacao);
			
			long tamanhoTodosRegistros = generoDAO.contar();
			
			Assert.assertTrue( generos.size() == tamanhoTodosRegistros );
			
			List<Genero> generosPaginados = generoDAO.buscar(2, 3);
			
			Assert.assertTrue( generosPaginados.size() ==  3);
	
			Assert.assertTrue( generosPaginados.get(0).getId() == generos.get(2).getId());
	
			Assert.assertTrue( generosPaginados.get(2).getId() == generos.get(4).getId());
			
			tx.commit();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
	}

}
