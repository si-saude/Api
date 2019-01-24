package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoAtendimento extends GenericConstant  {
	private static TipoAtendimento instance;
	
	private TipoAtendimento() {
		
	}
	
	public static TipoAtendimento getInstance() {
		if(instance == null)
			instance = new TipoAtendimento();
		return instance;
	}
	
	public final String PERIODICO 				= "PERIÓDICO";
	public final String PROAF 				= "PROAF";
	public final String PREVENCAO 				= "PREVENÇÃO";
}
