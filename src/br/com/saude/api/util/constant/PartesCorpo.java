package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class PartesCorpo extends GenericConstant {
	
	private static PartesCorpo instance;
	
	private PartesCorpo() {
		
	}
	
	public static PartesCorpo getInstance() {
		if(instance==null)
			instance = new PartesCorpo();
		return instance;
	}
	
	public static final String CABECA 				= "CABEÇA";
	public static final String MEMBRO_INFERIOR 			= "MEMBRO INFERIOR";
	public static final String MEMBRO_SUPERIOR 			= "MEMBRO SUPERIOR";
	public static final String PARTES_MULTIPLAS 				= "PARTES MULTIPLAS";
	public static final String TRONCO 				= "TRONCO";
}
