package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoResultadoExame extends GenericConstant {
	
	private static TipoResultadoExame instance;
	
	private TipoResultadoExame() {
		
	}
	
	public static TipoResultadoExame getInstance() {
		if(instance==null)
			instance = new TipoResultadoExame();
		return instance;
	}
	
	public final String PRECLINICO 				= "PRECLINICO";
	public final String POSCLINICO			= "POSCLINICO";
	public final String ESPECIAL			= "ESPECIAL";
}
