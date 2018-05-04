package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusRiscoPotencial extends GenericConstant {

	private static StatusRiscoPotencial instance;
	
	private StatusRiscoPotencial() {
		
	}
	
	public static StatusRiscoPotencial getInstance() {
		if(instance == null)
			instance = new StatusRiscoPotencial();
		return instance;
	}
	
	public final String ABERTO 					= "ABERTO";
	public final String PLANEJAMENTO			= "PLANEJAMENTO";
	public final String VALIDADO				= "VALIDADO";
	public final String ENCERRADO				= "ENCERRADO";
}
