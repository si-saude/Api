package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusSolicitacao extends GenericConstant {
	
	private static StatusSolicitacao instance;
	
	private StatusSolicitacao() {
		
	}
	
	public static StatusSolicitacao getInstance() {
		if(instance==null)
			instance = new StatusSolicitacao();
		return instance;
	}
	
	public static final String ABERTO 							= "ABERTO";
	public static final String PLANEJADO						= "PLANEJADO";
	public static final String EXECUCAO						= "EXECUÇÃO";
	public static final String CONCLUIDO						= "CONCLUÍDO";
	public static final String CANCELADO						= "CANCELADO";
	public static final String AGUARDANDO_INFORMACAO			= "AGUARDANDO INFORMAÇÃO";

}