package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CurriculoFilter;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoExampleBuilder extends GenericExampleBuilder<Curriculo,CurriculoFilter> {

	public static CurriculoExampleBuilder newInstance(CurriculoFilter filter) {
		return new CurriculoExampleBuilder(filter);
	}
	
	private CurriculoExampleBuilder(CurriculoFilter filter) {
		super(filter);
	}
	
	private void addHistorico() {
		if(this.filter.getHistorico() != null)
			this.entity.setHistorico(Helper.filterLike(this.filter.getHistorico()));
	}
	
	private void addFormacao() {
		if(this.filter.getFormacao() != null)
			this.entity.setFormacao(Helper.filterLike(this.filter.getFormacao()));
	}
	
	private void addAtuacao() {
		if(this.filter.getAtuacao() != null)
			this.entity.setAtuacao(Helper.filterLike(this.filter.getAtuacao()));
	}
	
	@Override
	public CurriculoExampleBuilder example() {
		return (CurriculoExampleBuilder)super.example();
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new Curriculo();
		addHistorico();
		addFormacao();
		addAtuacao();
	}

}
