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
	public static final String EXAME_FISICO 						= "EXAME FÍSICO";
	public static final String EXAME_ODONTOLOGICO 					= "EXAME ODONTOLÓGICO";
	public static final String HABITOS_ALIMENTARES 					= "HÁBITOS ALIMENTARES";
	public static final String TESTE_FAGERSTROM 					= "TESTE DE FAGERSTRÖM";
	public static final String NIVEL_ESTRESSE 						= "NÍVEL DE ESTRESSE";
}
