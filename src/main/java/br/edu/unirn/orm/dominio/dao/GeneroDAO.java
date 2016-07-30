package br.edu.unirn.orm.dominio.dao;

import java.util.List;

import br.edu.unirn.orm.GenericDAO;
import br.edu.unirn.orm.dominio.Genero;

public class GeneroDAO extends GenericDAO<Genero> {

	public GeneroDAO() {
		super(Genero.class);
	}

	public List<Genero> buscarPorDenominacao(String denominacao){		
		List<Genero> generos = getCurrentSession().createQuery(
				"select g from Genero g "
				+ " where g.denominacao like ?",Genero.class)
					.setParameter(0, "%"+ denominacao +"%")
					.getResultList();
		
		return generos;
	}
}
