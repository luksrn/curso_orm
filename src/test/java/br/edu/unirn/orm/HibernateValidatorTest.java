package br.edu.unirn.orm;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;

import br.edu.unirn.orm.dominio.Artista;
import br.edu.unirn.orm.dominio.ArtistaDetalhe;
import br.edu.unirn.utils.AbstractTest;
import br.edu.unirn.utils.DataHelper;

public class HibernateValidatorTest extends AbstractTest implements DataHelper {

	@Test
	public void validacaoDominioInvalida(){
		
		Artista artista = new Artista();
		
		
		Set<ConstraintViolation<Artista>> erros = validator.validate(artista);
		
		Assert.assertTrue( erros.size() == 2 );
		
		List<String> mensagens = erros.stream()
								.map( ConstraintViolation::getMessage )
								.collect(Collectors.toList() );
		
		Assert.assertTrue(mensagens.contains("O artista deve possuir um nome!") );
		// Ocorre devido ArtistaDetalhe ser null.
		Assert.assertTrue(mensagens.contains("O artista deve possuir detalhes!") );
	}
	
	

	@Test
	public void validacaoDominioInvalidaComDetalheInstanciado(){
		
		Artista artista = new Artista();
		ArtistaDetalhe detalhe = new ArtistaDetalhe();
		artista.setDetalhes(detalhe);
		detalhe.setArtista(artista);
		detalhe.setDataNascimento(asData("08/14/2110"));
		
		Set<ConstraintViolation<Artista>> erros = validator.validate(artista);
		
		Assert.assertTrue( erros.size() == 3 );
		
		List<String> mensagens = erros.stream()
								.map( ConstraintViolation::getMessage )
								.collect(Collectors.toList() );
		
		Assert.assertTrue(mensagens.contains("O artista deve possuir um nome!") );
		
		Assert.assertTrue(mensagens.contains("O artista deve possuir um genero sexual") );
		
		Assert.assertTrue(mensagens.contains("O artista deve possuir uma Data de Nascimento no passado.") );
		
		
	}
}
