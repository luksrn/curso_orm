package br.edu.unirn.orm;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.utils.AbstractTest;

public class GenericDAOTest extends AbstractTest {
	 
	
	/**
	 * Esta abordagem é considerada um antipattern pela documentação.
	 * 
	 */
	@Test
	public void testarComunicacaoSessionEmUmDAO_SessionPerOperation(){		
		
		GenericDAO<Genero> generoDAO = new GenericDAO<>(Genero.class);
		
		Genero generoA = new Genero();
		generoA.setDenominacao("Genero A");
		generoDAO.salvar(generoA);
		
		// Efeito do Session Por Operação.
		generoDAO.doInTransaction( session -> {
			Assert.assertFalse( session.contains(generoA) );
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
	}

}
