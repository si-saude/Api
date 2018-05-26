package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.SolicitacaoCentralIntegraFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;;

public class SolicitacaoCentralIntegraBuilder extends GenericEntityBuilder<SolicitacaoCentralIntegra, SolicitacaoCentralIntegraFilter> {
	private Function<Map<String,SolicitacaoCentralIntegra>,SolicitacaoCentralIntegra> loadTarefa;
	private Function<Map<String,SolicitacaoCentralIntegra>,SolicitacaoCentralIntegra> loadTipoSolicitacao;

	public static SolicitacaoCentralIntegraBuilder newInstance(SolicitacaoCentralIntegra solicitacao) {
		return new SolicitacaoCentralIntegraBuilder(solicitacao);
	}
	
	public static SolicitacaoCentralIntegraBuilder newInstance(List<SolicitacaoCentralIntegra> solicitacoes) {
		return new SolicitacaoCentralIntegraBuilder(solicitacoes);
	}
	
	private SolicitacaoCentralIntegraBuilder(SolicitacaoCentralIntegra solicitacao) {
		super(solicitacao);
	}
	
	private SolicitacaoCentralIntegraBuilder(List<SolicitacaoCentralIntegra> solicitacoes) {
		super(solicitacoes);
	}

	@Override
	protected void initializeFunctions() {
		this.loadTarefa = solicitacoes ->{
			if(solicitacoes.get("origem").getTarefa() != null) {
				solicitacoes.get("destino").setTarefa(TarefaBuilder.
						newInstance(solicitacoes.get("origem").getTarefa()).getEntity());
			}
			return solicitacoes.get("destino");
		};
		
		this.loadTipoSolicitacao = solicitacoes ->{
			if(solicitacoes.get("origem").getTipoSolicitacao() != null) {
				solicitacoes.get("destino").setTipoSolicitacao(TipoSolicitacaoBuilder.
						newInstance(solicitacoes.get("origem").getTipoSolicitacao()).getEntity());
			}
			return solicitacoes.get("destino");
		};
	}

	@Override
	protected SolicitacaoCentralIntegra clone(SolicitacaoCentralIntegra solicitacao) {
		
		SolicitacaoCentralIntegra newSolicitacao = new SolicitacaoCentralIntegra();
		
		newSolicitacao.setId(solicitacao.getId());
		newSolicitacao.setVersion(solicitacao.getVersion());
		newSolicitacao.setAbertura(solicitacao.getAbertura());
		newSolicitacao.setConcluido(solicitacao.isConcluido());
		newSolicitacao.setDescricao(solicitacao.getDescricao());
		newSolicitacao.setObservacao(solicitacao.getObservacao());
		newSolicitacao.setPrazo(solicitacao.getPrazo());
		newSolicitacao.setStatus(solicitacao.getStatus());
		newSolicitacao.setTempoEstimado(solicitacao.getTempoEstimado());
		newSolicitacao.setTempoGasto(solicitacao.getTempoGasto());
		
		if(newSolicitacao.getTipoSolicitacao() != null)
			newSolicitacao.setTipoSolicitacao(TipoSolicitacaoBuilder.newInstance(solicitacao.getTipoSolicitacao())
					.getEntity());
		
		if(newSolicitacao.getTarefa() != null)
			newSolicitacao.setTarefa(TarefaBuilder.newInstance(newSolicitacao.getTarefa()).getEntity());
		
		return newSolicitacao;
	}
	
	public SolicitacaoCentralIntegraBuilder loadTarefa() {
		return (SolicitacaoCentralIntegraBuilder) this.loadProperty(this.loadTarefa);
	}
	
	public SolicitacaoCentralIntegraBuilder loadTipoSolicitacao() {
		return (SolicitacaoCentralIntegraBuilder) this.loadProperty(this.loadTipoSolicitacao);
	}

	@Override
	public SolicitacaoCentralIntegra cloneFromFilter(SolicitacaoCentralIntegraFilter filter) {
		return null;
	}
	
}
