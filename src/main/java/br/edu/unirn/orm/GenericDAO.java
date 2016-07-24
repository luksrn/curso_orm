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
public class GenericDAO<T> extends AbstractHibernateDAO<T,Long> {
	
	private Class<T> dominio;
	
	public GenericDAO(Class<T> dominio){	
		this.dominio = dominio;
	}
	
	public Class<T> getDominio(){
		return dominio;
	}
	
	@Override
	public T salvar(T entidade) {
		getCurrentSession().saveOrUpdate(entidade);
		return entidade;
	}

	@Override
	public void deletar(T entidade) {
		getCurrentSession().delete(entidade);
	}

	@Override
	public long contar() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);			
		criteria.select(builder.count(criteria.from(dominio)));
		return getCurrentSession().createQuery(criteria).getSingleResult();
	}

	@Override
	public List<T> buscar() { 
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<T> criteria = builder.createQuery(dominio);			
		criteria.from(dominio);
		return getCurrentSession().createQuery(criteria).getResultList();	
	}

	@Override
	public List<T> buscar(int offset, int max) { 
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<T> criteria = builder.createQuery(dominio);			
		criteria.from(dominio);
		return getCurrentSession().createQuery(criteria)
				.setFirstResult(offset).setMaxResults(max).getResultList();
	}
	
}
