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
	
	public final String PERIODICO 				= "PERI�DICO";
	public final String RETORNO_AO_TRABALHO		= "RETORNO AO TRABALHO";
	public final String MUDANCA_DE_FUNCAO		= "MUDAN�A DE FUN��O";
	public final String DEMISSIONAL				= "DEMISSIONAL";
}
