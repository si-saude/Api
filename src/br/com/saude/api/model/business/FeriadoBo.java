package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FeriadoBuilder;
import br.com.saude.api.model.creation.builder.example.FeriadoExampleBuilder;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;
import br.com.saude.api.model.persistence.FeriadoDao;

public class FeriadoBo extends GenericBo<Feriado, FeriadoFilter, FeriadoDao, FeriadoBuilder, FeriadoExampleBuilder> {
	private static FeriadoBo instance;
	
	private FeriadoBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {

	}
	
	public static FeriadoBo getInstance() {
		if (instance == null)
			instance = new FeriadoBo();
		return instance;
	}
}
