package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.persistence.AsoDao;

public class AsoBo extends GenericBo<Aso, GenericFilter, AsoDao, 
	GenericEntityBuilder<Aso,GenericFilter>, GenericExampleBuilder<Aso,GenericFilter>> {
	
	private static AsoBo instance;
	
	private AsoBo() {
		super();
	}
	
	public static AsoBo getInstance() {
		if(instance==null)
			instance = new AsoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
	
	}

	public Aso getUltimoByEmpregado(Aso aso) throws Exception{
		return getDao().getUltimoByEmpregado(aso);
	}	
}
