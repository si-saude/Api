package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AptidaoCardiorrespiratoria extends GenericConstant {

	private static AptidaoCardiorrespiratoria instance;
	
	private AptidaoCardiorrespiratoria() {
		
	}
	
	public static AptidaoCardiorrespiratoria getInstance() {
		if(instance == null)
			instance = new AptidaoCardiorrespiratoria();
		return instance;
	}
	public final String NAO_AVALIADO 				= "NÃO AVALIADO";
	public final String PENDENTE 				= "PENDENTE";
	public final String MUITO_FRACA 				= "MUITO FRACA";
	public final String FRACA		= "FRACA";
	public final String REGULAR		= "REGULAR";
	public final String BOA		= "BOA";
	public final String EXCELENTE		= "EXCELENTE";
}
