package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.MedidaAlimentarBuilder;
import br.com.saude.api.model.creation.builder.example.MedidaAlimentarExampleBuilder;
import br.com.saude.api.model.entity.filter.MedidaAlimentarFilter;
import br.com.saude.api.model.entity.po.MedidaAlimentar;
import br.com.saude.api.model.persistence.MedidaAlimentarDao;

public class MedidaAlimentarBo extends GenericBo<MedidaAlimentar, MedidaAlimentarFilter, MedidaAlimentarDao, 
	MedidaAlimentarBuilder, MedidaAlimentarExampleBuilder> {

	private static MedidaAlimentarBo instance;
	
	private MedidaAlimentarBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() { }
	
	public static MedidaAlimentarBo getInstance() {
		if(instance==null)
			instance = new MedidaAlimentarBo();
		return instance;
	}
}
