package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoCat extends GenericConstant  {
	private static TipoCat instance;
	
	private TipoCat() {
		
	}
	
	public static TipoCat getInstance() {
		if(instance == null)
			instance = new TipoCat();
		return instance;
	}
	
	public final String INICIAL 				= "INICIAL";
	public final String REABERTURA 				= "REABERTURA";
}
