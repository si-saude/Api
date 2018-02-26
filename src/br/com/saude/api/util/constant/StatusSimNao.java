package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusSimNao extends GenericConstant {

	private static StatusSimNao instance;
	
	private StatusSimNao() {
		
	}
	
	public static StatusSimNao getInstance() {
		if(instance == null)
			instance = new StatusSimNao();
		return instance;
	}
	
	public static final String SIM			= "SIM";
	public static final String NAO			= "NÃO";
}
