package br.com.saude.api.util.constant;

public class StatusAtestado {
	private static StatusAtestado instance;
	
	private StatusAtestado() {
		
	}
	
	public static StatusAtestado getInstance() {
		if(instance==null)
			instance = new StatusAtestado();
		return instance;
	}
	
	public static final String EM_ANALISE 				= "EM AN�LISE";
	public static final String HOMOLOGADO 				= "HOMOLOGADO";
	public static final String RECUSADO			= "RECUSADO";
}
