package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AptidaoFisicaBrigadista extends GenericConstant {

	private static AptidaoFisicaBrigadista instance;
	
	private AptidaoFisicaBrigadista() {
		
	}
	
	public static AptidaoFisicaBrigadista getInstance() {
		if(instance == null)
			instance = new AptidaoFisicaBrigadista();
		return instance;
	}
	
	public final String SATISFATORIO 				= "SATISFATÓRIO";
	public final String INSATISFATORIO		= "INSATISFATÓRIO";
	public final String PENDENTE		= "PENDENTES";
	public final String NAO_AVALIADO		= "NÃO AVALIADO";
	public final String NAO_APLICAVEL		= "NÃO APLICÁVEL";

}
