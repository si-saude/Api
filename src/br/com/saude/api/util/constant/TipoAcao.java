package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoAcao extends GenericConstant {

	private static TipoAcao instance;
	
	private TipoAcao() {
		
	}
	
	public static TipoAcao getInstance() {
		if(instance == null)
			instance = new TipoAcao();
		return instance;
	}
	
	public final String AGENDAMENTO 				= "AGENDAMENTO";
	public final String ATENDIMENTO 				= "ATENDIMENTO";
	public final String AVALIACAO 					= "AVALIAÇÃO";
	public final String OUTROS 						= "OUTROS";
}
