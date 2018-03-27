package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoContato extends GenericConstant {

	private static TipoContato instance;
	
	private TipoContato() {
		
	}
	
	public static TipoContato getInstance() {
		if(instance == null)
			instance = new TipoContato();
		return instance;
	}
	
	public final String ATENDIMENTO_GRUPO 				= "ATENDIMENTO EM GRUPO";
	public final String ATENDIMENTO_INDIVIDUAL			= "ATENDIMENTO INDIVIDUAL";
	public final String CONTATO_TELEFONICO 				= "CONTATO TELEF�NICO";
	public final String CONTATO_VIRTUAL 				= "CONTATO VIRTUAL";
	public final String VISITA_TECNICA 					= "VISITA T�CNICA";
	public final String OUTROS 							= "OUTROS";
}
