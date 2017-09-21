package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addMatricula() {
		if(this.filter.getMatricula() != null)
			this.entity.setMatricula(Helper.filterLike(this.filter.getMatricula()));
	}
	
	private void addChave() {
		if(this.filter.getChave() != null)
			this.entity.setChave(Helper.filterLike(this.filter.getChave()));
	}
	
	private void addRamal() {
		if(this.filter.getRamal() != null)
			this.entity.setRamal(Helper.filterLike(this.filter.getRamal()));
	}
	
	private void addMi() {
		if(this.filter.getMi() != null)
			this.entity.setMi(Helper.filterLike(this.filter.getMi()));
	}
	
	private void addDataNascimento() {
		if(this.filter.getDataNascimento()!= null) {
			switch(this.filter.getDataNascimento().getTypeFilter()) {
				case ENTRE: this.criterions.add(Restrictions.between("dataNascimento", 
													this.filter.getDataNascimento().getInicio(), 
													this.filter.getDataNascimento().getFim()));
					break;
				case MAIOR_IGUAL:
				case MAIOR: this.criterions.add(Restrictions.gt("dataNascimento", 
									this.filter.getDataNascimento().getInicio()));
					break;
				case MENOR_IGUAL:
				case MENOR: this.criterions.add(Restrictions.lt("dataNascimento", 
									this.filter.getDataNascimento().getInicio()));
					break;
				case IGUAL: this.criterions.add(Restrictions.eq("dataNascimento", this.filter.getDataNascimento().getInicio()));
					break;
				case DIFERENTE: this.criterions.add(Restrictions.ne("dataNascimento", this.filter.getDataNascimento().getInicio()));
					break;
				default:
					break;
			}
		}
	}
	
	private void addEquipe() {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addLocalizacao() {
		if(this.filter.getLocalizacao()!=null) {
			CriteriaExample criteriaExample = LocalizacaoExampleBuilder
					.newInstance(this.filter.getLocalizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("localizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	@Override
	public ProfissionalExampleBuilder example() {
		return (ProfissionalExampleBuilder) super.example();
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Profissional();
		addChave();
		addDataNascimento();
		addMatricula();
		addMi();
		addNome();
		addRamal();
		addEquipe();
		addLocalizacao();
	}
}
