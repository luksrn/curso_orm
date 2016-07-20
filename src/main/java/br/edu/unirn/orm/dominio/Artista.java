package br.edu.unirn.orm.dominio;
 
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@NamedQueries({
	@NamedQuery(name="findByNome",
			query="select a from Artista a "
					+ " join fetch a.detalhes d "
					+ " where a.nome like :nome"),
	@NamedQuery(name="findByAtuacao",
			query="select a from Artista a "
			+ " join a.artistaAtuacao artistaAtuacao "
			+ " join artistaAtuacao.atuacao atuacao "
			+ " where atuacao.denominacao = :denominacao")
})
@Entity
public class Artista {
	
	@Id
	@GeneratedValue(generator="artista_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="artista_seq",sequenceName="artista_seq")
	private Long id;
	
	@Basic
	@Column(name="nome_artista",nullable=false)
	private String nome;
	
	@OneToOne(mappedBy="artista",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true)
	private ArtistaDetalhe detalhes;
	
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

	public ArtistaDetalhe getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(ArtistaDetalhe detalhes) {
		this.detalhes = detalhes;
	}
	
	public List<ArtistaAtuacao> getArtistaAtuacao() {
		return artistaAtuacao;
	}

	public void setArtistaAtuacao(List<ArtistaAtuacao> artistaAtuacao) {
		this.artistaAtuacao = artistaAtuacao;
	}
	
	
	
}
