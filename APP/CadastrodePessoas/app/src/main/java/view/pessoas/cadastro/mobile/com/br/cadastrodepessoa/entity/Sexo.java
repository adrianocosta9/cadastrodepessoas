package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity;

/**
 * Enum de dominio discreto.
 */
public enum Sexo {
    
	M("{Masculino}"),
	F("{Feminino}");

    /**
     * @return Retorna o codigo.
     */
   
	private String label;
    
    private Sexo(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
