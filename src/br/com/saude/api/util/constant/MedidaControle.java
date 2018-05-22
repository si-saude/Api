package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class MedidaControle extends GenericConstant  {

	private static MedidaControle instance;
	
	private MedidaControle() {
		
	}
	
	public static MedidaControle getInstance() {
		if(instance==null)
			instance = new MedidaControle();
		return instance;
	}
	
	public final String SINALIZACAO_SEGURANCA			= "SINALIZA��O DE SEGURAN�A";
	public final String UTILIZACAO_EPI					= "UTILIZA��O DE EPI";
	public final String MEDIDAS_ADMINISTRATIVAS			= "MEDIDAS ADMINISTRATIVAS";
	public final String NORMAS_PROCEDIMENTOS			= "NORMAS E PROCEDIMENTOS";
	public final String TREINAMENTOS_SEGURANCA			= "TREINAMENTOS DE SEGURAN�A";
	public final String MANUNTENCAO_CORRETIVA_PREVENTIVA_PREDITIVA		= "MANUTEN��O CORRETIVA, PREVENTIVA E PREDITIVA";
}
