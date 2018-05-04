package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TempoMeses extends GenericConstant {

	private static TempoMeses instance;
	
	private TempoMeses() {
		
	}
	
	public static TempoMeses getInstance() {
		if(instance == null)
			instance = new TempoMeses();
		return instance;
	}
	
	public final String MENOS_1MES 				= "MENOS DE 1 MÊS";
	public final String DE1_3MESES 			= "DE 1 A 3 MESES";
	public final String DE4_7MESES 			= "DE 4 A 7 MESES";
	public final String DE7_10MESES 			= "DE 7 A 10 MESES";
	public final String DE11_12MESES 			= "DE 11 A 12 MESES";
}
