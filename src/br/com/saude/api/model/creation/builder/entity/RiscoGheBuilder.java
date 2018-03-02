package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RiscoGheFilter;
import br.com.saude.api.model.entity.po.RiscoGhe;

public class RiscoGheBuilder extends GenericEntityBuilder<RiscoGhe, RiscoGheFilter> {

	public static RiscoGheBuilder newInstance(RiscoGhe risco) {
		return new RiscoGheBuilder(risco);
	}
	
	public static RiscoGheBuilder newInstance(List<RiscoGhe> riscos) {
		return new RiscoGheBuilder(riscos);
	}
	
	private RiscoGheBuilder(RiscoGhe risco) {
		super(risco);
	}

	private RiscoGheBuilder(List<RiscoGhe> riscos) {
		super(riscos);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected RiscoGhe clone(RiscoGhe risco) {
		
		RiscoGhe newRisco = new RiscoGhe();
		
		newRisco.setId(risco.getId());
		newRisco.setTitulo(risco.getTitulo());
		newRisco.setVersion(risco.getVersion());
		
		return newRisco;
	}

	@Override
	public RiscoGhe cloneFromFilter(RiscoGheFilter filter) {
		return null;
	}
}
