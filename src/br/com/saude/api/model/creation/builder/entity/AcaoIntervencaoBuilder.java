package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AcaoIntervencaoFilter;
import br.com.saude.api.model.entity.po.AcaoIntervencao;

public class AcaoIntervencaoBuilder extends GenericEntityBuilder<AcaoIntervencao, AcaoIntervencaoFilter> {

	private Function<Map<String,AcaoIntervencao>,AcaoIntervencao> loadEquipe;
	public static AcaoIntervencaoBuilder newInstance(AcaoIntervencao acao) {
		return new AcaoIntervencaoBuilder(acao);
	}
	
	public static AcaoIntervencaoBuilder newInstance(List<AcaoIntervencao> acoes) {
		return new AcaoIntervencaoBuilder(acoes);
	}
	
	private AcaoIntervencaoBuilder(AcaoIntervencao acao) {
		super(acao);
	}
	
	protected AcaoIntervencaoBuilder(List<AcaoIntervencao> acoes) {
		super(acoes);
	}

	@Override
	protected void initializeFunctions() {
		
		this.loadEquipe = acaoIntervencoes -> {
			if(acaoIntervencoes.get("origem").getEquipe()!= null) {
				acaoIntervencoes.get("destino").setEquipe(EquipeBuilder.newInstance(acaoIntervencoes.get("origem").getEquipe()).getEntity());
			}
			return acaoIntervencoes.get("destino");
		};
	}

	@Override
	protected AcaoIntervencao clone(AcaoIntervencao acaoIntervencao) {
		
		AcaoIntervencao newAcaoIntervencao = new AcaoIntervencao();
		newAcaoIntervencao.setId(acaoIntervencao.getId());
		newAcaoIntervencao.setDescricao(acaoIntervencao.getDescricao());
		newAcaoIntervencao.setVersion(acaoIntervencao.getVersion());		
		return newAcaoIntervencao;
	}

	@Override
	public AcaoIntervencao cloneFromFilter(AcaoIntervencaoFilter filter) {
		return null;
	}
	
	public AcaoIntervencaoBuilder loadEquipe() {
		return (AcaoIntervencaoBuilder) this.loadProperty(this.loadEquipe);
	}
}
