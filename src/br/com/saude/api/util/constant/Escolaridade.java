package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Escolaridade extends GenericConstant  {
	
	private static Escolaridade instance;
	
	private Escolaridade() {
		
	}
	
	public static Escolaridade getInstance() {
		if(instance==null)
			instance = new Escolaridade();
		return instance;
	}
	
	public final String FUNDAMENTAL_INCOMPLETO 				= "FUNDAMENTAL - INCOMPLETO";
	public final String FUNDAMENTAL_COMPLETO 				= "FUNDAMENTAL - COMPLETO";
	public final String MEDIO_INCOMPLETO 				= "M�DIO - INCOMPLETO";
	public final String MEDIO_COMPLETO 				= "M�DIO - COMPLETO";
	public final String SUPERIOR_INCOMPLETO 				= "SUPERIOR - INCOMPLETO";
	public final String SUPERIOR_COMPLETO 				= "SUPERIOR - COMPLETO";
	public final String POS_GRADUACAO_LATO_SENSO_INCOMPLETO 				= "P�S GRADUA��O (LATO SENSO) - INCOMPLETO";
	public final String POS_GRADUACAO_LATO_SENSO_COMPLETO 				= "P�S GRADUA��O (LATO SENSO) - COMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_MESTRADO_INCOMPLETO 				= "P�S GRADUA��O (STRICTO SENSO, N�VEL MESTRADO) - INCOMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_MESTRADO_COMPLETO 				= "P�S GRADUA��O (STRICTO SENSO, N�VEL MESTRADO) - COMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_DOUTOR_INCOMPLETO 				= "P�S GRADUA��O (STRICTO SENSO, N�VEL DOUTOR) - INCOMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_DOUTOR_COMPLETO 				= "P�S GRADUA��O (STRICTO SENSO, N�VEL DOUTOR) - COMPLETO";
}
