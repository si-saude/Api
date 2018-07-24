package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AvaliacaoEficacia extends GenericConstant  {

	private static AvaliacaoEficacia instance;
	
	private AvaliacaoEficacia() {
		
	}
	
	public static AvaliacaoEficacia getInstance() {
		if(instance==null)
			instance = new AvaliacaoEficacia();
		return instance;
	}
	
	public final String EFICAZ				= "E";
	public final String INEFICAZ			= "I";
	public final String NAOSEAPLICA			= "NA";
}
