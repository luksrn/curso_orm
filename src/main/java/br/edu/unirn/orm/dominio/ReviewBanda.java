package br.edu.unirn.orm.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("1")
public class ReviewBanda extends Review {

	@ManyToOne
	@JoinColumn(name="id_grupo_musical")
	private Banda banda;

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
	
	
}
