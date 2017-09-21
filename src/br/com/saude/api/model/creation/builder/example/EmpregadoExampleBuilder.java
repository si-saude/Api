package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoExampleBuilder extends GenericExampleBuilder<Empregado,EmpregadoFilter> {
	
	public static EmpregadoExampleBuilder newInstance(EmpregadoFilter filter) {
		return new EmpregadoExampleBuilder(filter);
	}
	
	private EmpregadoExampleBuilder(EmpregadoFilter filter) {
		super(filter);
	}
	
	private void addNome() {
		if(this.filter.getNome()!= null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCpf() {
		if(this.filter.getCpf()!= null)
			this.entity.setCpf(Helper.filterLike(this.filter.getCpf()));
	}
	
	private void addDataNascimento() {
		if(this.filter.getDataNascimento()!= null) {
			this.criterions.add(Restrictions.between("dataNascimento", 
					this.filter.getDataNascimento().getInicio(), 
					this.filter.getDataNascimento().getFim()));
		}
	}

	@Override
	public EmpregadoExampleBuilder example() {
		return (EmpregadoExampleBuilder) super.example();
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Empregado();
		addNome();
		addCpf();
		addDataNascimento();
	}
}
