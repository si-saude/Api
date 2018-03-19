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
	
	public static final String AGENDA												= "AGENDA";
	public static final String QUADRO_ATENDIMENTO									= "QUADRO-ATENDIMENTO";
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
	public static final String ATENDIMENTO						= "ATENDIMENTO";
	public static final String ATENDIMENTO_GERENCIAR						= "ATENDIMENTO_GERENCIAR";
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
	public static final String CIDADE_LISTAR						= "CIDADE_LISTAR";
	public static final String CIDADE_ADICIONAR 			    	= "CIDADE_ADICIONAR";
	public static final String CIDADE_ALTERAR		 			= "CIDADE_ALTERAR";
	public static final String CIDADE_REMOVER 					= "CIDADE_REMOVER";
	public static final String CIDADE_DETALHE 					= "CIDADE_DETALHE";
	public static final String CRITERIO_LISTAR						= "CRITERIO_LISTAR";
	public static final String CRITERIO_ADICIONAR 			    	= "CRITERIO_ADICIONAR";
	public static final String CRITERIO_ALTERAR		 			= "CRITERIO_ALTERAR";
	public static final String CRITERIO_REMOVER 					= "CRITERIO_REMOVER";
	public static final String CRITERIO_DETALHE 					= "CRITERIO_DETALHE";
	public static final String CURSO_LISTAR						= "CURSO_LISTAR";
	public static final String CURSO_ADICIONAR 			    	= "CURSO_ADICIONAR";
	public static final String CURSO_ALTERAR		 			= "CURSO_ALTERAR";
	public static final String CURSO_REMOVER 					= "CURSO_REMOVER";
	public static final String CURSO_DETALHE 					= "CURSO_DETALHE";
	public static final String EQUIPE_LISTAR						= "EQUIPE_LISTAR";
	public static final String EQUIPE_ADICIONAR 			    	= "EQUIPE_ADICIONAR";
	public static final String EQUIPE_ALTERAR		 			= "EQUIPE_ALTERAR";
	public static final String EQUIPE_REMOVER 					= "EQUIPE_REMOVER";
	public static final String EQUIPE_DETALHE 					= "EQUIPE_DETALHE";
	public static final String FERIADO_LISTAR						= "FERIADO_LISTAR";
	public static final String FERIADO_ADICIONAR 			    	= "FERIADO_ADICIONAR";
	public static final String FERIADO_ALTERAR		 			= "FERIADO_ALTERAR";
	public static final String FERIADO_REMOVER 					= "FERIADO_REMOVER";
	public static final String FERIADO_DETALHE 					= "FERIADO_DETALHE";
	public static final String FILA_ESPERA_OCUPACIONAL_RECEPCAO = "FILA-ESPERA-OCUPACIONAL-RECEPCAO";
	public static final String FORNECEDOR_LISTAR						= "FORNECEDOR_LISTAR";
	public static final String FORNECEDOR_ADICIONAR 			    	= "FORNECEDOR_ADICIONAR";
	public static final String FORNECEDOR_ALTERAR		 			= "FORNECEDOR_ALTERAR";
	public static final String FORNECEDOR_REMOVER 					= "FORNECEDOR_REMOVER";
	public static final String FORNECEDOR_DETALHE 					= "FORNECEDOR_DETALHE";
	public static final String FUNCAO_LISTAR						= "FUNCAO_LISTAR";
	public static final String FUNCAO_ADICIONAR 			    	= "FUNCAO_ADICIONAR";
	public static final String FUNCAO_ALTERAR		 			= "FUNCAO_ALTERAR";
	public static final String FUNCAO_REMOVER 					= "FUNCAO_REMOVER";
	public static final String FUNCAO_DETALHE 					= "FUNCAO_DETALHE";
	public static final String GERENCIA_LISTAR						= "GERENCIA_LISTAR";
	public static final String GERENCIA_ADICIONAR 			    	= "GERENCIA_ADICIONAR";
	public static final String GERENCIA_ALTERAR		 			= "GERENCIA_ALTERAR";
	public static final String GERENCIA_REMOVER 					= "GERENCIA_REMOVER";
	public static final String GERENCIA_DETALHE 					= "GERENCIA_DETALHE";
	public static final String GHE_LISTAR						= "GHE_LISTAR";
	public static final String GHE_ADICIONAR 			    	= "GHE_ADICIONAR";
	public static final String GHE_ALTERAR		 			= "GHE_ALTERAR";
	public static final String GHE_REMOVER 					= "GHE_REMOVER";
	public static final String GHE_DETALHE 					= "GHE_DETALHE";
	public static final String GHEE_LISTAR						= "GHEE_LISTAR";
	public static final String GHEE_ADICIONAR 			    	= "GHEE_ADICIONAR";
	public static final String GHEE_ALTERAR		 			= "GHEE_ALTERAR";
	public static final String GHEE_REMOVER 					= "GHEE_REMOVER";
	public static final String GHEE_DETALHE 					= "GHEE_DETALHE";
	public static final String GRUPO_MONITORAMENTO_LISTAR						= "GRUPO-MONITORAMENTO_LISTAR";
	public static final String GRUPO_MONITORAMENTO_ADICIONAR 			    	= "GRUPO-MONITORAMENTO_ADICIONAR";
	public static final String GRUPO_MONITORAMENTO_ALTERAR		 			= "GRUPO-MONITORAMENTO_ALTERAR";
	public static final String GRUPO_MONITORAMENTO_REMOVER 					= "GRUPO-MONITORAMENTO_REMOVER";
	public static final String GRUPO_MONITORAMENTO_DETALHE 					= "GRUPO-MONITORAMENTO_DETALHE";
	public static final String INDICADOR_RISCO_ACIDENTE_LISTAR						= "INDICADOR-RISCO-ACIDENTE_LISTAR";
	public static final String INDICADOR_RISCO_ACIDENTE_ADICIONAR 			    	= "INDICADOR-RISCO-ACIDENTE_ADICIONAR";
	public static final String INDICADOR_RISCO_ACIDENTE_ALTERAR		 			= "INDICADOR-RISCO-ACIDENTE_ALTERAR";
	public static final String INDICADOR_RISCO_ACIDENTE_REMOVER 					= "INDICADOR-RISCO-ACIDENTE_REMOVER";
	public static final String INDICADOR_RISCO_ACIDENTE_DETALHE 					= "INDICADOR-RISCO-ACIDENTE_DETALHE";
	public static final String INDICADOR_RISCO_AMBIENTAL_LISTAR						= "INDICADOR-RISCO-AMBIENTAL_LISTAR";
	public static final String INDICADOR_RISCO_AMBIENTAL_ADICIONAR 			    	= "INDICADOR-RISCO-AMBIENTAL_ADICIONAR";
	public static final String INDICADOR_RISCO_AMBIENTAL_ALTERAR		 			= "INDICADOR-RISCO-AMBIENTAL_ALTERAR";
	public static final String INDICADOR_RISCO_AMBIENTAL_REMOVER 					= "INDICADOR-RISCO-AMBIENTAL_REMOVER";
	public static final String INDICADOR_RISCO_AMBIENTAL_DETALHE 					= "INDICADOR-RISCO-AMBIENTAL_DETALHE";
	public static final String INDICADOR_RISCO_ERGONOMICO_LISTAR						= "INDICADOR-RISCO-ERGONOMICO_LISTAR";
	public static final String INDICADOR_RISCO_ERGONOMICO_ADICIONAR 			    	= "INDICADOR-RISCO-ERGONOMICO_ADICIONAR";
	public static final String INDICADOR_RISCO_ERGONOMICO_ALTERAR		 			= "INDICADOR-RISCO-ERGONOMICO_ALTERAR";
	public static final String INDICADOR_RISCO_ERGONOMICO_REMOVER 					= "INDICADOR-RISCO-ERGONOMICO_REMOVER";
	public static final String INDICADOR_RISCO_ERGONOMICO_DETALHE 					= "INDICADOR-RISCO-ERGONOMICO_DETALHE";
	public static final String INDICADOR_RISCO_SANITARIO_LISTAR						= "INDICADOR-RISCO-SANITARIO_LISTAR";
	public static final String INDICADOR_RISCO_SANITARIO_ADICIONAR 			    	= "INDICADOR-RISCO-SANITARIO_ADICIONAR";
	public static final String INDICADOR_RISCO_SANITARIO_ALTERAR		 			= "INDICADOR-RISCO-SANITARIO_ALTERAR";
	public static final String INDICADOR_RISCO_SANITARIO_REMOVER 					= "INDICADOR-RISCO-SANITARIO_REMOVER";
	public static final String INDICADOR_RISCO_SANITARIO_DETALHE 					= "INDICADOR-RISCO-SANITARIO_DETALHE";
	public static final String INDICADOR_RISCO_SAUDE_AMBIENTAL_LISTAR						= "INDICADOR-RISCO-SAUDE-AMBIENTAL_LISTAR";
	public static final String INDICADOR_RISCO_SAUDE_AMBIENTAL_ADICIONAR 			    	= "INDICADOR-RISCO-SAUDE-AMBIENTAL_ADICIONAR";
	public static final String INDICADOR_RISCO_SAUDE_AMBIENTAL_ALTERAR		 			= "INDICADOR-RISCO-SAUDE-AMBIENTAL_ALTERAR";
	public static final String INDICADOR_RISCO_SAUDE_AMBIENTAL_REMOVER 					= "INDICADOR-RISCO-SAUDE-AMBIENTAL_REMOVER";
	public static final String INDICADOR_RISCO_SAUDE_AMBIENTAL_DETALHE 					= "INDICADOR-RISCO-SAUDE-AMBIENTAL_DETALHE";
	public static final String INSTALACAO_LISTAR						= "INSTALACAO_LISTAR";
	public static final String INSTALACAO_ADICIONAR 			    	= "INSTALACAO_ADICIONAR";
	public static final String INSTALACAO_ALTERAR		 			= "INSTALACAO_ALTERAR";
	public static final String INSTALACAO_REMOVER 					= "INSTALACAO_REMOVER";
	public static final String INSTALACAO_DETALHE 					= "INSTALACAO_DETALHE";
	public static final String LOCALIZACAO_LISTAR						= "LOCALIZACAO_LISTAR";
	public static final String LOCALIZACAO_ADICIONAR 			    	= "LOCALIZACAO_ADICIONAR";
	public static final String LOCALIZACAO_ALTERAR		 			= "LOCALIZACAO_ALTERAR";
	public static final String LOCALIZACAO_REMOVER 					= "LOCALIZACAO_REMOVER";
	public static final String LOCALIZACAO_DETALHE 					= "LOCALIZACAO_DETALHE";
	public static final String PERIODICIDADE_LISTAR						= "PERIODICIDADE_LISTAR";
	public static final String PERIODICIDADE_ADICIONAR 			    	= "PERIODICIDADE_ADICIONAR";
	public static final String PERIODICIDADE_ALTERAR		 			= "PERIODICIDADE_ALTERAR";
	public static final String PERIODICIDADE_REMOVER 					= "PERIODICIDADE_REMOVER";
	public static final String PERIODICIDADE_DETALHE 					= "PERIODICIDADE_DETALHE";
	public static final String PROFISSIOGRAMA_LISTAR						= "PROFISSIOGRAMA_LISTAR";
	public static final String PROFISSIOGRAMA_ADICIONAR 			    	= "PROFISSIOGRAMA_ADICIONAR";
	public static final String PROFISSIOGRAMA_ALTERAR		 			= "PROFISSIOGRAMA_ALTERAR";
	public static final String PROFISSIOGRAMA_REMOVER 					= "PROFISSIOGRAMA_REMOVER";
	public static final String PROFISSIOGRAMA_DETALHE 					= "PROFISSIOGRAMA_DETALHE";
	public static final String PROFISSIONAL_SAUDE_LISTAR						= "PROFISSIONAL-SAUDE_LISTAR";
	public static final String PROFISSIONAL_SAUDE_ADICIONAR 			    	= "PROFISSIONAL-SAUDE_ADICIONAR";
	public static final String PROFISSIONAL_SAUDE_ALTERAR		 			= "PROFISSIONAL-SAUDE_ALTERAR";
	public static final String PROFISSIONAL_SAUDE_REMOVER 					= "PROFISSIONAL-SAUDE_REMOVER";
	public static final String PROFISSIONAL_SAUDE_DETALHE 					= "PROFISSIONAL-SAUDE_DETALHE";
	public static final String REGIME_LISTAR						= "REGIME_LISTAR";
	public static final String REGIME_ADICIONAR 			    	= "REGIME_ADICIONAR";
	public static final String REGIME_ALTERAR		 			= "REGIME_ALTERAR";
	public static final String REGIME_REMOVER 					= "REGIME_REMOVER";
	public static final String REGIME_DETALHE 					= "REGIME_DETALHE";
	public static final String REGRA_ATENDIMENTO_LISTAR						= "REGRA-ATENDIMENTO_LISTAR";
	public static final String REGRA_ATENDIMENTO_ADICIONAR 			    	= "REGRA-ATENDIMENTO_ADICIONAR";
	public static final String REGRA_ATENDIMENTO_ALTERAR		 			= "REGRA-ATENDIMENTO_ALTERAR";
	public static final String REGRA_ATENDIMENTO_REMOVER 					= "REGRA-ATENDIMENTO_REMOVER";
	public static final String REGRA_ATENDIMENTO_DETALHE 					= "REGRA-ATENDIMENTO_DETALHE";
	public static final String RELATORIO_MEDICO_LISTAR						= "RELATORIO-MEDICO_LISTAR";
	public static final String RELATORIO_MEDICO_ADICIONAR 			    	= "RELATORIO-MEDICO_ADICIONAR";
	public static final String RELATORIO_MEDICO_ALTERAR		 			= "RELATORIO-MEDICO_ALTERAR";
	public static final String RELATORIO_MEDICO_REMOVER 					= "RELATORIO-MEDICO_REMOVER";
	public static final String RELATORIO_MEDICO_DETALHE 					= "RELATORIO-MEDICO_DETALHE";
	public static final String REQUISITO_ASO_LISTAR						= "REQUISITO-ASO_LISTAR";
	public static final String REQUISITO_ASO_ADICIONAR 			    	= "REQUISITO-ASO_ADICIONAR";
	public static final String REQUISITO_ASO_ALTERAR		 			= "REQUISITO-ASO_ALTERAR";
	public static final String REQUISITO_ASO_REMOVER 					= "REQUISITO-ASO_REMOVER";
	public static final String REQUISITO_ASO_DETALHE 					= "REQUISITO-ASO_DETALHE";
	public static final String RESULTADO_EXAME_LISTAR						= "RESULTADO-EXAME_LISTAR";
	public static final String RESULTADO_EXAME_ADICIONAR 			    	= "RESULTADO-EXAME_ADICIONAR";
	public static final String RESULTADO_EXAME_ALTERAR		 			= "RESULTADO-EXAME_ALTERAR";
	public static final String RESULTADO_EXAME_REMOVER 					= "RESULTADO-EXAME_REMOVER";
	public static final String RESULTADO_EXAME_DETALHE 					= "RESULTADO-EXAME_DETALHE";
	public static final String RISCO_GHE_LISTAR						= "RISCO-GHE_LISTAR";
	public static final String RISCO_GHE_ADICIONAR 			    	= "RISCO-GHE_ADICIONAR";
	public static final String RISCO_GHE_ALTERAR		 			= "RISCO-GHE_ALTERAR";
	public static final String RISCO_GHE_REMOVER 					= "RISCO-GHE_REMOVER";
	public static final String RISCO_GHE_DETALHE 					= "RISCO-GHE_DETALHE";
	public static final String SERVICO_LISTAR						= "SERVICO_LISTAR";
	public static final String SERVICO_ADICIONAR 			    	= "SERVICO_ADICIONAR";
	public static final String SERVICO_ALTERAR		 			= "SERVICO_ALTERAR";
	public static final String SERVICO_REMOVER 					= "SERVICO_REMOVER";
	public static final String SERVICO_DETALHE 					= "SERVICO_DETALHE";
	public static final String TIPO_GRUPO_MONITORAMENTO_LISTAR						= "TIPO-GRUPO-MONITORAMENTO_LISTAR";
	public static final String TIPO_GRUPO_MONITORAMENTO_ADICIONAR 			    	= "TIPO-GRUPO-MONITORAMENTO_ADICIONAR";
	public static final String TIPO_GRUPO_MONITORAMENTO_ALTERAR		 			= "TIPO-GRUPO-MONITORAMENTO_ALTERAR";
	public static final String TIPO_GRUPO_MONITORAMENTO_REMOVER 					= "TIPO-GRUPO-MONITORAMENTO_REMOVER";
	public static final String TIPO_GRUPO_MONITORAMENTO_DETALHE 					= "TIPO-GRUPO-MONITORAMENTO_DETALHE";
	public static final String VACINA_LISTAR						= "VACINA_LISTAR";
	public static final String VACINA_ADICIONAR 			    	= "VACINA_ADICIONAR";
	public static final String VACINA_ALTERAR		 			= "VACINA_ALTERAR";
	public static final String VACINA_REMOVER 					= "VACINA_REMOVER";
	public static final String VACINA_DETALHE 					= "VACINA_DETALHE";
}
