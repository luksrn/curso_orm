package br.edu.unirn.orm;

import java.util.function.Consumer;

import org.hibernate.Session;

@FunctionalInterface
public interface HibernateSessionConsumer 
							extends Consumer<Session>{

}
