package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.VacinaBuilder;
import br.com.saude.api.model.creation.builder.example.VacinaExampleBuilder;
import br.com.saude.api.model.entity.filter.VacinaFilter;
import br.com.saude.api.model.entity.po.Vacina;
import br.com.saude.api.model.persistence.VacinaDao;

public class VacinaBo extends GenericBo<Vacina, VacinaFilter, VacinaDao, 
										VacinaBuilder, VacinaExampleBuilder> {

	private static VacinaBo instance;
	
	private VacinaBo() {
		super();
	}
	
	public static VacinaBo getInstance() {
		if(instance==null)
			instance = new VacinaBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
}
