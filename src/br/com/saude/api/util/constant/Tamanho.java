package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Tamanho extends GenericConstant {

	private static Tamanho instance;
	
	private Tamanho() {
		
	}
	
	public static Tamanho getInstance() {
		if(instance == null)
			instance = new Tamanho();
		return instance;
	}
	
	public final String GRANDE 		= "GRANDE";
	public final String MEDIO 		= "MÉDIO";
	public final String PEQUENO		= "PEQUENO";

}
