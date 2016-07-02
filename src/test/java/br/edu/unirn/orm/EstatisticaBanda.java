package br.edu.unirn.orm;

class EstatisticasBanda {
	
	private String album;
	private Long quantidadeDeMusicas;
	private Long duracaoDoAlbum;
	
	public EstatisticasBanda(String album, 
			Long quantidadeDeMusicas, 
			Long duracaoDoAlbum) {
		this.album = album;
		this.quantidadeDeMusicas = quantidadeDeMusicas;
		this.duracaoDoAlbum = duracaoDoAlbum;
	}
	
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public Long getQuantidadeDeMusicas() {
		return quantidadeDeMusicas;
	}
	public void setQuantidadeDeMusicas(Long quantidadeDeMusicas) {
		this.quantidadeDeMusicas = quantidadeDeMusicas;
	}
	public Long getDuracaoDoAlbum() {
		return duracaoDoAlbum;
	}
	public void setDuracaoDoAlbum(Long duracaoDoAlbum) {
		this.duracaoDoAlbum = duracaoDoAlbum;
	}
	
	
	
}