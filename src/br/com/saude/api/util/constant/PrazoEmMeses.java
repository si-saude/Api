package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class PrazoEmMeses extends GenericConstant {

	private static PrazoEmMeses instance;
	
	private PrazoEmMeses() {
		
	}
	
	public static PrazoEmMeses getInstance() {
		if(instance == null)
			instance = new PrazoEmMeses();
		return instance;
	}
	
	public final String IMEDIATO			= "IMEDIATO";
	public final String UM_MES				= "1 MÊS";
	public final String DOIS_MES			= "2 MESES";
	public final String TRES_MES			= "3 MESES";
	public final String QUATRO_MES			= "4 MESES";
	public final String CINCO_MES			= "5 MESES";
	public final String SEIS_MES			= "6 MESES";
	public final String SETE_MES			= "7 MESES";
	public final String OITO_MES			= "8 MESES";
	public final String NOVE_MES			= "9 MESES";
	public final String DEZ_MES				= "10 MESES";
	public final String ONZE_MES			= "11 MESES";
	public final String DOZE_MES			= "12 MESES";
}
