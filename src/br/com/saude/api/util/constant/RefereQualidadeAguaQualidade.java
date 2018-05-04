package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class RefereQualidadeAguaQualidade extends GenericConstant {

	private static RefereQualidadeAguaQualidade instance;
	
	private RefereQualidadeAguaQualidade() {
		
	}
	
	public static RefereQualidadeAguaQualidade getInstance() {
		if(instance==null)
			instance = new RefereQualidadeAguaQualidade();
		return instance;
	}
	
	public static final String BOA 				= "BOA";
	public static final String REGULAR 			= "REGULAR";
	public static final String RUIM 			= "RUIM";

}
