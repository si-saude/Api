package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Funcionalidade extends GenericConstant {
	
	private static Funcionalidade instance;
	
	private Funcionalidade() {
		
	}
	
	public static Funcionalidade getInstance() {
		if(instance==null)
			instance = new Funcionalidade();
		return instance;
	}
	
	public static final String EMPREGADO_LISTAR 				= "EMPREGADO_LISTAR";
	public static final String EMPREGADO_ADICIONAR 			    = "EMPREGADO_ADICIONAR";
	public static final String EMPREGADO_ALTERAR 				= "EMPREGADO_ALTERAR";
	public static final String EMPREGADO_REMOVER 				= "EMPREGADO_REMOVER";
}
