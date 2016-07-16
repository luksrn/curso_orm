package br.edu.unirn.orm.dominio;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
 
@StaticMetamodel(CD.class)
public class CD_ {

	public static volatile SingularAttribute<CD, Integer> id;
	public static volatile SingularAttribute<CD, String> titulo;
	public static volatile SingularAttribute<CD, Integer> ano;
	public static volatile SingularAttribute<CD, Banda> banda;
	public static volatile ListAttribute<CD, Musica> musicas;
}
