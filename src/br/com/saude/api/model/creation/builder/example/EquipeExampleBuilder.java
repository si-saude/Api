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
	
	public GenericExampleBuilder<Equipe, EquipeFilter> exampleMedicinaOuOdonto() throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleMedicinaOuOdonto();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	public GenericExampleBuilder<Equipe, EquipeFilter> exampleMedOuErgOuHIg() throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleMedOuErgOuHig();
			this.criterions.add(getExample());
		}
		return this;
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
			this.entity.setAbreviacao(this.filter.getAbreviacao());
	}
	
	private void addCoordenador() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCoordenador()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getCoordenador()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("coordenador", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addMedicinaOuOdonto() {
		this.criterions.add(Restrictions.or(Restrictions.eq("abreviacao", "MED"), 
				Restrictions.eq("abreviacao", "ODO")));
	}
	private void addMedOuErgOuHig() {
		this.criterions.add(Restrictions.or(Restrictions.eq("abreviacao", "MED"), 
							Restrictions.or(Restrictions.eq("abreviacao", "ERG")),
											Restrictions.eq("abreviacao", "HIG")));
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addNome();
		addAbreviacao();
		addCoordenador();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNeId();
		addNome();
		addAbreviacao();
		addCoordenador();
	}
	
	protected void createExampleMedicinaOuOdonto() throws InstantiationException, IllegalAccessException {
		addNeId();
		addNome();
		addAbreviacao();
		addCoordenador();
		addMedicinaOuOdonto();
	}
	
	protected void createExampleMedOuErgOuHig() throws InstantiationException, IllegalAccessException {
		addMedOuErgOuHig();
	}
}
