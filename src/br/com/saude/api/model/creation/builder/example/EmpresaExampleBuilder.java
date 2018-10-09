package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EmpresaFilter;
import br.com.saude.api.model.entity.po.Empresa;

public class EmpresaExampleBuilder extends GenericExampleBuilder<Empresa,EmpresaFilter> {
	public static EmpresaExampleBuilder newInstance(EmpresaFilter filter) {
		return new EmpresaExampleBuilder(filter);
	}
	
	private EmpresaExampleBuilder(EmpresaFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addEmpresa();
		addId();
	}
	
	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNome();
		addEmpresa();
		addId();
	}
	
	public void addNome() {
		if(this.filter.getNome()!=null)
			this.criterions.add(Restrictions.ilike("nome", Helper.filterLike(this.filter.getNome())));
	}
	
	private void addEmpresa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpresa()!=null) {
			CriteriaExample criteriaExample = EmpresaExampleBuilder
					.newInstance(this.filter.getEmpresa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empresa", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
}
