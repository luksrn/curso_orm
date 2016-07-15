package br.edu.unirn.orm;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.utils.AbstractTest;

public class ConsultaCriteriaTest extends AbstractTest {
	
	@Test
	public void testConsultaGeneroPorNome(){
		doWithSession(session ->{
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Genero> criteria = builder.createQuery(Genero.class);
			
			Root<Genero> root = criteria.from(Genero.class);
			
			criteria.select(root).where( builder.equal( root.get("denominacao"), "MPB" ) );
			
			Genero genero = session.createQuery(criteria).getSingleResult();
			 
			Assert.assertTrue(genero.getId() == 6);
			
		});
	}

}
