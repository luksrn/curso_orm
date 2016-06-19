package br.edu.unirn.orm.hibernate;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

import br.edu.unirn.orm.dominio.GeneroSexual;

public class GeneroSexualType 
		extends AbstractSingleColumnStandardBasicType<GeneroSexual>{
	
	public static final GeneroSexualType INSTANCE = new GeneroSexualType();

	public GeneroSexualType() {
		super(CharTypeDescriptor.INSTANCE, 
			  GeneroSexualTypeDescriptor.INSTANCE);
	}

	@Override
	public String getName() {
		return "generoSexual";
	}
	
	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
}
