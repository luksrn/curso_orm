package br.edu.unirn.orm.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IntervaloAnos {

	@Column(name="ano_inicio",nullable=false)
	private Integer anoInicio;
	
	@Column(name="ano_fim",nullable=true)
	private Integer anoFim;

	public Integer getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}

	public Integer getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(Integer anoFim) {
		this.anoFim = anoFim;
	}
	 
	
}
