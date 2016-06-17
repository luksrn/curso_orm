package br.edu.unirn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DataHelper {
	
	default Date asData(String data){
		try{
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch(Exception e){
			return null;
		}
	}
	 

}
