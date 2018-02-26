package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoPerguntaFichaColeta extends GenericConstant {

	private static TipoPerguntaFichaColeta instance;
	
	private TipoPerguntaFichaColeta() {
		
	}
	
	public static TipoPerguntaFichaColeta getInstance() {
		if(instance == null)
			instance = new TipoPerguntaFichaColeta();
		return instance;
	}
	
	public static final String DOUBLE 				= "DOUBLE";
	public static final String INTEIRO 				= "INTEIRO";
	public static final String TEXTO 				= "TEXTO";
	public static final String SIM_OU_NAO 			= "SIM OU NÃO";
}
