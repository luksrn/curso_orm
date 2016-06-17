package br.edu.unirn.orm.dominio;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Musica {
	
	@ManyToOne
	@JoinColumn(name="id_album")
	private CD album;

	@Id
	@GeneratedValue(generator="musica_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="musica_seq",sequenceName="musica_seq")
	private Long id;
	
	private Integer numero;
	
	private String titulo;
	
	private Duration duracao;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Duration getDuracao() {
		return duracao;
	}

	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}
 

	public CD getAlbum() {
		return album;
	}

	public void setAlbum(CD album) {
		this.album = album;
	}
	
	public static void main(String[] args) {
		System.out.println( Duration.ofMinutes(1));

		System.out.println( Duration.ofSeconds(240)); // 2min

		System.out.println( Duration.ofSeconds(460).toMinutes()); //

		System.out.println( Duration.ofMinutes(6));

		System.out.println( Duration.ofMinutes(2));
	}
	
	
}