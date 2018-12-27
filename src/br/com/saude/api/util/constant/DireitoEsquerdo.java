package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class DireitoEsquerdo extends GenericConstant  {

	private static DireitoEsquerdo instance;
	
	private DireitoEsquerdo() {
		
	}
	
	public static DireitoEsquerdo getInstance() {
		if(instance==null)
			instance = new DireitoEsquerdo();
		return instance;
	}
	
	public static final String DIREITO 				= "DIREITO";
	public static final String ESQUERDO			= "ESQUERDO";

}
