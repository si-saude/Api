package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CnaeFilter;
import br.com.saude.api.model.entity.po.Cnae;

public class CnaeExampleBuilder extends GenericExampleBuilder<Cnae,CnaeFilter> {

	public static CnaeExampleBuilder newInstance(CnaeFilter filter) {
		return new CnaeExampleBuilder(filter);
	}
	
	private CnaeExampleBuilder(CnaeFilter filter) {
		super(filter);
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.criterions.add(Restrictions.ilike("codigo", Helper.filterLike(this.filter.getCodigo())));
	}
	
	@Override
	protected void createExample() {
		addCodigo();
	}

	@Override
	protected void createExampleSelectList() {
		
	}

}
