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
	
	public static final String HOMOLOGADO 				= "HOMOLOGADO";
	public static final String RECUSADO			= "RECUSADO";
	public static final String ANALISE_ADMINISTRATIVA			= "ANÁLISE ADMINISTRATIVA";
	public static final String ANALISE_TECNICA			= "ANÁLISE TÉCNICA";
	public static final String PENDENTE_DE_ARQUIVAMENTO			= "PENDENTE DE ARQUIVAMENTO";
	public static final String ARQUIVADO			= "ARQUIVADO";
}
