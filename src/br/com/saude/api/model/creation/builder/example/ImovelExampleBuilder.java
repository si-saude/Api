package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ImovelFilter;
import br.com.saude.api.model.entity.po.Imovel;

public class ImovelExampleBuilder extends GenericExampleBuilder<Imovel, ImovelFilter> {

	public static ImovelExampleBuilder newInstance(ImovelFilter filter) {
		return new ImovelExampleBuilder(filter);
	}
	
	private ImovelExampleBuilder(ImovelFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addBase();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addBase() throws InstantiationException, IllegalAccessException {
		if(this.filter.getBase()!=null) {
			CriteriaExample criteriaExample = BaseExampleBuilder
					.newInstance(this.filter.getBase()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("base", criteriaExample, JoinType.INNER_JOIN));
		}
	}

}
