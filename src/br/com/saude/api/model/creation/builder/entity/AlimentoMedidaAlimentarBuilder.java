package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.AlimentoMedidaAlimentar;

public class AlimentoMedidaAlimentarBuilder
		extends GenericEntityBuilder<AlimentoMedidaAlimentar, GenericFilter> {

	private Function<Map<String,AlimentoMedidaAlimentar>,AlimentoMedidaAlimentar> loadMedidaAlimentar;

	
	public static AlimentoMedidaAlimentarBuilder newInstance(AlimentoMedidaAlimentar entity) {
		return new AlimentoMedidaAlimentarBuilder(entity);
	}

	public static AlimentoMedidaAlimentarBuilder newInstance(List<AlimentoMedidaAlimentar> list) {
		return new AlimentoMedidaAlimentarBuilder(list);
	}

	private AlimentoMedidaAlimentarBuilder(AlimentoMedidaAlimentar entity) {
		super(entity);
	}

	private AlimentoMedidaAlimentarBuilder(List<AlimentoMedidaAlimentar> list) {
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
	protected AlimentoMedidaAlimentar clone(AlimentoMedidaAlimentar entity) {
		AlimentoMedidaAlimentar newEntity = new AlimentoMedidaAlimentar();

		newEntity.setId(entity.getId());
		newEntity.setQuantidade(entity.getQuantidade());
		newEntity.setReferencia(entity.getReferencia());
		newEntity.setVersion(entity.getVersion());

		if (entity.getAlimento() != null)
			newEntity.setAlimento(AlimentoBuilder.newInstance(entity.getAlimento()).getEntity());

		return newEntity;
	}

	@Override
	public AlimentoMedidaAlimentar cloneFromFilter(GenericFilter filter) {
		return null;
	}
	
	public AlimentoMedidaAlimentarBuilder loadMedidaAlimentar() {
		return (AlimentoMedidaAlimentarBuilder) this.loadProperty(this.loadMedidaAlimentar);
	}

}