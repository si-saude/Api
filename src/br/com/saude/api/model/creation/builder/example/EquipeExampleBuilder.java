package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
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
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addAbreviacao() {
		if (this.filter.getAbreviacao()!=null) 
			this.entity.setAbreviacao(Helper.filterLike(this.filter.getAbreviacao()));
	}
	
	private void addCoordenador() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCoordenador()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getCoordenador()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("coordenador", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addNome();
		addAbreviacao();
		addCoordenador();
	}

	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
}
