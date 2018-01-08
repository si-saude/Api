package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AcaoResultadoExame extends GenericConstant {
	
	private static AcaoResultadoExame instance;
	
	private AcaoResultadoExame() {
		
	}
	
	public static AcaoResultadoExame getInstance() {
		if(instance==null)
			instance = new AcaoResultadoExame();
		return instance;
	}
	
	public final String SOLICITADO 				= "SOLICITADO";
	public final String REALIZADO			= "REALIZADO";
	public final String RECEBIDO			= "RECEBIDO";
}
