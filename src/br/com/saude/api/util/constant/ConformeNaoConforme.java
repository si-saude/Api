package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class ConformeNaoConforme extends GenericConstant  {

	private static ConformeNaoConforme instance;
	
	private ConformeNaoConforme() {
		
	}
	
	public static ConformeNaoConforme getInstance() {
		if(instance==null)
			instance = new ConformeNaoConforme();
		return instance;
	}
	
	public final String CONFORME			= "CONFORME";
	public final String NAO_CONFORME			= "NÃO CONFORME";
}
