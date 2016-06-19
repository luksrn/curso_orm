package br.edu.unirn.orm.hibernate;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.CharacterTypeDescriptor;

import br.edu.unirn.orm.dominio.GeneroSexual;

public class GeneroSexualTypeDescriptor 
		extends AbstractTypeDescriptor<GeneroSexual> {

    public static final GeneroSexualTypeDescriptor INSTANCE =
        new GeneroSexualTypeDescriptor();
    
	protected GeneroSexualTypeDescriptor() {
		super(GeneroSexual.class);
	}

    public String toString(GeneroSexual value) {
        return value == null ? null : value.name();
    }

    public GeneroSexual fromString(String string) {
        return string == null ? null : GeneroSexual.valueOf( string );
    }

    /**
	 * Utilizado no bind de parâmetros do {@link java.sql.PreparedStatement}.
	 * GeneroSexual -> Char
     */
	@Override
	public <X> X unwrap(GeneroSexual value, Class<X> type, WrapperOptions options) {
		return CharacterTypeDescriptor.INSTANCE.unwrap(
	            		value == null ? null : value.getCodigo(),
	                    type,
	                    options);
	}
	/**
	 * Utilizado durante a extração de valores do {@link java.sql.ResultSet}.
	 * Char -> GeneroSexual
	 */
	@Override
	public <X> GeneroSexual wrap(X value, WrapperOptions options) {
		Character valueAsChar = CharacterTypeDescriptor.INSTANCE.wrap(value, options);
		return GeneroSexual.fromCodigo(valueAsChar);
	}

}
