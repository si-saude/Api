package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class DoresCorporaisIntensidade extends GenericConstant {

	private static DoresCorporaisIntensidade instance;
	
	private DoresCorporaisIntensidade() {
		
	}
	
	public static DoresCorporaisIntensidade getInstance() {
		if(instance == null)
			instance = new DoresCorporaisIntensidade();
		return instance;
	}
	
	public final String INSUPORTAVEL 				= "INSURPORTÁVEL";
	public final String SEVERO 			= "SEVERO";
	public final String MODERADO 			= "MODERADO";
	public final String PEQUENO 			= "PEQUENO";
	public final String AUSENTE 			= "AUSENTE";

}
