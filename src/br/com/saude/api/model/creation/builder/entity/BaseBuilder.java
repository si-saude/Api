package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.BaseFilter;
import br.com.saude.api.model.entity.po.Base;

public class BaseBuilder extends GenericEntityBuilder<Base, BaseFilter> {

	public static BaseBuilder newInstance(Base base) {
		return new BaseBuilder(base);
	}
	
	public static BaseBuilder newInstance(List<Base> bases) {
		return new BaseBuilder(bases);
	}
	
	private BaseBuilder(List<Base> bases) {
		super(bases);
	}

	private BaseBuilder(Base base) {
		super(base);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Base clone(Base base) {
		Base newBase = new Base();
		
		newBase.setId(base.getId());
		newBase.setNome(base.getNome());
		newBase.setUf(base.getUf());
		newBase.setVersion(base.getVersion());
		
		return newBase;
	}

	@Override
	public Base cloneFromFilter(BaseFilter filter) {
		return null;
	}
}
