package br.edu.unirn.utils;

import java.util.concurrent.Callable;

/**
 * Apenas para testes
 * @author lucas
 *
 */
public interface VoidCallable extends Callable<Void>{

	void execute();
	
	default Void call() throws Exception {
		execute();
		return null;
	}
}
