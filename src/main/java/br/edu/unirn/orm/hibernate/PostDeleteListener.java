package br.edu.unirn.orm.hibernate;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class PostDeleteListener implements PostDeleteEventListener {

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		System.out.println("-- Post Delete --");
		
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	
 
}
