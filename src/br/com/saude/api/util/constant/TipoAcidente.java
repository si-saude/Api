package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoAcidente extends GenericConstant  {
	private static TipoAcidente instance;
	
	private TipoAcidente() {
		
	}
	
	public static TipoAcidente getInstance() {
		if(instance == null)
			instance = new TipoAcidente();
		return instance;
	}
	
	public final String EQUIPARADO 				= "EQUIPARADO";
	public final String TIPICO 				= "TÍPICO";
	public final String TRAJETO 					= "TRAJETO";
}
