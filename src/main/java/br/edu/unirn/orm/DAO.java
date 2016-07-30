package br.edu.unirn.orm;

import java.io.Serializable;
import java.util.List;

/**
 * @author lucas.oliveira
 *
 * @param <T> - Entidade Persistente
 * @param <ID> - Chave Primária
 */
public interface DAO <T, ID extends Serializable> {

	T salvar(T entidade);
	
	void deletar(T entidade);
	
	long contar();

	List<T> buscar();
	
	List<T> buscar(int offset, int max);
	
}
