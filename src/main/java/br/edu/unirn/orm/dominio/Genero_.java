package br.edu.unirn.orm.dominio;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Genero.class)
public class Genero_ {

	public static volatile SingularAttribute<Genero, Long> id;
	public static volatile SingularAttribute<Genero, String> denominacao;
}
