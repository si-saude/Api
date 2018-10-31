package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.IndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;

public class IndicadorConhecimentoAlimentarBuilder extends GenericEntityBuilder<IndicadorConhecimentoAlimentar, IndicadorConhecimentoAlimentarFilter> {

	private Function<Map<String,IndicadorConhecimentoAlimentar>, IndicadorConhecimentoAlimentar> loadItemIndicadorConhecimentoAlimentares;

	public static IndicadorConhecimentoAlimentarBuilder newInstance(IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar) {
		return new IndicadorConhecimentoAlimentarBuilder(indicadorConhecimentoAlimentar);
	}
	
	public static IndicadorConhecimentoAlimentarBuilder newInstance(List<IndicadorConhecimentoAlimentar> indicadorConhecimentoAlimentares) {
		return new IndicadorConhecimentoAlimentarBuilder(indicadorConhecimentoAlimentares);
	}
	
	private IndicadorConhecimentoAlimentarBuilder(List<IndicadorConhecimentoAlimentar> indicadorConhecimentoAlimentares) {
		super(indicadorConhecimentoAlimentares);
	}

	private IndicadorConhecimentoAlimentarBuilder(IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar) {
		super(indicadorConhecimentoAlimentar);
	}

	@Override
	protected void initializeFunctions() {
		this.loadItemIndicadorConhecimentoAlimentares = indicadorConhecimentoAlimentar -> {
			if(indicadorConhecimentoAlimentar.get("origem").getItemIndicadorConhecimentoAlimentares() != null)
				indicadorConhecimentoAlimentar.get("destino").setItemIndicadorConhecimentoAlimentares(
					ItemIndicadorConhecimentoAlimentarBuilder.newInstance(
						indicadorConhecimentoAlimentar.get("origem").getItemIndicadorConhecimentoAlimentares())
					.getEntityList());
			return indicadorConhecimentoAlimentar.get("destino");
		};
	}

	@Override
	protected IndicadorConhecimentoAlimentar clone(IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar) {
		IndicadorConhecimentoAlimentar newIndicadorConhecimentoAlimentar = new IndicadorConhecimentoAlimentar();
		
		newIndicadorConhecimentoAlimentar.setId(indicadorConhecimentoAlimentar.getId());
		newIndicadorConhecimentoAlimentar.setEnunciado(indicadorConhecimentoAlimentar.getEnunciado());
		newIndicadorConhecimentoAlimentar.setOrdem(indicadorConhecimentoAlimentar.getOrdem());
		newIndicadorConhecimentoAlimentar.setInativo(indicadorConhecimentoAlimentar.isInativo());
		newIndicadorConhecimentoAlimentar.setVersion(indicadorConhecimentoAlimentar.getVersion());
		
		return newIndicadorConhecimentoAlimentar;
	}

	@Override
	public IndicadorConhecimentoAlimentar cloneFromFilter(IndicadorConhecimentoAlimentarFilter filter) {
		return null;
	}
	
	public IndicadorConhecimentoAlimentarBuilder loadItemIndicadorConhecimentoAlimentares() {
		return (IndicadorConhecimentoAlimentarBuilder) this.loadProperty(this.loadItemIndicadorConhecimentoAlimentares);
	}

}
