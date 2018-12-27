package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Intensidade extends GenericConstant {

	private static Intensidade instance;
	
	private Intensidade() {
		
	}
	
	public static Intensidade getInstance() {
		if(instance == null)
			instance = new Intensidade();
		return instance;
	}
	
	public static final String INSUPORTAVEL 					= "INSUPORTÁVEL";
	public static final String SEVERA 							= "SEVERA";
	public static final String MODERADA							= "MODERADA";
	public static final String PEQUENA			 				= "PEQUENA";
	public static final String AUSENTE							= "AUSENTE";
}
