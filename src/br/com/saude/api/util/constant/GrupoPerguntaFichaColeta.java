package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class GrupoPerguntaFichaColeta extends GenericConstant {

	private static GrupoPerguntaFichaColeta instance;
	
	private GrupoPerguntaFichaColeta() {
		
	}
	
	public static GrupoPerguntaFichaColeta getInstance() {
		if(instance == null)
			instance = new GrupoPerguntaFichaColeta();
		return instance;
	}
	
	public static final String ANAMNESE 							= "ANAMNESE";
	public static final String EXAME_FISICO 						= "EXAME F�SICO";
	public static final String EXAME_ODONTOLOGICO 					= "EXAME ODONTOL�GICO";
	public static final String HABITOS_ALIMENTARES 					= "H�BITOS ALIMENTARES";
	public static final String TESTE_FAGERSTROM 					= "TESTE DE FAGERSTR�M";
	public static final String NIVEL_ESTRESSE 						= "N�VEL DE ESTRESSE";
}
