package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoAtividadeFisica extends GenericConstant  {
	private static TipoAtividadeFisica instance;
	
	private TipoAtividadeFisica() {
		
	}
	
	public static TipoAtividadeFisica getInstance() {
		if(instance == null)
			instance = new TipoAtividadeFisica();
		return instance;
	}
	
	public final String REALIZADA 				= "REALIZADA";
	public final String ORIENTADA 				= "ORIENTADA";
}
