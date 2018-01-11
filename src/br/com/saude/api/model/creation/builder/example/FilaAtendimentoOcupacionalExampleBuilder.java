package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FilaAtendimentoOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;

public class FilaAtendimentoOcupacionalExampleBuilder
	extends GenericExampleBuilder<FilaAtendimentoOcupacional, FilaAtendimentoOcupacionalFilter>{

	public static FilaAtendimentoOcupacionalExampleBuilder newInstance(FilaAtendimentoOcupacionalFilter filter) {
		return new FilaAtendimentoOcupacionalExampleBuilder(filter);
	}
	
	private FilaAtendimentoOcupacionalExampleBuilder(FilaAtendimentoOcupacionalFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addStatus();
		addInicio();
		addAtualizacao();
		addFim();
		addLocalizacao();
		addProfissional();
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
	
	private void addInicio() {
		super.addData("inicio", this.filter.getInicio());
	}
	
	private void addFim() {
		super.addData("fim", this.filter.getFim());
	}
	
	private void addProfissional() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissional()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getProfissional()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissional", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addLocalizacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getLocalizacao()!=null) {
			CriteriaExample criteriaExample = LocalizacaoExampleBuilder
					.newInstance(this.filter.getLocalizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("localizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
