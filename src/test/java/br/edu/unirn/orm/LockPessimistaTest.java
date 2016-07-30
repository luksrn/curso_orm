package br.edu.unirn.orm;

import java.util.concurrent.TimeUnit;

import javax.persistence.LockModeType;
import javax.persistence.PessimisticLockException;
import javax.persistence.TypedQuery;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.junit.Ignore;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.utils.AbstractTest;

public class LockPessimistaTest extends AbstractTest {

	/**
	 * O comportamento deste lock irá depender do Banco utilizado.
	 * 
	 * PESSIMISTIC_READ - Permite um lock compartilhado. Previne um Lock Exclusivo por outra thread. Se o banco
	 * não tiver suporte ao Lock compartilhado, seŕa utilizado o lock exclusivo.
	 * 
	 * PESSIMISTIC_WRITE - Permite um lock exclusivo. Previne um lock exclusive/compartilhado por outra thread.
	 */
	@Test(expected=RuntimeException.class)
	public void testLockForUpdate(){
		
		doInTransaction( session -> {
			// Interface do JPA (Shared Lock)
			Artista artista = session.find(Artista.class, 3L, LockModeType.PESSIMISTIC_READ);
			
			executeSync(() -> {
				
				doInTransaction( sessionB -> {

					Artista artistaOutraThread = sessionB.find(Artista.class, 3L);
					artistaOutraThread.setNome("Tentativa alterar...");
					sessionB.update(artistaOutraThread);
					// throws javax.persistence.PessimisticLockException:
					//  org.h2.jdbc.JdbcSQLException: Concurrent update in table "ARTISTA"
					//: another transaction has updated or deleted the same row [90131-192]
				});
				
			});
		});
		
	}
	
	
	@Test
	public void testLockDepoisDeLeitura(){
		
		doInTransaction( session -> {
			Artista artista = session.find(Artista.class, 3L);
			session.lock(artista, LockModeType.PESSIMISTIC_READ);
		});
	}
	
	/**
	 * 
	 * Por padrão, o escopo do lock é aplicado apenas para entidade que está sendo aplicado
	 * o lock explicitamente...:
	 * 
	 *  No exemplo, ArtistaDetalhes não contém o lock:
	 */ 
	@Test
	public void lockAndReatachDetachedEntityComHibernate(){

		Artista artista = doWithSession( session -> {
			return session.find(Artista.class, 3L);
		});
		
		
		doInTransaction( session -> {
			session.buildLockRequest( 
					new LockOptions(LockMode.PESSIMISTIC_WRITE)
						.setTimeOut((int)TimeUnit.MILLISECONDS.toMillis(3))
					).lock(artista);;

			executeSync(()-> {
				
				doInTransaction( sessionB -> {
					Artista a = sessionB.find(Artista.class, 3L);
					//a.setNome("");
					a.getDetalhes().setBiografia("Biografia Alterada");
					// Aqui o Hibernate consegue identificar que Artista não foi alterado
					// e só gera o UPDATE para ArtistaDetalhes.
					// Como o lock foi realizado apenas em Artista, o update funciona corretamente!
					sessionB.update(a);

				});
			});
		});
		
		

	}
	
	@Test(expected=RuntimeException.class)
	public void lockDetached(){

		Artista artista2 = doWithSession( session -> {
			return session.find(Artista.class, 3L);
		});
		artista2.setNome("Alterado em VIEW");
		
		doInTransaction( session -> {
			session.buildLockRequest( 
					new LockOptions(LockMode.PESSIMISTIC_WRITE)
						.setTimeOut((int)TimeUnit.MILLISECONDS.toMillis(3))
					).lock(artista2);;
			
			executeSync(()-> {
				doInTransaction( sessionB -> {
					Artista a = sessionB.find(Artista.class, 3L);
					a.setNome("alterado");
					// javax.persistence.PessimisticLockException: could not execute statement
					sessionB.update(a);

				});
			});
			
			// Se executado, seria OK
			session.update(artista2);
		});
	}
	
	@Ignore
	@Test
	public void testLockScopeTodoasEntityComConsultaHql(){

		doInTransaction( session -> {
			TypedQuery<Artista> query = session.createQuery("select a from Artista a "
					+ " join fetch a.detalhes "
					+ " where a.id = :id", Artista.class);
			query.setParameter("id", 3L);
			query.setLockMode(LockModeType.PESSIMISTIC_WRITE);

			Artista artistaLock = query.getSingleResult();
			
			executeSync(()->{
				doInTransaction( sessionB -> {
					Artista a = sessionB.find(Artista.class, 3L);
					a.getDetalhes().setBiografia("Biografia Alterada");
					// Lock de escopo ocorreu na query
					sessionB.update(a);

				});
			});
		});
	}
}
