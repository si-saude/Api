package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoExampleBuilder {
	private Empregado entity;
	private EmpregadoFilter filter;
	private List<Criterion> criterions;
	
	public EmpregadoExampleBuilder(EmpregadoFilter filter) {
		this.filter = filter;
		this.entity = new Empregado();
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
	
	public List<Criterion> getExample() {
		if(this.filter != null) {
			this.criterions = new ArrayList<Criterion>();
			this.entity = new Empregado();
			addNome();
			addCpf();
			addDataNascimento();
			this.criterions.add(Example.create(this.entity).enableLike().ignoreCase());
			return this.criterions;			
		}else
			return null;
	}
}
