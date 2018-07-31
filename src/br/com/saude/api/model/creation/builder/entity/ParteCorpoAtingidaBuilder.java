package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ParteCorpoAtingidaFilter;
import br.com.saude.api.model.entity.po.ParteCorpoAtingida;

public class ParteCorpoAtingidaBuilder extends GenericEntityBuilder<ParteCorpoAtingida, ParteCorpoAtingidaFilter> {

	private ParteCorpoAtingidaBuilder(List<ParteCorpoAtingida> parteCorpoAtingidas) {
		super(parteCorpoAtingidas);
	}

	private ParteCorpoAtingidaBuilder(ParteCorpoAtingida parteCorpoAtingida) {
		super(parteCorpoAtingida);
	}

	public static ParteCorpoAtingidaBuilder newInstance(ParteCorpoAtingida parteCorpoAtingida) {
		return new ParteCorpoAtingidaBuilder(parteCorpoAtingida);
	}
	
	public static ParteCorpoAtingidaBuilder newInstance(List<ParteCorpoAtingida> parteCorpoAtingidas) {
		return new ParteCorpoAtingidaBuilder(parteCorpoAtingidas);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected ParteCorpoAtingida clone(ParteCorpoAtingida parteCorpoAtingida) {
		
		ParteCorpoAtingida newParteCorpoAtingida = new ParteCorpoAtingida();
		newParteCorpoAtingida.setId(parteCorpoAtingida.getId());
		newParteCorpoAtingida.setCodigo(parteCorpoAtingida.getCodigo());
		newParteCorpoAtingida.setDescricao(parteCorpoAtingida.getDescricao());
		newParteCorpoAtingida.setVersion(parteCorpoAtingida.getVersion());
		
		return newParteCorpoAtingida;
	}

	@Override
	public ParteCorpoAtingida cloneFromFilter(ParteCorpoAtingidaFilter filter) {
		return null;
	}
}
