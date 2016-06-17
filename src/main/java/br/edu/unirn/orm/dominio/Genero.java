package br.edu.unirn.orm.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="genero_musical")
public class Genero {
	
	@Id
	@GeneratedValue(generator="genero_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="genero_seq",sequenceName="genero_seq")
	private Long id;
	
	private String denominacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
}
