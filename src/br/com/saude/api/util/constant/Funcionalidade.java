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
	public static final String EMPREGADO_DETALHE 				= "EMPREGADO_DETALHE";
	public static final String BASE_LISTAR						= "BASE_LISTAR";
	public static final String BASE_ADICIONAR 			    	= "BASE_ADICIONAR";
	public static final String BASE_ALTERAR		 				= "BASE_ALTERAR";
	public static final String BASE_REMOVER 					= "BASE_REMOVER";
	public static final String BASE_DETALHE 					= "BASE_DETALHE";
	public static final String CARGO_LISTAR						= "CARGO_LISTAR";
	public static final String CARGO_ADICIONAR 			    	= "CARGO_ADICIONAR";
	public static final String CARGO_ALTERAR		 			= "CARGO_ALTERAR";
	public static final String CARGO_REMOVER 					= "CARGO_REMOVER";
	public static final String CARGO_DETALHE 					= "CARGO_DETALHE";
}
