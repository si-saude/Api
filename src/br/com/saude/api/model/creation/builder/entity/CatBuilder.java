package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;

public class CatBuilder extends GenericEntityBuilder<Cat, CatFilter> {

	public static CatBuilder newInstance(Cat cat) {
		return new CatBuilder(cat);
	}
	
	public static CatBuilder newInstance(List<Cat> cats) {
		return new CatBuilder(cats);
	}
	
	private CatBuilder(List<Cat> cats) {
		super(cats);
	}

	private CatBuilder(Cat cat) {
		super(cat);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Cat clone(Cat cat) {
		Cat newCat = new Cat();
		
		newCat.setId(cat.getId());
		newCat.setNumero(cat.getNumero());
		newCat.setVersion(cat.getVersion());
		
		return newCat;
	}

	@Override
	public Cat cloneFromFilter(CatFilter filter) {
		return null;
	}

}
