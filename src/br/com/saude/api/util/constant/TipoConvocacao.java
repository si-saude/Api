package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoConvocacao extends GenericConstant {

	private static TipoConvocacao instance;
	
	private TipoConvocacao() {
		
	}
	
	public static TipoConvocacao getInstance() {
		if(instance==null)
			instance = new TipoConvocacao();
		return instance;
	}
	
	public static final String PERIODICO 				= "PERIÓDICO";
	public static final String RETORNO_AO_TRABALHO		= "RETORNO AO TRABALHO";
	public static final String MUDANCA_DE_FUNCAO		= "MUDANÇA DE FUNÇÃO";
	public static final String DEMISSIONAL				= "DEMISSIONAL";
	public static final String ESPECIAL				= "ESPECIAL";
	public static final String SUPLETIVO				= "SUPLETIVO";
	public static final String ADMISSIONAL				= "ADMISSIONAL";
	public static final String PERICIAL				= "PERICIAL";
}
