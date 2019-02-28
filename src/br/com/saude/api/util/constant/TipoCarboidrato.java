package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoCarboidrato extends GenericConstant {

	private static TipoCarboidrato instance;
	
	private TipoCarboidrato() {
		
	}
	
	public static TipoCarboidrato getInstance() {
		if(instance == null)
			instance = new TipoCarboidrato();
		return instance;
	}
	
	public final String SIMPLES 				= "SIMPLES";
	public final String COMPLEXO 				= "COMPLEXO";
}
