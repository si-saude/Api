package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.IntervencaoFilter;
import br.com.saude.api.model.entity.po.Intervencao;

public class IntervencaoExampleBuilder extends GenericExampleBuilder<Intervencao,IntervencaoFilter> {
	
	public static IntervencaoExampleBuilder newInstance(IntervencaoFilter filter) {
		return new IntervencaoExampleBuilder(filter);
	}
	
	private IntervencaoExampleBuilder(IntervencaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
		addEquipe();
		addInativo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addInativo() {
		this.entity.setInativo(this.addBoolean("inativo", this.filter.getInativo()));
	}
	
}