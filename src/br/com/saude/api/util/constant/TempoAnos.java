package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TempoAnos extends GenericConstant {

	private static TempoAnos instance;
	
	private TempoAnos() {
		
	}
	
	public static TempoAnos getInstance() {
		if(instance == null)
			instance = new TempoAnos();
		return instance;
	}
	
	public final String DE1_4ANOS 				= "DE 1 - 4 ANOS";
	public final String DE4_6ANOS 			= "DE 4 - 6 ANOS";
	public final String DE6_10ANOS 			= "DE 6 - 10 ANOS";
	public final String ACIMA_10ANOS 			= "ACIMA DE 6 ANOS";

}
