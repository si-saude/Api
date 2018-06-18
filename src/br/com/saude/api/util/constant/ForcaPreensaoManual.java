package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class ForcaPreensaoManual extends GenericConstant {

	private static ForcaPreensaoManual instance;
	
	private ForcaPreensaoManual() {
		
	}
	
	public static ForcaPreensaoManual getInstance() {
		if(instance == null)
			instance = new ForcaPreensaoManual();
		return instance;
	}
	
	public final String NAO_AVALIADO 				= "NÃO AVALIADO";
	public final String PENDENTE 				= "PENDENTE";
	public final String FRACO 				= "FRACO";
	public final String REGULAR		= "REGULAR";
	public final String MEDIO		= "MÉDIO";
	public final String BOA		= "BOA";
	public final String EXCELENTE		= "EXCELENTE";

}
