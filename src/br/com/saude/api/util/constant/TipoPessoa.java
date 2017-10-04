package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoPessoa extends GenericConstant {
	
	private static TipoPessoa instance;
	
	private TipoPessoa() {
		
	}
	
	public static TipoPessoa getInstance() {
		if(instance==null)
			instance = new TipoPessoa();
		return instance;
	}
	
	public final String FISICA 				= "FÍSICA";
	public final String JURIDICA			= "JURÍDICA";
}
