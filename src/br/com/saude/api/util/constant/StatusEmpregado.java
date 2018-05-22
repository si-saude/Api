package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusEmpregado extends GenericConstant  {

	private static StatusEmpregado instance;
	
	private StatusEmpregado() {
		
	}
	
	public static StatusEmpregado getInstance() {
		if(instance == null)
			instance = new StatusEmpregado();
		return instance;
	}
	
	public final String ATIVO 				= "ATIVO";
	public final String AFASTADO			= "AFASTADO";
	public final String CEDIDO				= "CEDIDO";
	public final String DESLIGADO			= "DESLIGADO";
	public final String FALECIDO			= "FALECIDO";
	public final String TRANSFERIDO			= "TRANSFERIDO";
	public final String SINDICALISTA	    = "SINDICALISTA";
	public final String SUSPENSO			= "SUSPENSO";	
}
