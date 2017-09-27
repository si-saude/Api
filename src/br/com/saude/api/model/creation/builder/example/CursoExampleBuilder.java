package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;

public class CursoExampleBuilder extends GenericExampleBuilder<Curso,CursoFilter> {

	public static CursoExampleBuilder newInstance(CursoFilter filter) {
		return new CursoExampleBuilder(filter);
	}
	
	private CursoExampleBuilder(CursoFilter filter) {
		super(filter);
	}
	
	@Override
	public CursoExampleBuilder example() {
		return (CursoExampleBuilder)super.example();
	}
	
	public CursoExampleBuilder exampleSelectList() {
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
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addValidade() {
		if(this.filter.getValidade() > 0)
			this.entity.setValidade(this.filter.getValidade());
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Curso();
		addNome();
		addDescricao();
		addValidade();
	}
	
	private void createExampleSelectList() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Curso();
		this.filter.setPageNumber(1);
		this.filter.setPageSize(Integer.MAX_VALUE);
	}

}
