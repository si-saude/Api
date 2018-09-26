package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

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
	}
	
	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNome();
	}
	
	public void addNome() {
		if(this.filter.getNome()!=null)
			this.criterions.add(Restrictions.ilike("nome", Helper.filterLike(this.filter.getNome())));
	}
}
