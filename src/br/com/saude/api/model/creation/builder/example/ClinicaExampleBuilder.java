package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ClinicaFilter;
import br.com.saude.api.model.entity.po.Clinica;

public class ClinicaExampleBuilder extends GenericExampleBuilder<Clinica, ClinicaFilter> {

	public static ClinicaExampleBuilder newInstance(ClinicaFilter filter) {
		return new ClinicaExampleBuilder(filter);
	}
	
	private ClinicaExampleBuilder(ClinicaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addNome();
		addUf();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addUf() {
		if(this.filter.getUf() != null)
			this.entity.setUf(Helper.filterLike(this.filter.getUf()));
	}
}
