package br.edu.unirn.orm.dominio;
 
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class Artista {
	
	@Column(name="sexo",columnDefinition="char")
	//@Convert(converter=GeneroSexualConverter.class)
	@Type(type="generoSexual")
	private GeneroSexual genero;
	
	@Id
	@GeneratedValue(generator="artista_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="artista_seq",sequenceName="artista_seq")
	private Long id;
	
	@Basic
	@Column(name="nome_artista",nullable=false)
	private String nome;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NASCIMENTO")
	private Date dataNascimento;
	 
	@Basic(fetch=FetchType.LAZY)
	@Column(length=65535,nullable=true)
	private String biografia;
	
	@OneToMany(mappedBy="artista",
			cascade=CascadeType.ALL)
	private List<ArtistaAtuacao> artistaAtuacao;
	// Getters e Setters
	
	public void adicionarAtuacao(Atuacao atuacao, Banda banda, int inicio, int fim){
		
		ArtistaAtuacao artistaAtuacao = 
				new ArtistaAtuacao(this,
						atuacao,
						banda, 
						LocalDate.of(inicio, 1, 1),
						LocalDate.of(fim, 1, 1));
		
		this.artistaAtuacao.add(artistaAtuacao);
	}

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

	public GeneroSexual getGenero() {
		return genero;
	}

	public void setGenero(GeneroSexual sexo) {
		this.genero = sexo;
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

	public List<ArtistaAtuacao> getArtistaAtuacao() {
		return artistaAtuacao;
	}

	public void setArtistaAtuacao(List<ArtistaAtuacao> artistaAtuacao) {
		this.artistaAtuacao = artistaAtuacao;
	}
	
	
	
}
