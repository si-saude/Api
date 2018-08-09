package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.po.Aprho;

public class AprhoExampleBuilder extends GenericExampleBuilder<Aprho, AprhoFilter> {

	public static AprhoExampleBuilder newInstance(AprhoFilter filter) {
		return new AprhoExampleBuilder(filter);
	}
	
	private AprhoExampleBuilder(AprhoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpresa();
		addData();
		addDataRevisao();
		addGhe();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addEmpresa() {
		if(this.filter.getEmpresa() != null)
			this.entity.setEmpresa(Helper.filterLike(this.filter.getEmpresa()));
	}
	
	private void addGhe() throws InstantiationException, IllegalAccessException {
		if (this.filter.getGhe() != null) {
			CriteriaExample criteriaExample = GheExampleBuilder
					.newInstance(this.filter.getGhe()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("ghe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addData() {
		this.addData("data", this.filter.getData());
	}

	private void addDataRevisao() {
		this.addData("dataRevisao", this.filter.getDataRevisao());
	}
}
