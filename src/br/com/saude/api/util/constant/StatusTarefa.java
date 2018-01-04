package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusTarefa extends GenericConstant {

	private static StatusTarefa instance;
	
	private StatusTarefa() {
		
	}
	
	public static StatusTarefa getInstance() {
		if(instance == null)
			instance = new StatusTarefa();
		return instance;
	}
	
	public final String ABERTA 				= "ABERTA";
	public final String CONFIRMADA 			= "CONFIRMADA";
	public final String EXECUCAO 			= "EXECUÇÃO";
	public final String CONCLUIDA 			= "CONCLUÍDA";
	public final String CANCELADA 			= "CANCELADA";
}
