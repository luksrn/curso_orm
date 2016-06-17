package br.edu.unirn.utils;

import java.util.function.Consumer;

import org.hibernate.Session;

@FunctionalInterface
public interface HibernateSessionConsumer 
							extends Consumer<Session>{

}
