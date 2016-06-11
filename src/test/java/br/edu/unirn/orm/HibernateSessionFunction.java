package br.edu.unirn.orm;

import java.util.function.Function;

import org.hibernate.Session;

@FunctionalInterface
public interface HibernateSessionFunction<R> 
					extends Function<Session, R> {

}
