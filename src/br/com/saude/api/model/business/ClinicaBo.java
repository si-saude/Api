package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ClinicaBuilder;
import br.com.saude.api.model.creation.builder.example.ClinicaExampleBuilder;
import br.com.saude.api.model.entity.filter.ClinicaFilter;
import br.com.saude.api.model.entity.po.Clinica;
import br.com.saude.api.model.persistence.ClinicaDao;

public class ClinicaBo extends GenericBo<Clinica, ClinicaFilter, ClinicaDao, 
											ClinicaBuilder, ClinicaExampleBuilder> {

	private static ClinicaBo instance;
	
	private ClinicaBo() {
		super();
	}
	
	public static ClinicaBo getInstance() {
		if(instance == null)
			instance = new ClinicaBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadExames();
		};
	}
	
	@Override
	public Clinica getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Clinica> getListLoadAll(ClinicaFilter filter) throws Exception {
		return getDao().getListLoadAll(getExampleBuilder(filter).example());
	}
}
