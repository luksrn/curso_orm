package br.edu.unirn.orm.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.edu.unirn.orm.dominio.GeneroSexual;

@Converter
public class GeneroSexualConverter 
		implements AttributeConverter<GeneroSexual, Character>{

	@Override
	public Character convertToDatabaseColumn(GeneroSexual attribute) {
		if(attribute == null) return null;
		return attribute.getCodigo();
	}

	@Override
	public GeneroSexual convertToEntityAttribute(Character dbData) {
		if(dbData == null) return null;
		return GeneroSexual.fromCodigo(dbData);
	}
}
