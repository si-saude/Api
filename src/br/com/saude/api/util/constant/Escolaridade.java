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
	public final String MEDIO_INCOMPLETO 				= "MÉDIO - INCOMPLETO";
	public final String MEDIO_COMPLETO 				= "MÉDIO - COMPLETO";
	public final String SUPERIOR_INCOMPLETO 				= "SUPERIOR - INCOMPLETO";
	public final String SUPERIOR_COMPLETO 				= "SUPERIOR - COMPLETO";
	public final String POS_GRADUACAO_LATO_SENSO_INCOMPLETO 				= "PÓS GRADUAÇÃO (LATO SENSO) - INCOMPLETO";
	public final String POS_GRADUACAO_LATO_SENSO_COMPLETO 				= "PÓS GRADUAÇÃO (LATO SENSO) - COMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_MESTRADO_INCOMPLETO 				= "PÓS GRADUAÇÃO (STRICTO SENSO, NÍVEL MESTRADO) - INCOMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_MESTRADO_COMPLETO 				= "PÓS GRADUAÇÃO (STRICTO SENSO, NÍVEL MESTRADO) - COMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_DOUTOR_INCOMPLETO 				= "PÓS GRADUAÇÃO (STRICTO SENSO, NÍVEL DOUTOR) - INCOMPLETO";
	public final String POS_GRADUACAO_STRICTO_SENSO_NIVEL_DOUTOR_COMPLETO 				= "PÓS GRADUAÇÃO (STRICTO SENSO, NÍVEL DOUTOR) - COMPLETO";
}
