package br.edu.unirn.orm.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Gravadora {
	
	@Id
	@GeneratedValue(generator="gravadora_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gravadora_seq",sequenceName="gravadora_seq")
	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
