package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FilaEsperaOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;

public class FilaEsperaOcupacionalExampleBuilder 
	extends GenericExampleBuilder<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter> {

	public static FilaEsperaOcupacionalExampleBuilder newInstance(FilaEsperaOcupacionalFilter filter) {
		return new FilaEsperaOcupacionalExampleBuilder(filter);
	}
	
	private FilaEsperaOcupacionalExampleBuilder(FilaEsperaOcupacionalFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addStatus();
		addAtualizacao();
		addHorarioCheckin();
		addSaida();
		addEmpregado();
		addLocalizacao();
		addServico();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addStatus() {
		if(this.filter.getStatus() != null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
	}
	
	private void addAtualizacao() {
		super.addData("atualizacao", this.filter.getAtualizacao());
	}
	
	private void addHorarioCheckin() {
		super.addData("horarioCheckin", this.filter.getHorarioCheckin());
	}
	
	private void addSaida() {
		super.addData("saida", this.filter.getSaida());
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addLocalizacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getLocalizacao()!=null) {
			CriteriaExample criteriaExample = LocalizacaoExampleBuilder
					.newInstance(this.filter.getLocalizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("localizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addServico() throws InstantiationException, IllegalAccessException {
		if(this.filter.getServico()!=null) {
			CriteriaExample criteriaExample = ServicoExampleBuilder
					.newInstance(this.filter.getServico()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("servico", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
