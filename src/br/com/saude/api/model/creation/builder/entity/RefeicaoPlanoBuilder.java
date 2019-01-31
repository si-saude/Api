package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RefeicaoPlanoFilter;
import br.com.saude.api.model.entity.po.RefeicaoPlano;

public class RefeicaoPlanoBuilder extends GenericEntityBuilder<RefeicaoPlano, RefeicaoPlanoFilter> {
	
	private Function<Map<String,RefeicaoPlano>,RefeicaoPlano> loadItens;
	
	public static RefeicaoPlanoBuilder newInstance(RefeicaoPlano refeicao) {
		return new RefeicaoPlanoBuilder(refeicao);
	}
	
	public static RefeicaoPlanoBuilder newInstance(List<RefeicaoPlano> refeicaos) {
		return new RefeicaoPlanoBuilder(refeicaos);
	}
	
	private RefeicaoPlanoBuilder(RefeicaoPlano refeicaos) {
		super(refeicaos);
	}
	
	private RefeicaoPlanoBuilder(List<RefeicaoPlano> refeicaos) {
		super(refeicaos);
	}

	@Override
	protected void initializeFunctions() {
		this.loadItens = refeicoes ->{
			if(refeicoes.get("origem").getItens() != null) {
				refeicoes.get("destino").setItens(
						ItemRefeicaoPlanoBuilder.newInstance(
								refeicoes.get("origem").getItens()).loadAlimento().getEntityList());
			}
			return refeicoes.get("destino");
		};
	}
	
	@Override
	protected RefeicaoPlano clone(RefeicaoPlano refeicao) {
		RefeicaoPlano cloneRefeicaoPlano = new RefeicaoPlano();
		
		cloneRefeicaoPlano.setId(refeicao.getId());
		cloneRefeicaoPlano.setVersion(refeicao.getVersion());
		cloneRefeicaoPlano.setNome(refeicao.getNome());
		
		if ( refeicao.getItens() != null ) 
			cloneRefeicaoPlano.setItens(ItemRefeicaoPlanoBuilder.newInstance(refeicao.getItens()).getEntityList());
		
		if ( refeicao.getPlanoAlimentar() != null )
			cloneRefeicaoPlano.setPlanoAlimentar(PlanoAlimentarBuilder.newInstance(refeicao.getPlanoAlimentar()).getEntity());
		
		return cloneRefeicaoPlano;
	}
	
	@Override
	public RefeicaoPlano cloneFromFilter(RefeicaoPlanoFilter filter) {
		return null;
	}
	public RefeicaoPlanoBuilder loadItens() {
		return (RefeicaoPlanoBuilder) this.loadProperty(this.loadItens);
	}
}

