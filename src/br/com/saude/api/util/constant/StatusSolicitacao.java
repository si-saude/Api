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
	
	public final String ABERTO 							= "ABERTO";
	public final String PLANEJADO						= "PLANEJADO";
	public final String EXECUCAO						= "EXECUÇÃO";
	public final String CONCLUIDO						= "CONCLUÍDO";
	public final String CANCELADO						= "CANCELADO";
	public final String AGUARDANDO_INFORMACAO			= "AGUARDANDO INFORMAÇÃO";

}