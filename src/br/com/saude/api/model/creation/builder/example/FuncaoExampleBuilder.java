package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoExampleBuilder extends GenericExampleBuilder<Funcao,FuncaoFilter> {

	public static FuncaoExampleBuilder newInstance(FuncaoFilter filter) {
		return new FuncaoExampleBuilder(filter);
	}
	
	private FuncaoExampleBuilder(FuncaoFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCursosObrigatorios() {
		if(this.filter.getCursosObrigatorios() != null)
			this.entity.setCursosObrigatorios(Helper.filterLike(this.filter.getCursosObrigatorios()));
	}
	
	@Override
	public FuncaoExampleBuilder example() {
		return (FuncaoExampleBuilder)super.example();
	}
	
	public FuncaoExampleBuilder exampleSelectList() {
		if(this.filter!=null) {
			createExampleSelectList();
			this.criterions.add(getExample());
		}
		return this;
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Funcao();
		addNome();
		addCursosObrigatorios();
	}
	
	private void createExampleSelectList() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Funcao();
		addNeId();
		this.filter.setPageNumber(1);
		this.filter.setPageSize(Integer.MAX_VALUE);
	}
}
