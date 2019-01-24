package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Flexibilidade extends GenericConstant {

	private static Flexibilidade instance;
	
	private Flexibilidade() {
		
	}
	
	public static Flexibilidade getInstance() {
		if(instance == null)
			instance = new Flexibilidade();
		return instance;
	}
	
	public final String EXCELENTE 				= "EXCELENTE";
	public final String ACIMA_DA_MEDIA 				= "ACIMA DA MÉDIA";
	public final String MEDIA 				= "MÉDIA";
	public final String ABAIXO_DA_MEDIA		= "ABAIXO DA MÉDIA";
	public final String RUIM		= "RUIM";
}
