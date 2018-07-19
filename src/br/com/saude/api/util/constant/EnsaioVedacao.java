package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class EnsaioVedacao extends GenericConstant {

	private static EnsaioVedacao instance;
	
	private EnsaioVedacao() {
		
	}
	
	public static EnsaioVedacao getInstance() {
		if(instance == null)
			instance = new EnsaioVedacao();
		return instance;
	}
	
	public static final String SIM 				= "SIM";
	public static final String NAO 			= "NÃO";
	public static final String NAO_APLICAVEL 			= "NÃO APLICÁVEL";


}
