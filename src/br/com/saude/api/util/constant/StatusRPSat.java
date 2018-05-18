package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusRPSat extends GenericConstant {

	private static StatusRPSat instance;
	
	private StatusRPSat() {
		
	}
	
	public static StatusRPSat getInstance() {
		if(instance == null)
			instance = new StatusRPSat();
		return instance;
	}
	
	public final String ACEITAVEL 					= "ACEIT�VEL";
	public final String TOLERAVEL					= "TOLER�VEL";
	public final String INACEITAVEL					= "INACEIT�VEL";
}
