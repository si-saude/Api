package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AptidaoAso extends GenericConstant {

	private static AptidaoAso instance;
	
	private AptidaoAso() {
		
	}
	
	public static AptidaoAso getInstance() {
		if(instance==null)
			instance = new AptidaoAso();
		return instance;
	}
	
	public final String APTO 		   		= "APTO";
	public final String INAPTO				= "INAPTO";
}
