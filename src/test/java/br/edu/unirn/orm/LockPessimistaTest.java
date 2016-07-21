package br.edu.unirn.orm;

import javax.persistence.LockModeType;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
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

					Artista artistaOutraThread = sessionB.find(Artista.class, 3L, LockModeType.PESSIMISTIC_READ);	
					// throws javax.persistence.PessimisticLockException:
					//  org.h2.jdbc.JdbcSQLException: Concurrent update in table "ARTISTA": another transaction has updated or deleted the same row [90131-192]
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
					new LockOptions(LockMode.PESSIMISTIC_READ)
					).lock(artista);;
			
			executeSync(()-> {
				Artista a = session.find(Artista.class, 3L);
				a.getDetalhes().setBiografia("Biografia Alterada");
				// Aqui o Hibernate consegue identificar que Artista não foi alterado
				// e só gera o UPDATE para ArtistaDetalhes.
				session.update(a);
			});
		});
	}
}
