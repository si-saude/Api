package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AplicavelNaoAplicavel extends GenericConstant {
	
	private static AplicavelNaoAplicavel instance;
	
	private AplicavelNaoAplicavel() {
		
	}
	
	public static AplicavelNaoAplicavel getInstance() {
		if(instance==null)
			instance = new AplicavelNaoAplicavel();
		return instance;
	}
	
	public final String APLICAVEL 				= "APLIC�VEL";
	public final String NAO_APLICAVEL			= "N�O APLIC�VEL";

}
