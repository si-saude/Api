package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class Funcionalidade extends GenericConstant {
	
	private static Funcionalidade instance;
	
	private Funcionalidade() {
		
	}
	
	public static Funcionalidade getInstance() {
		if(instance==null)
			instance = new Funcionalidade();
		return instance;
	}
	
	public static final String AUDITORIA_RESULTADO_EXAME_LISTAR						= "AUDITORIA-RESULTADO-EXAME_LISTAR";
	public static final String AUDITORIA_RESULTADO_EXAME_ADICIONAR 			    	= "AUDITORIA-RESULTADO-EXAME_ADICIONAR";
	public static final String AUDITORIA_RESULTADO_EXAME_ALTERAR		 			= "AUDITORIA-RESULTADO-EXAME_ALTERAR";
	public static final String AUDITORIA_RESULTADO_EXAME_REMOVER 					= "AUDITORIA-RESULTADO-EXAME_REMOVER";
	public static final String AUDITORIA_RESULTADO_EXAME_DETALHE 					= "AUDITORIA-RESULTADO-EXAME_DETALHE";
	public static final String CONVOCACAO_LISTAR						= "CONVOCACAO_LISTAR";
	public static final String CONVOCACAO_ADICIONAR 			    	= "CONVOCACAO_ADICIONAR";
	public static final String CONVOCACAO_ALTERAR		 			= "CONVOCACAO_ALTERAR";
	public static final String CONVOCACAO_REMOVER 					= "CONVOCACAO_REMOVER";
	public static final String CONVOCACAO_DETALHE 					= "CONVOCACAO_DETALHE";
	public static final String EMPREGADO_CONVOCACAO_LISTAR						= "EMPREGADO-CONVOCACAO_LISTAR";
	public static final String EMPREGADO_CONVOCACAO_ADICIONAR 			    	= "EMPREGADO-CONVOCACAO_ADICIONAR";
	public static final String EMPREGADO_CONVOCACAO_ALTERAR		 				= "EMPREGADO-CONVOCACAO_ALTERAR";
	public static final String EMPREGADO_CONVOCACAO_REMOVER 					= "EMPREGADO-CONVOCACAO_REMOVER";
	public static final String EMPREGADO_CONVOCACAO_DETALHE 					= "EMPREGADO-CONVOCACAO_DETALHE";
	public static final String ATENDIMENTO_LISTAR						= "CONVOCACAO_LISTAR";
	public static final String ATENDIMENTO_ADICIONAR 			    	= "ATENDIMENTO_ADICIONAR";
	public static final String ATENDIMENTO_ALTERAR		 			= "ATENDIMENTO_ALTERAR";
	public static final String ATENDIMENTO_REMOVER 					= "ATENDIMENTO_REMOVER";
	public static final String ATENDIMENTO_DETALHE 					= "ATENDIMENTO_DETALHE";
	public static final String EXAME_LISTAR						= "EXAME_LISTAR";
	public static final String EXAME_ADICIONAR 			    	= "EXAME_ADICIONAR";
	public static final String EXAME_ALTERAR		 				= "EXAME_ALTERAR";
	public static final String EXAME_REMOVER 					= "EXAME_REMOVER";
	public static final String EXAME_DETALHE 					= "EXAME_DETALHE";
	public static final String AUDITORIA_ASO_LISTAR						= "AUDITORIA-ASO_LISTAR";
	public static final String AUDITORIA_ASO_ADICIONAR 			    	= "AUDITORIA-ASO_ADICIONAR";
	public static final String AUDITORIA_ASO_ALTERAR		 			= "AUDITORIA-ASO_ALTERAR";
	public static final String AUDITORIA_ASO_REMOVER 					= "AUDITORIA-ASO_REMOVER";
	public static final String AUDITORIA_ASO_DETALHE 					= "AUDITORIA-ASO_DETALHE";
	public static final String EMPREGADO_LISTAR 				= "EMPREGADO_LISTAR";
	public static final String EMPREGADO_ADICIONAR 			    = "EMPREGADO_ADICIONAR";
	public static final String EMPREGADO_ALTERAR 				= "EMPREGADO_ALTERAR";
	public static final String EMPREGADO_REMOVER 				= "EMPREGADO_REMOVER";
	public static final String EMPREGADO_DETALHE 				= "EMPREGADO_DETALHE";
	public static final String BASE_LISTAR						= "BASE_LISTAR";
	public static final String BASE_ADICIONAR 			    	= "BASE_ADICIONAR";
	public static final String BASE_ALTERAR		 				= "BASE_ALTERAR";
	public static final String BASE_REMOVER 					= "BASE_REMOVER";
	public static final String BASE_DETALHE 					= "BASE_DETALHE";
	public static final String CARGO_LISTAR						= "CARGO_LISTAR";
	public static final String CARGO_ADICIONAR 			    	= "CARGO_ADICIONAR";
	public static final String CARGO_ALTERAR		 			= "CARGO_ALTERAR";
	public static final String CARGO_REMOVER 					= "CARGO_REMOVER";
	public static final String CARGO_DETALHE 					= "CARGO_DETALHE";
	public static final String USUARIO_LISTAR						= "USUARIO_LISTAR";
	public static final String USUARIO_ADICIONAR 			    	= "USUARIO_ADICIONAR";
	public static final String USUARIO_ALTERAR		 			= "USUARIO_ALTERAR";
	public static final String USUARIO_REMOVER 					= "USUARIO_REMOVER";
	public static final String USUARIO_DETALHE 					= "USUARIO_DETALHE";
	public static final String PERFIL_LISTAR						= "PERFIL_LISTAR";
	public static final String PERFIL_ADICIONAR 			    	= "PERFIL_ADICIONAR";
	public static final String PERFIL_ALTERAR		 			= "PERFIL_ALTERAR";
	public static final String PERFIL_REMOVER 					= "PERFIL_REMOVER";
	public static final String PERFIL_DETALHE 					= "PERFIL_DETALHE";
}
