package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class DentroForaPrazo extends GenericConstant  {

	private static DentroForaPrazo instance;
	
	private DentroForaPrazo() {
		
	}
	
	public static DentroForaPrazo getInstance() {
		if(instance==null)
			instance = new DentroForaPrazo();
		return instance;
	}
	
	public static final String DENTRO_DO_PRAZO 				= "DENTRO DO PRAZO";
	public static final String FORA_DO_PRAZO			= "FORA DO PRAZO";

}
