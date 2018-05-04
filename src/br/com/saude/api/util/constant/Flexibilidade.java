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
	
	public final String FRACO 				= "FRACO";
	public final String REGULAR		= "REGULAR";
	public final String MEDIO		= "MÉDIO";
	public final String BOA		= "BOA";
	public final String EXCELENTE		= "EXCELENTE";
}
