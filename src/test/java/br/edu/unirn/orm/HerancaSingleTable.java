package br.edu.unirn.orm;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Review;
import br.edu.unirn.orm.dominio.ReviewBanda;
import br.edu.unirn.orm.dominio.ReviewCD;
import br.edu.unirn.utils.AbstractTest;
 

public class HerancaSingleTable extends AbstractTest {

	
	@SuppressWarnings("all")
	@Test
	public void testConsultaPolimorfica(){
		
		doWithSession( session -> {
			
			List<Review> reviews = session.createQuery( 
							"select r from Review r").getResultList();
			

			Assert.assertTrue( reviews.size() == 2 );
			Assert.assertTrue( reviews.get(0).getClass() == ReviewBanda.class);
			Assert.assertTrue( reviews.get(1).getClass() == ReviewCD.class);
			
			List<Review> reviewsCd = session.createQuery( 
					"select r from ReviewCD r").getResultList();
	

			Assert.assertTrue( reviewsCd.size() == 1 );
			Assert.assertTrue( reviewsCd.get(0).getClass() == ReviewCD.class);
		});
	}
	
}
