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
	
	public final String LES�O_GRAVE_MORTE 				= "LES�O GRAVE/MORTE";
	public final String PRIMEIROS_SOCORROS		= "PRIMEIROS SOCORROS";
	public final String TRATAMENTO_MEDICO		= "TRATAMENTO M�DICO";
	public final String N_A		= "N/A";
}
