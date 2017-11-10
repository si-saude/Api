package br.com.saude.api.model.creation.builder.example;


import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalExampleBuilder extends GenericExampleBuilder<Profissional,ProfissionalFilter> {

	public static ProfissionalExampleBuilder newInstance(ProfissionalFilter filter) {
		return new ProfissionalExampleBuilder(filter);
	}
	
	private ProfissionalExampleBuilder(ProfissionalFilter filter) {
		super(filter);
	}
	
	private void addMi() {
		if(this.filter.getMi() != null)
			this.entity.setMi(Helper.filterLike(this.filter.getMi()));
	}
	
	private void addDataAso() {
		this.addData("dataAso", this.filter.getDataAso());
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addLocalizacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getLocalizacao()!=null) {
			CriteriaExample criteriaExample = LocalizacaoExampleBuilder
					.newInstance(this.filter.getLocalizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("localizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addCurriculo() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCurriculo()!=null) {
			CriteriaExample criteriaExample = CurriculoExampleBuilder
					.newInstance(this.filter.getCurriculo()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("curriculo", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addProfissionalConselho() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissionalConselho()!=null) {
			CriteriaExample criteriaExample = ProfissionalConselhoExampleBuilder
					.newInstance(this.filter.getProfissionalConselho()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissionalConselho", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpregado();
		addDataAso();
		addMi();
		addEquipe();
		addLocalizacao();
		addCurriculo();
		addProfissionalConselho();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
