package br.edu.unirn.orm;
// Wrapper
public class EstatisticaCD {

	private String album;
	private Long quantidadeDeMusicas;
	private Long duracaoDoAlbum;
	
	public EstatisticaCD(String album, 
			Long quantidadeDeMusicas,
			Long duracaoDoAlbum) {
		super();
		this.album = album;
		this.quantidadeDeMusicas = quantidadeDeMusicas;
		this.duracaoDoAlbum = duracaoDoAlbum;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public Long getQuantidadeDeMusicas() {
		return quantidadeDeMusicas;
	}
	
	public Long getDuracaoDoAlbum() {
		return duracaoDoAlbum;
	}
	
	
}
