package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoFisicaFilter;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;

public class AvaliacaoFisicaBuilder extends GenericEntityBuilder<AvaliacaoFisica, AvaliacaoFisicaFilter> {
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
		// TODO Auto-generated method stub
		
	}
	@Override
	protected AvaliacaoFisica clone(AvaliacaoFisica entity) {
		AvaliacaoFisica newAvaliacao = new AvaliacaoFisica();
		
		newAvaliacao.setId(entity.getId());
		newAvaliacao.setVersion(entity.getVersion());
		newAvaliacao.setAcaoIniciarExercicioFisico(entity.isAcaoIniciarExercicioFisico());
		newAvaliacao.setAptidaoCardiorespiratoria(entity.getAptidaoCardiorespiratoria());
		newAvaliacao.setCarenciaMuscular(entity.getCarenciaMuscular());
		newAvaliacao.setFcRepouso(entity.getFcRepouso());
		newAvaliacao.setFlexibilidade(entity.getFlexibilidade());
		newAvaliacao.setForcaAbdominal(entity.getForcaAbdominal());
		newAvaliacao.setForcaPreensaoManual(entity.getForcaPreensaoManual());
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
		newAvaliacao.setTipoAtendimento(entity.getTipoAtendimento());
		newAvaliacao.setObservacaoEstagioContemplacao(entity.getObservacaoEstagioContemplacao());
		
		return newAvaliacao;
	}

	@Override
	public AvaliacaoFisica cloneFromFilter(AvaliacaoFisicaFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}


}
