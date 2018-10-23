package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.MedidaAlimentarFilter;
import br.com.saude.api.model.entity.po.MedidaAlimentar;

public class MedidaAlimentarBuilder extends GenericEntityBuilder<MedidaAlimentar,MedidaAlimentarFilter> {

	public static MedidaAlimentarBuilder newInstance(MedidaAlimentar medidaAlimentar) {
		return new MedidaAlimentarBuilder(medidaAlimentar);
	}
	
	public static MedidaAlimentarBuilder newInstance(List<MedidaAlimentar> medidaAlimentares) {
		return new MedidaAlimentarBuilder(medidaAlimentares);
	}
	
	private MedidaAlimentarBuilder(MedidaAlimentar medidaAlimentar) {
		super(medidaAlimentar);
	}

	private MedidaAlimentarBuilder(List<MedidaAlimentar> medidaAlimentares) {
		super(medidaAlimentares);
	}
	
	@Override
	protected void initializeFunctions() {}

	@Override
	protected MedidaAlimentar clone(MedidaAlimentar medidaAlimentar) {
		MedidaAlimentar newMedidaAlimentar = new MedidaAlimentar();
		
		newMedidaAlimentar.setId(medidaAlimentar.getId());
		newMedidaAlimentar.setDescricao(medidaAlimentar.getDescricao());
		newMedidaAlimentar.setVersion(medidaAlimentar.getVersion());
		
		return newMedidaAlimentar;
	}

	@Override
	public MedidaAlimentar cloneFromFilter(MedidaAlimentarFilter filter) {
		return null;
	}

}
