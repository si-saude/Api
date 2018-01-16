package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class StatusFilaAtendimentoOcupacional  extends GenericConstant {

	private static StatusFilaAtendimentoOcupacional instance;
	
	private StatusFilaAtendimentoOcupacional() {
		
	}
	
	public static StatusFilaAtendimentoOcupacional getInstance() {
		if(instance == null)
			instance = new StatusFilaAtendimentoOcupacional();
		return instance;
	}
	
	public final String DISPONIVEL 					= "DISPONÍVEL";
	public final String INDISPONIVEL 				= "INDISPONÍVEL";
	public final String AGUARDANDO_EMPREGADO		= "AGUARDANDO EMPREGADO";
	public final String EM_ATENDIMENTO				= "EM ATENDIMENTO";
	public final String LANCAMENTO_DE_INFORMACOES	= "LANÇAMENTO DE INFORMAÇÕES";
	public final String ALMOCO 						= "ALMOÇO";
	public final String ENCERRADO 					= "ENCERRADO";
	public final String ENCERRADO_AUTOMATICAMENTE	= "ENCERRADO AUTOMATICAMENTE";
}
