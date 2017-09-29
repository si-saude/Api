package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ProfissionalConselhoFilter;
import br.com.saude.api.model.entity.po.ProfissionalConselho;

public class ProfissionalConselhoExampleBuilder 
			extends GenericExampleBuilder<ProfissionalConselho,ProfissionalConselhoFilter>{

	public static ProfissionalConselhoExampleBuilder newInstance(ProfissionalConselhoFilter filter) {
		return new ProfissionalConselhoExampleBuilder(filter);
	}
	
	private ProfissionalConselhoExampleBuilder(ProfissionalConselhoFilter filter) {
		super(filter);
	}
	
	private void addConselho() {
		if(this.filter.getConselho() != null)
			this.entity.setConselho(Helper.filterLike(this.filter.getConselho()));
	}
	
	private void addNumero() {
		if(this.filter.getNumero()!= null)
			this.entity.setNumero(Helper.filterLike(this.filter.getNumero()));
	}
	
	private void addUf() {
		if(this.filter.getUf()!= null)
			this.entity.setUf(Helper.filterLike(this.filter.getUf()));
	}
	
	private void addVencimento() {
		if(this.filter.getVencimento()!= null) {
			this.criterions.add(Restrictions.between("vencimento", 
					this.filter.getVencimento().getInicio(), 
					this.filter.getVencimento().getFim()));
		}
	}
	
	@Override
	public ProfissionalConselhoExampleBuilder example() {
		return (ProfissionalConselhoExampleBuilder)super.example();
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.criterias = new ArrayList<Triplet<String,CriteriaExample,JoinType>>();
		this.entity = new ProfissionalConselho();
		addConselho();
		addNumero();
		addUf();
		addVencimento();
	}
}
