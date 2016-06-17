package br.edu.unirn.orm.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Atuacao {

	@Id
	@GeneratedValue(generator="atuacao_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="atuacao_seq",sequenceName="atuacao_seq")
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
