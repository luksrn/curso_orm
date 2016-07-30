package br.edu.unirn.orm.hibernate;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;

public class PreDeleteListener implements PreDeleteEventListener {

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {

		System.out.println("--- Pre Delete -- ");
		return false;
	}

}
