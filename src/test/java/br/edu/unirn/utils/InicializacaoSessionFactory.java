package br.edu.unirn.utils;

import org.junit.rules.ExternalResource;

import br.edu.unirn.orm.SessionFactoryHolder;

public class InicializacaoSessionFactory extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		System.out.println("** Inicializando SessionFactory **");
		SessionFactoryHolder.getSessionFactory();
	}
	
	@Override
	protected void after() {
		System.out.println("** Finalizando SessionFactory **");
		SessionFactoryHolder.getSessionFactory().close();
	}
}
