package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AcaoIntervencaoFilter;
import br.com.saude.api.model.entity.po.AcaoIntervencao;

public class AcaoIntervencaoExampleBuilder extends GenericExampleBuilder<AcaoIntervencao, AcaoIntervencaoFilter> {
	
	public static AcaoIntervencaoExampleBuilder newInstance(AcaoIntervencaoFilter filter) {
		return new AcaoIntervencaoExampleBuilder(filter);
	}
	
	private AcaoIntervencaoExampleBuilder(AcaoIntervencaoFilter filter) {
		super(filter);
	}
	public void addDescricao() {
		if(this.filter.getDescricao()!=null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}	
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addDescricao();
		addEquipe();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addId();
		addDescricao();
		addEquipe();
	}
	
}
