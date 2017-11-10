package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class EstadoCivil extends GenericConstant {
	
	private static EstadoCivil instance;
	
	private EstadoCivil() {
		
	}
	
	public static EstadoCivil getInstance() {
		if(instance == null)
			instance = new EstadoCivil();
		return instance;
	}
	
	public final String SOLTEIRO 				= "SOLTEIRO(A)";
	public final String CASADO		= "CASADO(A)";
	public final String DIVORCIADO		= "DIVORCIADO(A)";
	public final String VIUVO		= "VIÚVO(A)";
	public final String SEPARADO		= "SEPARADO(A)";
}
