package br.edu.unirn.orm.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="album")
public class CD {

	@ManyToOne
	@JoinColumn(name="id_gravadora")
	private Gravadora gravadora;
	
	@Id
	@GeneratedValue(generator="musica_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="musica_seq",sequenceName="musica_seq")
	private Long id;
	
	private String titulo;
	
	private Integer ano;

	@OneToMany(mappedBy="album",
			fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<Musica> musicas;	
	
	@ManyToOne
	@JoinColumn(name="id_grupo_musical")
	private Banda banda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musica) {
		this.musicas = musica;
	}
	
	

}
