package br.edu.unirn.orm;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * 
 * @author lucas.oliveira
 *
 * @param <T> Tipo do dominio associado ao DAO
 */
public class GenericDAO<T> extends AbstractDAO<T,Long> {
	
	private Class<T> dominio;
	
	public GenericDAO(Class<T> dominio){	
		this.dominio = dominio;
	}
	
	public Class<T> getDominio(){
		return dominio;
	}
	
	@Override
	public T salvar(T entidade) {
		return doInTransaction( session -> {
			session.saveOrUpdate(entidade);
			return entidade;
		});
	}

	@Override
	public void deletar(T entidade) {
		doInTransaction( session -> {
			session.delete(entidade);
		});
	}

	@Override
	public long contar() {
		return doWithSession( session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();			
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);			
			criteria.select(builder.count(criteria.from(dominio)));
			return session.createQuery(criteria).getSingleResult();
		});
	}

	@Override
	public List<T> buscar() { 
		
		return doWithSession( session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();			
			CriteriaQuery<T> criteria = builder.createQuery(dominio);			
			criteria.from(dominio);
			return session.createQuery(criteria).getResultList();			
		});
	}

	@Override
	public List<T> buscar(int offset, int max) { 

		return doWithSession( session -> {
			CriteriaBuilder builder = session.getCriteriaBuilder();			
			CriteriaQuery<T> criteria = builder.createQuery(dominio);			
			criteria.from(dominio);
			return session.createQuery(criteria)
					.setFirstResult(offset).setMaxResults(max).getResultList();			
		});
	}
	
}
