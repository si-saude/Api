package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusAtestado extends GenericConstant{
	private static StatusAtestado instance;
	
	private StatusAtestado() {
		
	}
	
	public static StatusAtestado getInstance() {
		if(instance==null)
			instance = new StatusAtestado();
		return instance;
	}
	
	public static final String EM_ANALISE 				= "EM ANÁLISE";
	public static final String HOMOLOGADO 				= "HOMOLOGADO";
	public static final String RECUSADO			= "RECUSADO";
}
