package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class ClassificacaoAtividade extends GenericConstant  {

	private static ClassificacaoAtividade instance;
	
	private ClassificacaoAtividade() {
		
	}
	
	public static ClassificacaoAtividade getInstance() {
		if(instance==null)
			instance = new ClassificacaoAtividade();
		return instance;
	}
	
	public final String LEVE				= "LEVE";
	public final String MODERADO			= "MODERADO";
	public final String VIGOROSO			= "VIGOROSO";
}
