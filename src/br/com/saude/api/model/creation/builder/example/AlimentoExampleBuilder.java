package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AlimentoFilter;
import br.com.saude.api.model.entity.po.Alimento;

public class AlimentoExampleBuilder extends GenericExampleBuilder<Alimento, AlimentoFilter> {

	public static AlimentoExampleBuilder newInstance(AlimentoFilter filter) {
		return new AlimentoExampleBuilder(filter);
	}

	private AlimentoExampleBuilder(AlimentoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addInativo();
		addNome();
	}
	
	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {

	}

	protected void addInativo() {
		this.entity.setInativo(this.addBoolean("inativo", this.filter.getInativo()));
	}
	
	public void addNome() {
		if(this.filter.getNome()!=null)
			this.criterions.add(Restrictions.ilike("nome", Helper.commonFilterLike(this.filter.getNome())));
	}

}
