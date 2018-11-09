package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.NutricaoAlimentoFilter;
import br.com.saude.api.model.entity.po.NutricaoAlimento;

public class NutricaoAlimentoExampleBuilder extends GenericExampleBuilder<NutricaoAlimento, NutricaoAlimentoFilter> {

	public static NutricaoAlimentoExampleBuilder newInstance(NutricaoAlimentoFilter filter) {
		return new NutricaoAlimentoExampleBuilder(filter);
	}

	private NutricaoAlimentoExampleBuilder(NutricaoAlimentoFilter filter) {
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
			this.criterions.add(Restrictions.ilike("nome", Helper.filterLike(this.filter.getNome())));
	}

}
