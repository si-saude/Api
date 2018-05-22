package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class MeioPropagacao extends GenericConstant  {

	private static MeioPropagacao instance;
	
	private MeioPropagacao() {
		
	}
	
	public static MeioPropagacao getInstance() {
		if(instance==null)
			instance = new MeioPropagacao();
		return instance;
	}
	
	public final String AR			= "AR";
	public final String LIQUIDO		= "LÍQUIDO";
	public final String SOLIDO		= "SÓLIDO";
}
