package br.edu.unirn.orm;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.CD;
import br.edu.unirn.orm.dominio.CD_;
import br.edu.unirn.orm.dominio.Genero;
import br.edu.unirn.orm.dominio.Genero_;
import br.edu.unirn.utils.AbstractTest;

public class ConsultaCriteriaTest extends AbstractTest {
	
	@Test
	public void testMetaModel(){
		
		doWithSession(session ->{
			Metamodel metaModel = session.getMetamodel();
			ManagedType<CD> managedType = metaModel.managedType(CD.class);
			System.out.println("Informações da Entidade CD: ");
			managedType.getDeclaredAttributes().forEach( a -> {
				System.out.println(" Name: " + a.getName());
				System.out.println(" Java Type: " + a.getJavaType());
			});;
		});
	}
	
	
	@Test
	public void testConsultaGeneroPorNome(){
		doWithSession(session ->{
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Genero> criteria = builder.createQuery(Genero.class);
			
			Root<Genero> root = criteria.from(Genero.class);
			
			criteria.select(root).where( builder.equal( root.get(Genero_.denominacao), "MPB" ) );
			
			Genero genero = session.createQuery(criteria).getSingleResult();
			 
			Assert.assertTrue(genero.getId() == 6);
			
		});
	}
	
	
	
	@Test
	public void testConsultaTodosCdsAnoDepoisDe2015TypeSafe(){
		doWithSession( session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<CD> criteria = builder.createQuery(CD.class);
			
			Root<CD> root = criteria.from(CD.class);
			//Predicate restricoes = builder.gt( root.get("ano"), 2015 );
			Predicate restricoes = builder.gt( root.get(CD_.ano), 2015 );
			 
			criteria.select(root).where(restricoes);
			
			TypedQuery<CD> query = session.createQuery(criteria);
			
			List<CD> resultado = query.getResultList();
		
			Assert.assertTrue( resultado.size() == 1);
		});
	}

}
