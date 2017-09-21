package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;

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
	
	private void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Equipe();
		addNome();
	}

	@Override
	public EquipeExampleBuilder example() {
		return (EquipeExampleBuilder) super.example();
	}
}
