package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusAso extends GenericConstant {

	private static StatusAso instance;
	
	private StatusAso() {
		
	}
	
	public static StatusAso getInstance() {
		if(instance==null)
			instance = new StatusAso();
		return instance;
	}
	
	public final String SOLICITADO 				= "SOLICITADO";
}
