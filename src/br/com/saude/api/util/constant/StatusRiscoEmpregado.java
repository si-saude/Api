package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusRiscoEmpregado extends GenericConstant {

	private static StatusRiscoEmpregado instance;
	
	private StatusRiscoEmpregado() {
		
	}
	
	public static StatusRiscoEmpregado getInstance() {
		if(instance == null)
			instance = new StatusRiscoEmpregado();
		return instance;
	}
	
	public final String REALIZADO			= "REALIZADO";
	public final String VALIDADO			= "VALIDADO";
	public final String REAVALIADO			= "REAVALIADO";
}
