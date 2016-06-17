package br.edu.unirn.orm.dominio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Link table da relação artista - atuação (n-n) com atributos extras;
 * 
 * 
 */
@Entity
@Table(name="artista_atuacao")
public class ArtistaAtuacao {

	
	@Id
	@GeneratedValue(generator="artista_atuacao_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="artista_atuacao_seq",sequenceName="artista_atuacao_seq")
	private Long id;
	
 
	//@Id
	@ManyToOne
	@JoinColumn(name="id_atuacao")
	private Atuacao atuacao;
	

	//@Id
	@ManyToOne
	@JoinColumn(name="id_artista")
	private Artista artista;
	
	//@Id
	@ManyToOne
	@JoinColumn(name="id_banda")
	private Banda banda;
	
	public ArtistaAtuacao() {
		super();
	}

	public ArtistaAtuacao(Artista artista, Atuacao atuacao, Banda banda, LocalDate inicio) {
		super();
		this.artista = artista;
		this.atuacao = atuacao;
		this.banda = banda;
		this.inicio = inicio;
	}
	
	

	public ArtistaAtuacao(Artista artista, Atuacao atuacao, Banda banda, LocalDate inicio, LocalDate fim) {
		super();
		this.artista = artista;
		this.atuacao = atuacao;
		this.banda = banda;
		this.inicio = inicio;
		this.fim = fim;
	}


	public String getNomeArtista(){
		return artista.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	private LocalDate inicio;
	
	private LocalDate fim;

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Atuacao getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(Atuacao atuacao) {
		this.atuacao = atuacao;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}
	
	
}
