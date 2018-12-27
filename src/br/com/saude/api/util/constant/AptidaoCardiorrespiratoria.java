package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AptidaoCardiorrespiratoria extends GenericConstant {

	private static AptidaoCardiorrespiratoria instance;
	
	private AptidaoCardiorrespiratoria() {
		
	}
	
	public static AptidaoCardiorrespiratoria getInstance() {
		if(instance == null)
			instance = new AptidaoCardiorrespiratoria();
		return instance;
	}
	
	public final String MUITO_BAIXO 				= "MUITO BAIXO";
	public final String BAIXO 				= "BAIXO";
	public final String RAZOAVEL		= "RAZOÁVEL";
	public final String MODERADO		= "MODERADO";
	public final String BOM		= "BOM";
	public final String MUITO_BOM		= "MUITO BOM";
	public final String EXCELENTE		= "EXCELENTE";
}
