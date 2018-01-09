package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusFilaEsperaOcupacional extends GenericConstant {

	private static StatusFilaEsperaOcupacional instance;
	
	private StatusFilaEsperaOcupacional() {
		
	}
	
	public static StatusFilaEsperaOcupacional getInstance() {
		if(instance == null)
			instance = new StatusFilaEsperaOcupacional();
		return instance;
	}
	
	public final String AGUARDANDO 				= "AGUARDANDO";
	public final String EM_ATENDIMENTO			= "EM ATENDIMENTO";
	public final String AUSENTE 				= "AUSENTE";
	public final String ALMOCO 					= "ALMOÇO";
	public final String FINALIZADO				= "FINALIZADO";
}
