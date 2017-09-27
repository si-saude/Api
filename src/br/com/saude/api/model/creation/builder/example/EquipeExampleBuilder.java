package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
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
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Equipe();
		addNome();
	}
	
	private void createExampleSelectList() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Equipe();
		addNeId();
		this.filter.setPageNumber(1);
		this.filter.setPageSize(Integer.MAX_VALUE);
	}

	@Override
	public EquipeExampleBuilder example() {
		return (EquipeExampleBuilder) super.example();
	}
	
	public EquipeExampleBuilder exampleSelectList() {
		if(this.filter!=null) {
			createExampleSelectList();
			this.criterions.add(getExample());
		}
		return this;
	}
}
