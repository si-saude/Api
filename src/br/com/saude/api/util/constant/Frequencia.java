package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Frequencia extends GenericConstant {

	private static Frequencia instance;
	
	private Frequencia() {
		
	}
	
	public static Frequencia getInstance() {
		if(instance == null)
			instance = new Frequencia();
		return instance;
	}
	
	public final String TODO_DIA 				= "TODO DIA";
	public final String MUITAS_VEZES_POR_DIA		= "MUITAS VEZES POR DIA";
	public final String UMA_VEZ_POR_DIA		= "1 VEZ POR DIA";
	public final String TRES_QUATRO_VEZES_POR_SEMANA		= "3-4 VEZES POR SEMANA";
	public final String UMA_DUAS_VEZES_POR_SEMANA		= "1-2 VEZES POR SEMANA";

}
