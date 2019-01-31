package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoFisicaFilter;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;

public class AvaliacaoFisicaBuilder extends GenericEntityBuilder<AvaliacaoFisica, AvaliacaoFisicaFilter> {
	
	private Function<Map<String,AvaliacaoFisica>,AvaliacaoFisica> loadAvaliacaoFisicaAtividadeFisica;
	
	public static AvaliacaoFisicaBuilder newInstance(AvaliacaoFisica avaliacao) {
		return new AvaliacaoFisicaBuilder(avaliacao);
	}
	
	public static AvaliacaoFisicaBuilder newInstance(List<AvaliacaoFisica> list) {
		return new AvaliacaoFisicaBuilder(list);
	}
	
	private AvaliacaoFisicaBuilder(AvaliacaoFisica avaliacao) {
		super(avaliacao);
	}

	private AvaliacaoFisicaBuilder(List<AvaliacaoFisica> list) {
		super(list);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadAvaliacaoFisicaAtividadeFisica = avaliacaoFisica -> {
			if(avaliacaoFisica.get("origem").getAvaliacaoFisicaAtividadeFisicas() != null)
				avaliacaoFisica.get("destino").setAvaliacaoFisicaAtividadeFisicas(AvaliacaoFisicaAtividadeFisicaBuilder
						.newInstance(avaliacaoFisica.get("origem").getAvaliacaoFisicaAtividadeFisicas())
						.loadAtividadeFisica().getEntityList());
			return avaliacaoFisica.get("destino");
		};
		
	}
	@Override
	protected AvaliacaoFisica clone(AvaliacaoFisica entity) {
		AvaliacaoFisica newAvaliacao = new AvaliacaoFisica();
		
		newAvaliacao.setId(entity.getId());
		newAvaliacao.setVersion(entity.getVersion());
		newAvaliacao.setAcaoIniciarExercicioFisico(entity.isAcaoIniciarExercicioFisico());
		newAvaliacao.setCarenciaMuscular(entity.getCarenciaMuscular());
		newAvaliacao.setFcRepouso(entity.getFcRepouso());
		newAvaliacao.setGorduraAbsoluta(entity.getGorduraAbsoluta());
		newAvaliacao.setImc(entity.getImc());
		newAvaliacao.setInteresseProgramaFisico(entity.isInteresseProgramaFisico());
		newAvaliacao.setIpaqAnterior(entity.getIpaqAnterior());
		newAvaliacao.setMassaMagra(entity.getMassaMagra());
		newAvaliacao.setObservacoes(entity.getObservacoes());
		newAvaliacao.setPad(entity.getPad());
		newAvaliacao.setPass(entity.getPass());
		newAvaliacao.setPercentualGordura(entity.getPercentualGordura());
		newAvaliacao.setPercentualGorduraIdeal(entity.getPercentualGorduraIdeal());
		newAvaliacao.setPercentualGorduraNegociada(entity.getPercentualGorduraNegociada());
		newAvaliacao.setPercentualMassaMagra(entity.getPercentualMassaMagra());
		newAvaliacao.setPercentualMassaMagraIdeal(entity.getPercentualMassaMagraIdeal());
		newAvaliacao.setPesoExcesso(entity.getPesoExcesso());
		newAvaliacao.setPesoExcessoNegociado(entity.getPesoExcessoNegociado());
		newAvaliacao.setPercentualMassaMagraNegociada(entity.getPercentualMassaMagraNegociada());
		newAvaliacao.setPesoIdeal(entity.getPesoIdeal());
		newAvaliacao.setPesoNegociado(entity.getPesoNegociado());
		newAvaliacao.setPraticaExercicioFisico(entity.isPraticaExercicioFisico());
		newAvaliacao.setProtocoloComposicaoCorporal(entity.getProtocoloComposicaoCorporal());
		newAvaliacao.setRazaoCinturaEstatura(entity.getRazaoCinturaEstatura());
		newAvaliacao.setObservacaoEstagioContemplacao(entity.getObservacaoEstagioContemplacao());
		newAvaliacao.setAptidaoCardiorrespiratoriaValor(entity.getAptidaoCardiorrespiratoriaValor());
		newAvaliacao.setAptidaoCardiorrespiratoriaClassificacao(entity.getAptidaoCardiorrespiratoriaClassificacao());
		newAvaliacao.setAptidaoCardiorrespiratoriaObservacao(entity.getAptidaoCardiorrespiratoriaObservacao());
		newAvaliacao.setForcaAbdominalValor(entity.getForcaAbdominalValor());
		newAvaliacao.setForcaAbdominalClassificacao(entity.getForcaAbdominalClassificacao());
		newAvaliacao.setForcaAbdominalObservacao(entity.getForcaAbdominalObservacao());
		newAvaliacao.setFlexibilidadeValor(entity.getFlexibilidadeValor());
		newAvaliacao.setFlexibilidadeClassificacao(entity.getFlexibilidadeClassificacao());
		newAvaliacao.setFlexibilidadeObservacao(entity.getFlexibilidadeObservacao());
		newAvaliacao.setForcaPreensaoManualValor(entity.getForcaPreensaoManualValor());
		newAvaliacao.setForcaPreensaoManualClassificacao(entity.getForcaPreensaoManualClassificacao());
		newAvaliacao.setForcaPreensaoManualObservacao(entity.getForcaPreensaoManualObservacao());
		newAvaliacao.setDobraTricipital(entity.getDobraTricipital());
		newAvaliacao.setDobraSubscapular(entity.getDobraSubscapular());
		newAvaliacao.setDobraToracica(entity.getDobraToracica());
		newAvaliacao.setDobraAuxiliarMedia(entity.getDobraAuxiliarMedia());
		newAvaliacao.setDobraSupraIliaca(entity.getDobraSupraIliaca());
		newAvaliacao.setDobraAbdominal(entity.getDobraAbdominal());
		newAvaliacao.setDobraCoxaMedial(entity.getDobraCoxaMedial());
		newAvaliacao.setPressaoArterialSistolica(entity.getPressaoArterialSistolica());
		newAvaliacao.setPressaoArterialDiastolica(entity.getPressaoArterialDiastolica());
		newAvaliacao.setFrequenciaCardiaca(entity.getFrequenciaCardiaca());
		
		if ( entity.getAtendimento() != null ) {
			newAvaliacao.setAtendimento(
					AtendimentoBuilder.newInstance(entity.getAtendimento()).getEntity());
		}
		
		return newAvaliacao;
	}

	@Override
	public AvaliacaoFisica cloneFromFilter(AvaliacaoFisicaFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AvaliacaoFisicaBuilder loadAvaliacaoFisicaAtividadeFisica() {
		return (AvaliacaoFisicaBuilder) this.loadProperty(this.loadAvaliacaoFisicaAtividadeFisica);
	}
	
}
