package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Gravidade extends GenericConstant  {
	private static Gravidade instance;
	
	private Gravidade() {
		
	}
	
	public static Gravidade getInstance() {
		if(instance == null)
			instance = new Gravidade();
		return instance;
	}
	
	public final String LESÃO_GRAVE_MORTE 				= "LESÃO GRAVE/MORTE";
	public final String PRIMEIROS_SOCORROS		= "PRIMEIROS SOCORROS";
	public final String TRATAMENTO_MEDICO		= "TRATAMENTO MÉDICO";
	public final String N_A		= "N/A";
}
