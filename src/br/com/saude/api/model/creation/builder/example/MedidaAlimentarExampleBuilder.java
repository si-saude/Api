package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.MedidaAlimentarFilter;
import br.com.saude.api.model.entity.po.MedidaAlimentar;

public class MedidaAlimentarExampleBuilder extends GenericExampleBuilder<MedidaAlimentar,MedidaAlimentarFilter>  {

	public static MedidaAlimentarExampleBuilder newInstance(MedidaAlimentarFilter filter) {
		return new MedidaAlimentarExampleBuilder(filter);
	}
	
	private MedidaAlimentarExampleBuilder(MedidaAlimentarFilter filter) {
		super(filter);
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao()!=null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}

	@Override
	protected void createExample() {
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() {}
}
