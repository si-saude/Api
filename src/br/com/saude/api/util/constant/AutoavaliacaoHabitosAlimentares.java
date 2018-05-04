package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AutoavaliacaoHabitosAlimentares extends GenericConstant {

	private static AutoavaliacaoHabitosAlimentares instance;
	
	private AutoavaliacaoHabitosAlimentares() {
		
	}
	
	public static AutoavaliacaoHabitosAlimentares getInstance() {
		if(instance==null)
			instance = new AutoavaliacaoHabitosAlimentares();
		return instance;
	}
	
	public static final String MUITO_BOM 				= "MUITO BOM";
	public static final String BOM 			= "BOM";
	public static final String REGULAR 			= "REGULAR";
	public static final String RUIM 				= "RUIM";

}
