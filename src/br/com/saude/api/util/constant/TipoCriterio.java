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
	
	public static final String SEXO 					= "SEXO";
	public static final String IDADE					= "IDADE";
	public static final String EXAME					= "EXAME";
	public static final String FUNCAO					= "FUN��O";
	public static final String CARGO					= "CARGO";
	public static final String EXIGE_RELATORIO_MEDICO	= "EXIGE RELAT�RIO M�DICO";
}
