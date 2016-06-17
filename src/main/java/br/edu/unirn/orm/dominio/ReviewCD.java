package br.edu.unirn.orm.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("2")
public class ReviewCD extends Review {
	

	private Integer pontuacao;

	@ManyToOne
	@JoinColumn(name="id_album")
	private CD album;

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public CD getAlbum() {
		return album;
	}

	public void setAlbum(CD album) {
		this.album = album;
	}
	
	
}
