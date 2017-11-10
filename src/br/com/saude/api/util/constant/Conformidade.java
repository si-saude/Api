package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Conformidade extends GenericConstant  {

	private static Conformidade instance;
	
	private Conformidade() {
		
	}
	
	public static Conformidade getInstance() {
		if(instance==null)
			instance = new Conformidade();
		return instance;
	}
	
	public static final String LEGAL 				= "LEGAL";
	public static final String EMPRESARIAL			= "EMPRESARIAL";
}
