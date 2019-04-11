package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PlanoAlimentarFilter;
import br.com.saude.api.model.entity.po.PlanoAlimentar;

public class PlanoAlimentarBuilder extends GenericEntityBuilder<PlanoAlimentar, PlanoAlimentarFilter> {
	private Function<Map<String,PlanoAlimentar>,PlanoAlimentar> loadRefeicoes;
	private Function<Map<String,PlanoAlimentar>,PlanoAlimentar> loadRefeicoesAlimentos;
	
	public static PlanoAlimentarBuilder newInstance(PlanoAlimentar planoAlimentar) {
		return new PlanoAlimentarBuilder(planoAlimentar);
	}
	
	public static PlanoAlimentarBuilder newInstance(List<PlanoAlimentar> planoAlimentars) {
		return new PlanoAlimentarBuilder(planoAlimentars);
	}
	
	private PlanoAlimentarBuilder(PlanoAlimentar planoAlimentars) {
		super(planoAlimentars);
	}
	
	private PlanoAlimentarBuilder(List<PlanoAlimentar> planoAlimentars) {
		super(planoAlimentars);
	}

	@Override
	protected void initializeFunctions() {
		this.loadRefeicoes = planoAlimentars ->{
			if(planoAlimentars.get("origem").getRefeicoes() != null) {
				planoAlimentars.get("destino").setRefeicoes(
						RefeicaoPlanoBuilder.newInstance(
								planoAlimentars.get("origem").getRefeicoes()).loadItens().getEntityList());
			}
			return planoAlimentars.get("destino");
		};
		
		this.loadRefeicoesAlimentos = planoAlimentars ->{
			if(planoAlimentars.get("origem").getRefeicoes() != null) {
				planoAlimentars.get("destino").setRefeicoes(
						RefeicaoPlanoBuilder.newInstance(
								planoAlimentars.get("origem").getRefeicoes()).loadItens().getEntityList());
			}
			return planoAlimentars.get("destino");
		};
	}
	
	@Override
	protected PlanoAlimentar clone(PlanoAlimentar planoAlimentar) {
		PlanoAlimentar clonePlanoAlimentar = new PlanoAlimentar();
		
		clonePlanoAlimentar.setId(planoAlimentar.getId());
		clonePlanoAlimentar.setVersion(planoAlimentar.getVersion());
		clonePlanoAlimentar.setNe(planoAlimentar.getNe());
		clonePlanoAlimentar.setTmb(planoAlimentar.getTmb());
		clonePlanoAlimentar.setObjetivo(planoAlimentar.getObjetivo());
		
		if ( planoAlimentar.getAtendimento() != null ) {
			planoAlimentar.getAtendimento().setFilaAtendimentoOcupacional(null);
			clonePlanoAlimentar.setAtendimento(
					AtendimentoBuilder.newInstance(planoAlimentar.getAtendimento()).getEntity());
		}
		
		return clonePlanoAlimentar;
	}
	
	@Override
	public PlanoAlimentar cloneFromFilter(PlanoAlimentarFilter filter) {
		return null;
	}
	
	public PlanoAlimentarBuilder loadRefeicoes() {
		return (PlanoAlimentarBuilder) this.loadProperty(this.loadRefeicoes);
	}
	
	public PlanoAlimentarBuilder loadRefeicoesAlimentos() {
		return (PlanoAlimentarBuilder) this.loadProperty(this.loadRefeicoesAlimentos);
	}
}
