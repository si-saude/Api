package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;
import br.com.saude.api.model.entity.po.Curso;

public class CidadeExampleBuilder extends GenericExampleBuilder<Cidade,CidadeFilter> {

	public static CidadeExampleBuilder newInstance(CidadeFilter filter) {
		return new CidadeExampleBuilder(filter);
	}
	
	private CidadeExampleBuilder(CidadeFilter filter) {
		super(filter);
	}
	
	public CidadeExampleBuilder exampleSelectList() {
		if(this.filter!=null) {
			createExampleSelectList();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addUf() {
		if(this.filter.getUf() != null)
			this.entity.setUf(Helper.filterLike(this.filter.getUf()));
	}

	@Override
	public CidadeExampleBuilder example() {
		return (CidadeExampleBuilder) super.example();
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Cidade();
		addNome();
		addUf();
	}
	
	private void createExampleSelectList() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Cidade();
		this.filter.setPageNumber(1);
		this.filter.setPageSize(Integer.MAX_VALUE);
	}
}
