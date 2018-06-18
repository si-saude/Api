package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class AtividadeFornecedor extends GenericConstant  {

	private static AtividadeFornecedor instance;
	
	private AtividadeFornecedor() {
		
	}
	
	public static AtividadeFornecedor getInstance() {
		if(instance==null)
			instance = new AtividadeFornecedor();
		return instance;
	}
	
	public final String ACADEMIA			= "ACADEMIA";
	public final String CLÍNICA			= "CLINICA";
}
