package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.NutricaoAlimentoMedidaAlimentar;

public class NutricaoAlimentoMedidaAlimentarBuilder
		extends GenericEntityBuilder<NutricaoAlimentoMedidaAlimentar, GenericFilter> {

	private Function<Map<String,NutricaoAlimentoMedidaAlimentar>,NutricaoAlimentoMedidaAlimentar> loadMedidaAlimentar;

	
	public static NutricaoAlimentoMedidaAlimentarBuilder newInstance(NutricaoAlimentoMedidaAlimentar entity) {
		return new NutricaoAlimentoMedidaAlimentarBuilder(entity);
	}

	public static NutricaoAlimentoMedidaAlimentarBuilder newInstance(List<NutricaoAlimentoMedidaAlimentar> list) {
		return new NutricaoAlimentoMedidaAlimentarBuilder(list);
	}

	private NutricaoAlimentoMedidaAlimentarBuilder(NutricaoAlimentoMedidaAlimentar entity) {
		super(entity);
	}

	private NutricaoAlimentoMedidaAlimentarBuilder(List<NutricaoAlimentoMedidaAlimentar> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		loadMedidaAlimentar  = nutricaoAlimentoMedidaAlimentar -> {
			if(nutricaoAlimentoMedidaAlimentar.get("origem").getMedidaAlimentar() != null)
				nutricaoAlimentoMedidaAlimentar.get("destino").setMedidaAlimentar(
						MedidaAlimentarBuilder
						.newInstance(nutricaoAlimentoMedidaAlimentar.get("origem").getMedidaAlimentar())
						.getEntity());
			return nutricaoAlimentoMedidaAlimentar.get("destino");
		};
	}

	@Override
	protected NutricaoAlimentoMedidaAlimentar clone(NutricaoAlimentoMedidaAlimentar entity) {
		NutricaoAlimentoMedidaAlimentar newEntity = new NutricaoAlimentoMedidaAlimentar();

		newEntity.setId(entity.getId());
		newEntity.setQuantidade(entity.getQuantidade());
		newEntity.setReferencia(entity.getReferencia());
		newEntity.setVersion(entity.getVersion());

		if (entity.getNutricaoAlimento() != null)
			newEntity.setNutricaoAlimento(NutricaoAlimentoBuilder.newInstance(entity.getNutricaoAlimento()).getEntity());

		return newEntity;
	}

	@Override
	public NutricaoAlimentoMedidaAlimentar cloneFromFilter(GenericFilter filter) {
		return null;
	}
	
	public NutricaoAlimentoMedidaAlimentarBuilder loadMedidaAlimentar() {
		return (NutricaoAlimentoMedidaAlimentarBuilder) this.loadProperty(this.loadMedidaAlimentar);
	}

}