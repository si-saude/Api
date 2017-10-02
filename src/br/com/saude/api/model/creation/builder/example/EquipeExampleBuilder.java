package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeExampleBuilder extends GenericExampleBuilder<Equipe,EquipeFilter>{

	public static EquipeExampleBuilder newInstance(EquipeFilter filter) {
		return new EquipeExampleBuilder(filter);
	}
	
	private EquipeExampleBuilder(EquipeFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	@Override
	protected void createExample() {
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
}
