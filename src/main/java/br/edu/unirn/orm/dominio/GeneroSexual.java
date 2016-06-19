package br.edu.unirn.orm.dominio;

public enum GeneroSexual {
	MASCULINO('M'),
	FEMININO('F');
	
	private final char codigo;

	GeneroSexual(char codigo) {
        this.codigo = codigo;
    }	
	public static GeneroSexual fromCodigo(char codigo) {
        if ( codigo == 'M' || codigo == 'm' ) {
            return MASCULINO;
        }
        if ( codigo == 'F' || codigo == 'f' ) {
            return FEMININO;
        }
        throw new UnsupportedOperationException(
            "O código " + codigo + " não é suportado!");
    }
    public char getCode() { return codigo;  }
}
