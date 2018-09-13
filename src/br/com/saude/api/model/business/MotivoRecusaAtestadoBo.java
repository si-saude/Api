package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.MotivoRecusaAtestadoBuilder;
import br.com.saude.api.model.creation.builder.example.MotivoRecusaAtestadoExampleBuilder;
import br.com.saude.api.model.entity.filter.MotivoRecusaAtestadoFilter;
import br.com.saude.api.model.entity.po.MotivoRecusaAtestado;
import br.com.saude.api.model.persistence.MotivoRecusaAtestadoDao;

public class MotivoRecusaAtestadoBo extends 
	GenericBo<MotivoRecusaAtestado, MotivoRecusaAtestadoFilter, MotivoRecusaAtestadoDao, 
	MotivoRecusaAtestadoBuilder, MotivoRecusaAtestadoExampleBuilder> {

	private static MotivoRecusaAtestadoBo instance;

	private MotivoRecusaAtestadoBo() {
		super();
	}

	public static MotivoRecusaAtestadoBo getInstance() {
		if (instance == null)
			instance = new MotivoRecusaAtestadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

}
