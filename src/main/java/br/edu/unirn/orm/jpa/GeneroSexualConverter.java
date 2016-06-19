package br.edu.unirn.orm.jpa;

import br.edu.unirn.orm.dominio.GeneroSexual;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GeneroSexualConverter 
		implements AttributeConverter<GeneroSexual, Character>{

	@Override
	public Character convertToDatabaseColumn(GeneroSexual attribute) {
		if(attribute == null) return null;
		return attribute.getCode();
	}

	@Override
	public GeneroSexual convertToEntityAttribute(Character dbData) {
		if(dbData == null) return null;
		return GeneroSexual.fromCodigo(dbData);
	}
}
