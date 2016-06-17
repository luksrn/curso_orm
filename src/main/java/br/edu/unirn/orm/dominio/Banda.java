package br.edu.unirn.orm.dominio;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="grupo_musical")
public class Banda {

	@Id
	@GeneratedValue(generator="banda_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="banda_seq",sequenceName="grupo_musical_seq")
	private Long id;
	
	@Column(nullable=false)
	private String denominacao;
	
	@Column(name="ano_inicio",nullable=false)
	private Integer anoFormacao;
	
	@Column(name="ano_fim",nullable=true)
	private Integer anoFinalizacao;
	
	@OneToMany(mappedBy="banda")
	private Set<CD> albuns;


	@ManyToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="grupo_genero_musical",
			joinColumns=@JoinColumn(name="id_grupo_musical"),
			inverseJoinColumns=@JoinColumn(name="id_genero_musical"))
	private Set<Genero> generos;
	

	@OneToMany(mappedBy="banda",
			cascade=CascadeType.ALL,
			orphanRemoval=true)
	private List<ArtistaAtuacao> artistasAtuacao;
	

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

	public Integer getAnoFormacao() {
		return anoFormacao;
	}

	public void setAnoFormacao(Integer anoFormacao) {
		this.anoFormacao = anoFormacao;
	}

	public Integer getAnoFinalizacao() {
		return anoFinalizacao;
	}

	public void setAnoFinalizacao(Integer anoFinalizacao) {
		this.anoFinalizacao = anoFinalizacao;
	}

	public Set<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(Set<Genero> generos) {
		this.generos = generos;
	}

	public Set<CD> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(Set<CD> albuns) {
		this.albuns = albuns;
	}

	public List<ArtistaAtuacao> getArtistasAtuacao() {
		return artistasAtuacao;
	}

	public void setArtistasAtuacao(List<ArtistaAtuacao> artistasAtuacao) {
		this.artistasAtuacao = artistasAtuacao;
	}
	
	
}
