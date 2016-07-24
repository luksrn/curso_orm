package br.edu.unirn.orm;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.orm.dominio.dao.GeneroDAO;
import br.edu.unirn.utils.AbstractTest;
/**
 * 
 * ***
 * http://what-when-how.com/hibernate/implementing-conversations-hibernate/
 * ***
 * 
 * @author lucas.oliveira
 *
 */
public class GenericDAOTest extends AbstractTest {	 
	
	/**
	 * Esta abordagem é considerada um padrão pela documentação. Além disso,
	 * este teste é grande para mostrar a colaboração da session entre os componentes DAO.
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
			generoDAO.getCurrentSession().contains(generoA);
			
			
			List<Genero> generos = generoDAO.buscar();
			
			generos.forEach(Genero::getDenominacao);
			
			long tamanhoTodosRegistros = generoDAO.contar();
			
			Assert.assertTrue( generos.size() == tamanhoTodosRegistros );
			
			List<Genero> generosPaginados = generoDAO.buscar(2, 3);
			
			Assert.assertTrue( generosPaginados.size() ==  3);
	
			Assert.assertTrue( generosPaginados.get(0).getId() == generos.get(2).getId());
	
			Assert.assertTrue( generosPaginados.get(2).getId() == generos.get(4).getId());
			
			GeneroDAO generoDAO2 = new GeneroDAO();
			
			List<Genero> generosMetal = generoDAO2.buscarPorDenominacao("Metal");

			assertTrue( generosMetal.size() == 4 );
			
			// Este trecho de código mostra que a Session é compartilhada
			// dentro do escopo da thread!
			generoDAO2.getCurrentSession().contains(generoA);
			
			
			// Remover GeneroA

			generoDAO.deletar(generoA);
			
			tx.commit();
		} catch (RuntimeException e){
			if( tx.isActive() ){
				tx.rollback();
			}
			throw e;
		}
	}

}
