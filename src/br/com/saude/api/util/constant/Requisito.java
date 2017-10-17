package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Requisito extends GenericConstant {

	private static Requisito instance;
	
	private Requisito() {
		
	}
	
	public static Requisito getInstance() {
		if(instance == null)
			instance = new Requisito();
		return instance;
	}
	
	public final String CONFORMIDADE_LEGAL 				= "CONFORMIDADE LEGAL";
	public final String CONFORMIDADE_EMPRESARIAL		= "CONFORMIDADE EMPRESARIAL";
}
