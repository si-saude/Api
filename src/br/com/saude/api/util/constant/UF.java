package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class UF extends GenericConstant {
	private static UF instance;
	
	private UF() {
		
	}
	
	public static UF getInstance() {
		if(instance==null)
			instance = new UF();
		return instance;
	}
	
	public final String ES 				= "ES";
	public final String BA			= "BA";
	public final String N_A			= "N/A";
}
