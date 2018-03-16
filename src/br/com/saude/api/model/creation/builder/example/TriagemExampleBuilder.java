package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.TriagemFilter;
import br.com.saude.api.model.entity.po.Triagem;

public class TriagemExampleBuilder extends GenericExampleBuilder<Triagem, TriagemFilter> {

	public static TriagemExampleBuilder newInstance(TriagemFilter filter) {
		return new TriagemExampleBuilder(filter);
	}
	
	private TriagemExampleBuilder(TriagemFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addRiscoEmpregado();
		
		this.entity.setIndice(0);
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}

	private void addRiscoEmpregado() throws InstantiationException, IllegalAccessException {
		if (this.filter.getRiscoEmpregado() != null) {
			CriteriaExample criteriaExample = RiscoEmpregadoExampleBuilder
					.newInstance(this.filter.getRiscoEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("riscoEmpregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
