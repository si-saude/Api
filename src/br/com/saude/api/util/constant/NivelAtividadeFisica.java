package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class NivelAtividadeFisica extends GenericConstant {
	private static NivelAtividadeFisica instance;
	
	private NivelAtividadeFisica() {
		
	}
	
	public static NivelAtividadeFisica getInstance() {
		if(instance == null)
			instance = new NivelAtividadeFisica();
		return instance;
	}
	
	public static final String SEDENTARIO 						= "SEDENTÁRIO";
	public static final String IRREGULAR_ATIVO_B 			= "IRREGULAR ATIVO B";
	public static final String IRREGULAR_ATIVO_A		= "IRREGULAR ATIVO A";
	public static final String REGULARMENTE_ATIVO 				= "REGULARMENTE ATIVO";
	public static final String MUITO_ATIVO				= "MUITO ATIVO";
}
