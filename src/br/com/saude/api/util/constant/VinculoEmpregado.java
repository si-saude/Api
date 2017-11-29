package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class VinculoEmpregado extends GenericConstant {
	
	private static VinculoEmpregado instance;
	
	private VinculoEmpregado() {
		
	}
	
	public static VinculoEmpregado getInstance() {
		if(instance==null)
			instance = new VinculoEmpregado();
		return instance;
	}
	
	public final String CONTRATADO 				= "CONTRATADO";
	public final String ESTAGI�RIO			= "ESTAGI�RIO";
	public final String PR�PRIO			= "PR�PRIO";
}