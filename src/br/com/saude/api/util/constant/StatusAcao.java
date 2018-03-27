package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusAcao extends GenericConstant {

	private static StatusAcao instance;
	
	private StatusAcao() {
		
	}
	
	public static StatusAcao getInstance() {
		if(instance == null)
			instance = new StatusAcao();
		return instance;
	}
	
	public final String ABERTA 					= "ABERTA";
	public final String EXECUCAO 				= "EXECUÇÃO";
	public final String ENCERRADA 				= "ENCERRADA";
}
