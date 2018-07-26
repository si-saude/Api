package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class SituacaoEmpregado extends GenericConstant  {

	private static SituacaoEmpregado instance;
	
	private SituacaoEmpregado() {
		
	}
	
	public static SituacaoEmpregado getInstance() {
		if(instance==null)
			instance = new SituacaoEmpregado();
		return instance;
	}
	
	public final String AFASTADO			= "AFASTADO";
	public final String ALTA			= "ALTA";
}
