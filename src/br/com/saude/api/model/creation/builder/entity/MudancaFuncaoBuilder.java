package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.MudancaFuncao;

public class MudancaFuncaoBuilder extends GenericEntityBuilder<MudancaFuncao, MudancaFuncaoFilter> {
	private Function<Map<String,MudancaFuncao>,MudancaFuncao> loadTarefas;
	private Function<Map<String,MudancaFuncao>,MudancaFuncao> loadInstalacoes;
	private Function<Map<String,MudancaFuncao>,MudancaFuncao> loadGruposMonitorametos;
	private Function<Map<String,MudancaFuncao>,MudancaFuncao> loadGhe;

	public static MudancaFuncaoBuilder newInstance(MudancaFuncao solicitacao) {
		return new MudancaFuncaoBuilder(solicitacao);
	}
	
	public static MudancaFuncaoBuilder newInstance(List<MudancaFuncao> solicitacoes) {
		return new MudancaFuncaoBuilder(solicitacoes);
	}
	
	private MudancaFuncaoBuilder(MudancaFuncao solicitacao) {
		super(solicitacao);
	}
	
	private MudancaFuncaoBuilder(List<MudancaFuncao> solicitacoes) {
		super(solicitacoes);
	}

	@Override
	protected void initializeFunctions() {
		this.loadTarefas = mundancafuncoes ->{
			if(mundancafuncoes.get("origem").getTarefas() != null) {
				mundancafuncoes.get("destino").setTarefas(TarefaBuilder.
						newInstance(mundancafuncoes.get("origem").getTarefas()).getEntityList());
			}
			return mundancafuncoes.get("destino");
		};
		this.loadInstalacoes = mundancafuncoes ->{
			if(mundancafuncoes.get("origem").getInstalacoes() != null) {
				mundancafuncoes.get("destino").setInstalacoes(InstalacaoBuilder.
						newInstance(mundancafuncoes.get("origem").getInstalacoes()).getEntityList());
			}
			return mundancafuncoes.get("destino");
		};
		this.loadGruposMonitorametos = mundancafuncoes ->{
			if(mundancafuncoes.get("origem").getGrupoMonitoramentos() != null) {
				mundancafuncoes.get("destino").setGrupoMonitoramentos(GrupoMonitoramentoBuilder.
						newInstance(mundancafuncoes.get("origem").getGrupoMonitoramentos())
						.loadTipoGrupoMonitoramento().loadAvaliacoes().getEntityList());
			}
			return mundancafuncoes.get("destino");
		};
		
		this.loadGhe = mundancafuncoes -> {			
			if(mundancafuncoes.get("origem").getGhe() != null)
				mundancafuncoes.get("destino").setGhe(GheBuilder.
			    newInstance(mundancafuncoes.get("origem").getGhe()).loadAll().getEntity());
			
			return mundancafuncoes.get("destino");
		};
	}

	@Override
	protected MudancaFuncao clone(MudancaFuncao solicitacao) {
		
		MudancaFuncao newSolicitacao = new MudancaFuncao();
		
		newSolicitacao.setId(solicitacao.getId());
		newSolicitacao.setVersion(solicitacao.getVersion());
		newSolicitacao.setAtividades(solicitacao.getAtividades());
		newSolicitacao.setDataTransferencia(solicitacao.getDataTransferencia());
		
		if(solicitacao.getEnfase() != null)
			newSolicitacao.setEnfase(EnfaseBuilder.newInstance(solicitacao.getEnfase()).getEntity());
		if(solicitacao.getFuncao() != null)
			newSolicitacao.setFuncao(FuncaoBuilder.newInstance(solicitacao.getFuncao()).getEntity());
		if(solicitacao.getGhe() != null)
			newSolicitacao.setGhe(GheBuilder.newInstance(solicitacao.getGhe()).getEntity());
		if(solicitacao.getGhee() != null)
			newSolicitacao.setGhee(GheeBuilder.newInstance(solicitacao.getGhee()).getEntity());
		if(solicitacao.getRegime() != null)
			newSolicitacao.setRegime(RegimeBuilder.newInstance(solicitacao.getRegime()).getEntity());
		if(solicitacao.getGerencia() != null)
			newSolicitacao.setGerencia(GerenciaBuilder.newInstance(solicitacao.getGerencia()).getEntity());
		if(solicitacao.getBase() != null)
			newSolicitacao.setBase(BaseBuilder.newInstance(solicitacao.getBase()).getEntity());
		
		return newSolicitacao;
	}
	
	public MudancaFuncaoBuilder loadTarefas() {
		return (MudancaFuncaoBuilder) this.loadProperty(this.loadTarefas);
	}

	public MudancaFuncaoBuilder loadInstalacoes() {
		return (MudancaFuncaoBuilder) this.loadProperty(this.loadInstalacoes);
	}
	
	public MudancaFuncaoBuilder loadLoadMonitoramentos() {
		return (MudancaFuncaoBuilder) this.loadProperty(this.loadGruposMonitorametos);
	}
	public MudancaFuncaoBuilder loadLoadGhe() {
		return (MudancaFuncaoBuilder) this.loadProperty(this.loadGhe);
	}


	@Override
	public MudancaFuncao cloneFromFilter(MudancaFuncaoFilter filter) {
		return null;
	}
	
}
