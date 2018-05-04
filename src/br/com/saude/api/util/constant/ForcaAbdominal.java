package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class ForcaAbdominal extends GenericConstant {

	private static ForcaAbdominal instance;
	
	private ForcaAbdominal() {
		
	}
	
	public static ForcaAbdominal getInstance() {
		if(instance == null)
			instance = new ForcaAbdominal();
		return instance;
	}
	
	public final String FRACO 				= "FRACO";
	public final String REGULAR		= "REGULAR";
	public final String MEDIO		= "MÉDIO";
	public final String BOA		= "BOA";
	public final String EXCELENTE		= "EXCELENTE";
}
