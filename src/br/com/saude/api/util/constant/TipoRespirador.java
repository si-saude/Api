package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoRespirador extends GenericConstant {

	private static TipoRespirador instance;
	
	private TipoRespirador() {
		
	}
	
	public static TipoRespirador getInstance() {
		if(instance == null)
			instance = new TipoRespirador();
		return instance;
	}
	
	public final String SEMIFACIAL 			= "SEMI-FACIAL";
	public final String FACIALINTEIRA 		= "FACIAL-INTEIRA";

}
