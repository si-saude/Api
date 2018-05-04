package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class FumaClassificacao extends GenericConstant {

	private static FumaClassificacao instance;
	
	private FumaClassificacao() {
		
	}
	
	public static FumaClassificacao getInstance() {
		if(instance==null)
			instance = new FumaClassificacao();
		return instance;
	}
	
	public static final String EX_FUMANTE 				= "EX FUMANTE";
	public static final String ABSTEMIO 			= "ABSTÊMIO";
	public static final String FUMANTE 			= "FUMANTE";
	public static final String FUMANTE_PESADO 				= "FUMANTE PESADO";

}
