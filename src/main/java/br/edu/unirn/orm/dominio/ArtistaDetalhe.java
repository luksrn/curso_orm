package br.edu.unirn.orm.dominio;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
/**
 * Informações detalhadas do artista.
 *  
 * Exemplo de um cenário one to one unidirecional.
 */
@Entity
public class ArtistaDetalhe {
	
	@Id
	@GeneratedValue(generator="artista_detalhe",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="artista_detalhe",sequenceName="artista_detalhe_seq")
	private Long id;
	
	@Version
	private Integer version = 0;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_artista")
	private Artista artista;
	
	@Column(name="sexo",columnDefinition="char")
	@Type(type="generoSexual")
	private GeneroSexual genero;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NASCIMENTO")
	private Date dataNascimento;
	 
	@Basic(fetch=FetchType.LAZY)
	@Column(length=65535,nullable=true)
	private String biografia;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GeneroSexual getGenero() {
		return genero;
	}

	public void setGenero(GeneroSexual genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
}
