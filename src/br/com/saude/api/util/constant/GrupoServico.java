package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class GrupoServico extends GenericConstant {

	private static GrupoServico instance;
	
	private GrupoServico() {
		
	}
	
	public static GrupoServico getInstance() {
		if(instance == null)
			instance = new GrupoServico();
		return instance;
	}
	
	public static final String ATENDIMENTO 						= "ATENDIMENTO";
	public static final String ATENDIMENTO_OCUPACIONAL 			= "ATENDIMENTO OCUPACIONAL";
	public static final String ATENDIMENTO_PROGRAMA_SAUDE		= "ATENDIMENTO DE PROGRAMAS DE SAÚDE";
	public static final String PRONTO_ATENDIMENTO 				= "PRONTO ATENDIMENTO";
	public static final String HOMOLOGACAO_ATESTADO				= "HOMOLOGAÇÃO DE ATESTADO";
	public static final String EDUCACAO_SAUDE					= "EDUCAÇÃO EM SAÚDE";
	public static final String GESTAO 							= "GESTÃO";
	public static final String INSPECAO 						= "INSPEÇÃO";
	public static final String MONITORAMENTO 					= "MONITORAMENTO";
	public static final String PAGAMENTO 						= "PAGAMENTO";
	public static final String PARECER 							= "PARECER";
	public static final String TRANSPORTE 						= "REQUISIÇÃO DE TRANSPORTE";
	public static final String SAUDE_OPERACIONAL 	 			= "SAÚDE OPERACIONAL";
}
