package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity;

/**
 * Enum de dominio discreto.
 */

public enum TipoSincronizacao {
    
	APG("App Gestor"),
	GAP("Gestor App");

    /**
     * @return Retorna o codigo.
     */
	
	private String label;
    
    private TipoSincronizacao(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
    public static TipoSincronizacao getTipoSincronozacao (String tipoSincronozacao){
    	if (tipoSincronozacao.equals(TipoSincronizacao.APG))
    		return APG;
    	else
    		return GAP;
    }
}
