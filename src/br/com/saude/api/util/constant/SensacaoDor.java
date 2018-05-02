package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class SensacaoDor extends GenericConstant {

	private static SensacaoDor instance;
	
	private SensacaoDor() {
		
	}
	
	public static SensacaoDor getInstance() {
		if(instance == null)
			instance = new SensacaoDor();
		return instance;
	}
	
	public final String INSUPORTAVAL 				= "INSUPORTAVEL";
	public final String SEVERO		= "SEVERO";
	public final String MODERADO		= "MODERADO";
	public final String PEQUENO		= "PEQUENO";
	public final String AUSENTE		= "AUSENTE";
}
