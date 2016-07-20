package br.edu.unirn.orm;

import java.util.Date;

import javax.persistence.OptimisticLockException;

import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.utils.AbstractTest;



public class LockOtimistaTest extends AbstractTest {

	/**
	 * Hibernate: 
	 * 
	  
    select
        artistadet0_.id as id1_3_0_,
        artistadet0_.id_artista as id_artis6_3_0_,
        artistadet0_.biografia as biografi2_3_0_,
        artistadet0_.DATA_NASCIMENTO as DATA_NAS3_3_0_,
        artistadet0_.sexo as sexo4_3_0_,
        artistadet0_.version as version5_3_0_ 
    from
        ArtistaDetalhe artistadet0_ 
    where
        artistadet0_.id_artista=?
Hibernate: 
    update
        ArtistaDetalhe 
    set
        id_artista=?,
        biografia=?,
        DATA_NASCIMENTO=?,
        sexo=?,
        version=? 
    where
        id=? 
        and version=?
        
	 */
	@Test
	public void testFuncionamentoUpdateVersionLockOtimista(){
 
		doInTransaction( session -> {
			Artista roberto = session.get(Artista.class, 3L);
			roberto.getDetalhes().setBiografia("Biografia alterada...");
			session.save(roberto);
		});
		 
	}
	
	@Test(expected=OptimisticLockException.class)
	public void testColisaoLockOtimista(){
		// Detached Artista (Lido pelo User A)
		Artista artista = doWithSession( session -> {
			
			Artista roberto = session.createNamedQuery("findByNome", Artista.class)
					.setParameter("nome", "Roberto Carlos")
					.getSingleResult(); 
			return roberto;
		});
		
		// Lido e escrito pelo User B
		doInTransaction( session -> {

			Artista roberto = session.createNamedQuery("findByNome", Artista.class)
					.setParameter("nome", "Roberto Carlos")
					.getSingleResult(); 
			roberto.getDetalhes().setBiografia("Biografia alterada...");
			session.save(roberto);
		});
		
		// Escrito pelo User A // javax.persistence.OptimisticLockException: ->  org.hibernate.StaleStateException:
		doInTransaction( session -> {
			artista.getDetalhes().setBiografia("Conlisão!!!");
			session.save(artista);
		});
	}


	/**
	 * Lock ocorre em uma abordagem ou tudo ou nada. 
	 *
	 */
	@Test(expected=OptimisticLockException.class)
	public void testColisaoCamposDiferentesLockOtimista(){
		// Detached Artista (Lido pelo User A)
		Artista artista = doWithSession( session -> {
			
			Artista roberto = session.createNamedQuery("findByNome", Artista.class)
					.setParameter("nome", "Roberto Carlos")
					.getSingleResult(); 
			return roberto;
		});
		
		// Lido e escrito pelo User B
		doInTransaction( session -> {

			Artista roberto = session.createNamedQuery("findByNome", Artista.class)
					.setParameter("nome", "Roberto Carlos")
					.getSingleResult(); 
			roberto.getDetalhes().setBiografia("Biografia alterada...");
			session.save(roberto);
		});
		
		// Escrito pelo User A // javax.persistence.OptimisticLockException: ->  org.hibernate.StaleStateException:
		doInTransaction( session -> {
			artista.getDetalhes().setDataNascimento(new Date());
			session.save(artista);
		});
	}
 
}
