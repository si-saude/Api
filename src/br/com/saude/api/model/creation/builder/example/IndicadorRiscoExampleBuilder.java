package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public abstract class IndicadorRiscoExampleBuilder<T extends IndicadorRisco> 
											extends GenericExampleBuilder<T,IndicadorRiscoFilter> {

	protected IndicadorRiscoExampleBuilder(IndicadorRiscoFilter filter) {
		super(filter);
	}
	
	protected void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	protected void addRequisito() {
		if(this.filter.getRequisito() != null)
			this.entity.setRequisito(Helper.filterLike(this.filter.getRequisito()));
	}
	
	protected void addCritico() {
		if(this.filter.getCritico() == null || 
				this.filter.getCritico().getValue() <= 0 || 
				this.filter.getCritico().getValue() > 2)
			this.finishExampleFunction = example -> {
				return example.excludeProperty("critico");
			};
		else if(this.filter.getCritico().getValue() == 1)
			this.entity.setCritico(true);
		else
			this.entity.setCritico(false);
	}
	
	protected void addIndice0() {
		if(this.filter.getIndice0() != null)
			this.entity.setIndice0(Helper.filterLike(this.filter.getIndice0()));
	}
	
	protected void addIndice1() {
		if(this.filter.getIndice1() != null)
			this.entity.setIndice1(Helper.filterLike(this.filter.getIndice1()));
	}
	
	protected void addIndice2() {
		if(this.filter.getIndice2() != null)
			this.entity.setIndice2(Helper.filterLike(this.filter.getIndice2()));
	}
	
	protected void addIndice3() {
		if(this.filter.getIndice3() != null)
			this.entity.setIndice3(Helper.filterLike(this.filter.getIndice3()));
	}
	
	protected void addIndice4() {
		if(this.filter.getIndice4() != null)
			this.entity.setIndice4(Helper.filterLike(this.filter.getIndice4()));
	}
	
	protected void addIndice5() {
		if(this.filter.getIndice5() != null)
			this.entity.setIndice5(Helper.filterLike(this.filter.getIndice5()));
	}
	
	protected void addPeriodicidade() throws InstantiationException, IllegalAccessException {
		if(this.filter.getPeriodicidade()!=null) {
			CriteriaExample criteriaExample = PeriodicidadeExampleBuilder
					.newInstance(this.filter.getPeriodicidade()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("periodicidade", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addIndice0();
		addIndice1();
		addIndice2();
		addIndice3();
		addIndice4();
		addIndice5();
		addPeriodicidade();
		addCritico();
		addRequisito();
	}

	@Override
	protected void createExampleSelectList() {
		
	}

}
