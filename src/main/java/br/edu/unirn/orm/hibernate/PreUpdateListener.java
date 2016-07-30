package br.edu.unirn.orm.hibernate;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

public class PreUpdateListener implements PreUpdateEventListener {
	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		
		try {
			String[] propertyNames = event.getPersister()
								.getEntityMetamodel()
								.getPropertyNames();
	
			for ( int i = 0 ; i < event.getState().length ; i++ ){
				   
				System.out.println(
					"Propriedade = " + propertyNames[i] +  "!"
					+ "\n\t Valor Anterior: " + event.getOldState()[i] 
					+ "\n\t Novo Valor: " + event.getState()[i] );
				 
			}
		}catch(Exception e){
			
		}
			
		return false;
	}
}
