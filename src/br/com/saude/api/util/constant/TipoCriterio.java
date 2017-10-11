package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoCriterio extends GenericConstant  {

	private static TipoCriterio instance;
	
	private TipoCriterio() {
		
	}
	
	public static TipoCriterio getInstance() {
		if(instance == null)
			instance = new TipoCriterio();
		return instance;
	}
	
	public final String SEXO 			= "SEXO";
	public final String IDADE			= "IDADE";
	public final String EXAME			= "EXAME";
}
