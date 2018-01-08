package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ItemResultadoExameFilter;
import br.com.saude.api.model.entity.po.ItemResultadoExame;

public class ItemResultadoExameExampleBuilder extends GenericExampleBuilder<ItemResultadoExame,ItemResultadoExameFilter> {
	
	public static ItemResultadoExameExampleBuilder newInstance(ItemResultadoExameFilter filter) {
		return new ItemResultadoExameExampleBuilder(filter);
	}

	protected ItemResultadoExameExampleBuilder(ItemResultadoExameFilter filter) {
		super(filter);
	}
	
	private void addResultadoExame() throws InstantiationException, IllegalAccessException {
		if(this.filter.getResultadoExame()!=null) {
			CriteriaExample criteriaExample = ResultadoExameExampleBuilder
					.newInstance(this.filter.getResultadoExame()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("resultadoExame", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	private void addResultado() {
		if(this.filter.getResultado() != null)
			this.entity.setResultado(Helper.filterLike(this.filter.getResultado()));
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addResultadoExame();
		addTitulo();
		addResultado();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addTitulo();
		addResultado();
	}

}
