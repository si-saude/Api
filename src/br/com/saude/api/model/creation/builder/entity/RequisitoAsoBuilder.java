package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RequisitoAsoFilter;
import br.com.saude.api.model.entity.po.RequisitoAso;

public class RequisitoAsoBuilder extends GenericEntityBuilder<RequisitoAso, RequisitoAsoFilter> {

	public static RequisitoAsoBuilder newInstance(RequisitoAso requisito) {
		return new RequisitoAsoBuilder(requisito);
	}
	
	public static RequisitoAsoBuilder newInstance(List<RequisitoAso> requisitos) {
		return new RequisitoAsoBuilder(requisitos);
	}
	
	private RequisitoAsoBuilder(List<RequisitoAso> requisitos) {
		super(requisitos);
	}

	private RequisitoAsoBuilder(RequisitoAso requisito) {
		super(requisito);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected RequisitoAso clone(RequisitoAso requisito) {
		RequisitoAso newRequisito = new RequisitoAso();
		
		newRequisito.setId(requisito.getId());
		newRequisito.setConteudo(requisito.getConteudo());
		newRequisito.setVersion(requisito.getVersion());
		
		return newRequisito;
	}

	@Override
	public RequisitoAso cloneFromFilter(RequisitoAsoFilter filter) {
		return null;
	}

}
