package br.edu.unirn.orm.dominio;

import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="anoInicio",column=@Column(name="ano_criacao")),
		@AttributeOverride(name="anoFim",column=@Column(name="ano_fim_atividades"))
	})
	private IntervaloAnos periodoExistencia;
	
	@Id
	@GeneratedValue(generator="banda_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="banda_seq",sequenceName="grupo_musical_seq")
	private Long id;
	
	@Column(nullable=false)
	private String denominacao;
	
	
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

	public IntervaloAnos getPeriodoExistencia() {
		return periodoExistencia;
	}
	
	public void setPeriodoExistencia(IntervaloAnos periodoExistencia) {
		this.periodoExistencia = periodoExistencia;
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
