package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Operador extends GenericConstant {

	private static Operador instance;
	
	private Operador() {
		
	}
	
	public static Operador getInstance() {
		if(instance==null)
			instance = new Operador();
		return instance;
	}
	
	public static final String MENOR 				= "<";
	public static final String MENOR_IGUAL 			= "<=";
	public static final String MAIOR_IGUAL 			= ">=";
	public static final String MAIOR 				= ">";
	public static final String IGUAL 				= "=";
	public static final String DIFERENTE 			= "!=";
}