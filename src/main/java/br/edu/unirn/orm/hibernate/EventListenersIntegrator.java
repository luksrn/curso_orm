package br.edu.unirn.orm.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class EventListenersIntegrator implements Integrator {

	@SuppressWarnings("all")
	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		 
        EventListenerRegistry eventListenerRegistry =
            serviceRegistry.getService( EventListenerRegistry.class );
  
        eventListenerRegistry.prependListeners( EventType.PRE_DELETE,
        											PreDeleteListener.class );
        eventListenerRegistry.prependListeners( EventType.POST_DELETE,
				PostDeleteListener.class );
        eventListenerRegistry.prependListeners( EventType.PRE_UPDATE,
				PreUpdateListener.class );
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, 
			SessionFactoryServiceRegistry serviceRegistry) {	
	}

}
