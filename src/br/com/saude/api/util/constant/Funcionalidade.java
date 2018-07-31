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
	
	public static final String REPORT_PRE_REQUISITOS_AGENDAMENTO		= "REPORT-PRE-REQUISITOS-AGENDAMENTO";
	public static final String ENFASE_LISTAR							= "ENFASE_LISTAR";
	public static final String ENFASE_ADICIONAR 			    		= "ENFASE_ADICIONAR";
	public static final String ENFASE_ALTERAR		 					= "ENFASE_ALTERAR";
	public static final String ENFASE_REMOVER 							= "ENFASE_REMOVER";
	public static final String ENFASE_DETALHE 							= "ENFASE_DETALHE";
	public static final String REPORT_AVALIACAO_HIGIENE_OCUPACIONAL					= "REPORT-AVALIACAO-HIGIENE-OCUPACIONAL";
	public static final String REPORT_ATESTADO										= "REPORT-ATESTADO";
	public static final String REPORT_CAT										= "REPORT-CAT";
	public static final String AVALIACAO_HIGIENE_OCUPACIONAL_LISTAR						= "AVALIACAO-HIGIENE-OCUPACIONAL_LISTAR";
	public static final String AVALIACAO_HIGIENE_OCUPACIONAL_ADICIONAR 			    	= "AVALIACAO-HIGIENE-OCUPACIONAL_ADICIONAR";
	public static final String AVALIACAO_HIGIENE_OCUPACIONAL_ALTERAR		 			= "AVALIACAO-HIGIENE-OCUPACIONAL_ALTERAR";
	public static final String AVALIACAO_HIGIENE_OCUPACIONAL_REMOVER 					= "AVALIACAO-HIGIENE-OCUPACIONAL_REMOVER";
	public static final String AVALIACAO_HIGIENE_OCUPACIONAL_DETALHE 					= "AVALIACAO-HIGIENE-OCUPACIONAL_DETALHE";
	public static final String PARTE_CORPO_ATINGIDA_LISTAR						= "PARTE-CORPO-ATINGIDA_LISTAR";
	public static final String PARTE_CORPO_ATINGIDA_ADICIONAR 			    	= "PARTE-CORPO-ATINGIDA_ADICIONAR";
	public static final String PARTE_CORPO_ATINGIDA_ALTERAR		 			= "PARTE-CORPO-ATINGIDA_ALTERAR";
	public static final String PARTE_CORPO_ATINGIDA_REMOVER 					= "PARTE-CORPO-ATINGIDA_REMOVER";
	public static final String PARTE_CORPO_ATINGIDA_DETALHE 					= "PARTE-CORPO-ATINGIDA_DETALHE";
	public static final String NATUREZA_LESAO_LISTAR						= "NATUREZA-LESAO_LISTAR";
	public static final String NATUREZA_LESAO_ADICIONAR 			    	= "NATUREZA-LESAO_ADICIONAR";
	public static final String NATUREZA_LESAO_ALTERAR		 			= "NATUREZA-LESAO_ALTERAR";
	public static final String NATUREZA_LESAO_REMOVER 					= "NATUREZA-LESAO_REMOVER";
	public static final String NATUREZA_LESAO_DETALHE 					= "NATUREZA-LESAO_DETALHE";
	public static final String AGENTE_CAUSADOR_LISTAR						= "AGENTE-CAUSADOR_LISTAR";
	public static final String AGENTE_CAUSADOR_ADICIONAR 			    	= "AGENTE-CAUSADOR_ADICIONAR";
	public static final String AGENTE_CAUSADOR_ALTERAR		 			= "AGENTE-CAUSADOR_ALTERAR";
	public static final String AGENTE_CAUSADOR_REMOVER 					= "AGENTE-CAUSADOR_REMOVER";
	public static final String AGENTE_CAUSADOR_DETALHE 					= "AGENTE-CAUSADOR_DETALHE";
	public static final String CAT_LISTAR						= "CAT_LISTAR";
	public static final String CAT_ADICIONAR 			    	= "CAT_ADICIONAR";
	public static final String CAT_ALTERAR		 			= "CAT_ALTERAR";
	public static final String CAT_REMOVER 					= "CAT_REMOVER";
	public static final String CAT_DETALHE 					= "CAT_DETALHE";
	public static final String ATESTADO_LISTAR						= "ATESTADO_LISTAR";
	public static final String ATESTADO_ADICIONAR 			    	= "ATESTADO_ADICIONAR";
	public static final String ATESTADO_ALTERAR		 			= "ATESTADO_ALTERAR";
	public static final String ATESTADO_REMOVER 					= "ATESTADO_REMOVER";
	public static final String ATESTADO_DETALHE 					= "ATESTADO_DETALHE";
	public static final String APRHO_LISTAR						= "APRHO_LISTAR";
	public static final String APRHO_ADICIONAR 			    	= "APRHO_ADICIONAR";
	public static final String APRHO_ALTERAR		 			= "APRHO_ALTERAR";
	public static final String APRHO_REMOVER 					= "APRHO_REMOVER";
	public static final String APRHO_DETALHE 					= "APRHO_DETALHE";
	public static final String AGENTE_RISCO_LISTAR						= "AGENTE-RISCO_LISTAR";
	public static final String AGENTE_RISCO_ADICIONAR 			    	= "AGENTE-RISCO_ADICIONAR";
	public static final String AGENTE_RISCO_ALTERAR		 			= "AGENTE-RISCO_ALTERAR";
	public static final String AGENTE_RISCO_REMOVER 					= "AGENTE-RISCO_REMOVER";
	public static final String AGENTE_RISCO_DETALHE 					= "AGENTE-RISCO_DETALHE";
	public static final String POSSIVEL_DANO_SAUDE_LISTAR						= "POSSIVEL-DANO-SAUDE_LISTAR";
	public static final String POSSIVEL_DANO_SAUDE_ADICIONAR 			    	= "POSSIVEL-DANO-SAUDE_ADICIONAR";
	public static final String POSSIVEL_DANO_SAUDE_ALTERAR		 			= "POSSIVEL-DANO-SAUDE_ALTERAR";
	public static final String POSSIVEL_DANO_SAUDE_REMOVER 					= "POSSIVEL-DANO-SAUDE_REMOVER";
	public static final String POSSIVEL_DANO_SAUDE_DETALHE 					= "POSSIVEL-DANO-SAUDE_DETALHE";
	public static final String CATEGORIA_RISCO_LISTAR						= "CATEGORIA-RISCO_LISTAR";
	public static final String CATEGORIA_RISCO_ADICIONAR 			    	= "CATEGORIA-RISCO_ADICIONAR";
	public static final String CATEGORIA_RISCO_ALTERAR		 			= "CATEGORIA-RISCO_ALTERAR";
	public static final String CATEGORIA_RISCO_REMOVER 					= "CATEGORIA-RISCO_REMOVER";
	public static final String CATEGORIA_RISCO_DETALHE 					= "CATEGORIA-RISCO_DETALHE";
	public static final String FONTE_GERADORA_LISTAR						= "FONTE-GERADORA_LISTAR";
	public static final String FONTE_GERADORA_ADICIONAR 			    	= "FONTE-GERADORA_ADICIONAR";
	public static final String FONTE_GERADORA_ALTERAR		 			= "FONTE-GERADORA_ALTERAR";
	public static final String FONTE_GERADORA_REMOVER 					= "FONTE-GERADORA_REMOVER";
	public static final String FONTE_GERADORA_DETALHE 					= "FONTE-GERADORA_DETALHE";
	public static final String KANBAN												= "KANBAN";
	public static final String REPORT_SOLICITACAO_CENTRAL_INTEGRA					= "REPORT-SOLICITACAO-CENTRAL-INTEGRA";
	public static final String NOTIFICACAO											= "NOTIFICACAO";
	public static final String REPORT_EMPREGADOS_POR_GRUPO							= "REPORT-EMPREGADOS-POR-GRUPO";
	public static final String AGENDA												= "AGENDA";
	public static final String AGENDA_PERIODICO												= "AGENDA-PERIODICO";
	public static final String REPORT_PANORAMA										= "REPORT-PANORAMA";
	public static final String QUADRO_ATENDIMENTO									= "QUADRO-ATENDIMENTO";
	public static final String DECLARACAO_COMPARECIMENTO							= "DECLARACAO-COMPARECIMENTO";
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
	public static final String EIXO_LISTAR						= "EIXO_LISTAR";
	public static final String EIXO_ADICIONAR 			    	= "EIXO_ADICIONAR";
	public static final String EIXO_ALTERAR		 				= "EIXO_ALTERAR";
	public static final String EIXO_REMOVER 					= "EIXO_REMOVER";
	public static final String EIXO_DETALHE 					= "EIXO_DETALHE";
	public static final String DIAGNOSTICO_LISTAR						= "DIAGNOSTICO_LISTAR";
	public static final String DIAGNOSTICO_ADICIONAR 			    	= "DIAGNOSTICO_ADICIONAR";
	public static final String DIAGNOSTICO_ALTERAR		 				= "DIAGNOSTICO_ALTERAR";
	public static final String DIAGNOSTICO_REMOVER 					= "DIAGNOSTICO_REMOVER";
	public static final String DIAGNOSTICO_DETALHE 					= "DIAGNOSTICO_DETALHE";
	public static final String INTERVENCAO_LISTAR						= "INTERVENCAO_LISTAR";
	public static final String INTERVENCAO_ADICIONAR 			    	= "INTERVENCAO_ADICIONAR";
	public static final String INTERVENCAO_ALTERAR		 				= "INTERVENCAO_ALTERAR";
	public static final String INTERVENCAO_REMOVER 					= "INTERVENCAO_REMOVER";
	public static final String INTERVENCAO_DETALHE 					= "INTERVENCAO_DETALHE";
	public static final String AUDITORIA_ASO_LISTAR						= "AUDITORIA-ASO_LISTAR";
	public static final String AUDITORIA_ASO_ADICIONAR 			    	= "AUDITORIA-ASO_ADICIONAR";
	public static final String AUDITORIA_ASO_ALTERAR		 			= "AUDITORIA-ASO_ALTERAR";
	public static final String AUDITORIA_ASO_REMOVER 					= "AUDITORIA-ASO_REMOVER";
	public static final String AUDITORIA_ASO_DETALHE 					= "AUDITORIA-ASO_DETALHE";
	public static final String INDICADOR_SAST_LISTAR						= "INDICADOR-SAST_LISTAR";
	public static final String INDICADOR_SAST_ADICIONAR 			    	= "INDICADOR-SAST_ADICIONAR";
	public static final String INDICADOR_SAST_ALTERAR		 			= "INDICADOR-SAST_ALTERAR";
	public static final String INDICADOR_SAST_REMOVER 					= "INDICADOR-SAST_REMOVER";
	public static final String INDICADOR_SAST_DETALHE 					= "INDICADOR-SAST_DETALHE";
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
	public static final String PERGUNTA_FICHA_COLETA_LISTAR						= "PERGUNTA-FICHA-COLETA_LISTAR";
	public static final String PERGUNTA_FICHA_COLETA_ADICIONAR 			    	= "PERGUNTA-FICHA-COLETA_ADICIONAR";
	public static final String PERGUNTA_FICHA_COLETA_ALTERAR		 			= "PERGUNTA-FICHA-COLETA_ALTERAR";
	public static final String PERGUNTA_FICHA_COLETA_REMOVER 					= "PERGUNTA-FICHA-COLETA_REMOVER";
	public static final String PERGUNTA_FICHA_COLETA_DETALHE 					= "PERGUNTA-FICHA-COLETA_DETALHE";
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
	public static final String RISCO_POTENCIAL_LISTAR						= "RISCO-POTENCIAL_LISTAR";
	public static final String RISCO_POTENCIAL_ADICIONAR 			    	= "RISCO-POTENCIAL_ADICIONAR";
	public static final String RISCO_POTENCIAL_ALTERAR		 			= "RISCO-POTENCIAL_ALTERAR";
	public static final String RISCO_POTENCIAL_REMOVER 					= "RISCO-POTENCIAL_REMOVER";
	public static final String RISCO_POTENCIAL_DETALHE 					= "RISCO-POTENCIAL_DETALHE";
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
	public static final String TIPO_SOLICITACAO_ADICIONAR 			    			= "TIPO-SOLICITACAO_ADICIONAR";
	public static final String TIPO_SOLICITACAO_ALTERAR		 						= "TIPO-SOLICITACAO_ALTERAR";
	public static final String TIPO_SOLICITACAO_REMOVER 							= "TIPO-SOLICITACAO_REMOVER";
	public static final String TIPO_SOLICITACAO_DETALHE 							= "TIPO-SOLICITACAO_DETALHE";
	public static final String TIPO_SOLICITACAO_LISTAR								= "TIPO-SOLICITACAO_LISTAR";
}
