package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Fuma extends GenericConstant {

	private static Fuma instance;
	
	private Fuma() {
		
	}
	
	public static Fuma getInstance() {
		if(instance == null)
			instance = new Fuma();
		return instance;
	}
	
	public final String NAO 			= "NÃO";
	public final String FUMANTE 		= "FUMANTE";
	public final String EXFUMANTE		= "EX-FUMANTE";
	public final String ABSTEMIO		= "ABSTÊMIO";

}
