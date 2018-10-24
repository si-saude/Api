package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.NutricaoAlimentoBuilder;
import br.com.saude.api.model.creation.builder.example.NutricaoAlimentoExampleBuilder;
import br.com.saude.api.model.entity.filter.NutricaoAlimentoFilter;
import br.com.saude.api.model.entity.po.NutricaoAlimento;
import br.com.saude.api.model.persistence.NutricaoAlimentoDao;

public class NutricaoAlimentoBo extends 
	GenericBo<NutricaoAlimento, NutricaoAlimentoFilter, NutricaoAlimentoDao, NutricaoAlimentoBuilder, NutricaoAlimentoExampleBuilder> {

	private static NutricaoAlimentoBo instance;

	private NutricaoAlimentoBo() {
		super();
	}

	public static NutricaoAlimentoBo getInstance() {
		if (instance == null)
			instance = new NutricaoAlimentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = nutricaoAlimento -> {
			nutricaoAlimento.loadNutricaoAlimentoMedidaAlimentar();
			return nutricaoAlimento;
		};
	}

	@Override
	public NutricaoAlimento save(NutricaoAlimento entity) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		entity.getNutricaoAlimentoMedidaAlimentares().forEach(n -> {
			n.setNutricaoAlimento(entity);
		});
		
		return super.save(entity);
	}

	@Override
	public NutricaoAlimento getById(Object id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		// TODO Auto-generated method stub
		return super.getById(id, functionLoad);
	}
	
}
