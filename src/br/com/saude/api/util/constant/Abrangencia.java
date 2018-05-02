package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Abrangencia extends GenericConstant {

	private static Abrangencia instance;
	
	private Abrangencia() {
		
	}
	
	public static Abrangencia getInstance() {
		if(instance==null)
			instance = new Abrangencia();
		return instance;
	}
	
	public final String FAMILIAR 				= "FAMILIAR";
	public final String PESSOAL			= "PESSOAL";
	public final String FAMILIAR_PESSOA			= "FAMILIAR/PESSOAL";
}
