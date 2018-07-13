package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.MudancaFuncao;

public class MudancaFuncaoExampleBuilder extends GenericExampleBuilder<MudancaFuncao, MudancaFuncaoFilter> {

	public static MudancaFuncaoExampleBuilder newInstance(MudancaFuncaoFilter filter) {
		return new MudancaFuncaoExampleBuilder(filter);
	}
	
	private MudancaFuncaoExampleBuilder(MudancaFuncaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addCargo();
		addFuncao();
		addGhe();
		addGhee();
		addRegime();
		addGerencia();
		addBase();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addCargo() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCargo()!=null) {
			CriteriaExample criteriaExample = CargoExampleBuilder
					.newInstance(this.filter.getCargo()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addFuncao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFuncao()!=null) {
			CriteriaExample criteriaExample = FuncaoExampleBuilder
					.newInstance(this.filter.getFuncao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	private void addGhe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGhe()!=null) {
			CriteriaExample criteriaExample = GheExampleBuilder
					.newInstance(this.filter.getGhe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	private void addGhee() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGhee()!=null) {
			CriteriaExample criteriaExample = GheeExampleBuilder
					.newInstance(this.filter.getGhee()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	private void addRegime() throws InstantiationException, IllegalAccessException {
		if(this.filter.getRegime()!=null) {
			CriteriaExample criteriaExample = RegimeExampleBuilder
					.newInstance(this.filter.getRegime()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	private void addGerencia() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGerencia()!=null) {
			CriteriaExample criteriaExample = GerenciaExampleBuilder
					.newInstance(this.filter.getGerencia()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	private void addBase() throws InstantiationException, IllegalAccessException {
		if(this.filter.getBase()!=null) {
			CriteriaExample criteriaExample = BaseExampleBuilder
					.newInstance(this.filter.getBase()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}	
	
}
