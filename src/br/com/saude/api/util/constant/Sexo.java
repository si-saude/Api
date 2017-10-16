package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Sexo extends GenericConstant  {

	private static Sexo instance;
	
	private Sexo() {
		
	}
	
	public static Sexo getInstance() {
		if(instance==null)
			instance = new Sexo();
		return instance;
	}
	
	public final String MASCULINO			= "MASCULINO";
	public final String FEMININO			= "FEMININO";
}
