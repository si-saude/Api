package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.AlimentoBuilder;
import br.com.saude.api.model.creation.builder.example.AlimentoExampleBuilder;
import br.com.saude.api.model.entity.filter.AlimentoFilter;
import br.com.saude.api.model.entity.po.Alimento;
import br.com.saude.api.model.persistence.AlimentoDao;

public class AlimentoBo extends 
	GenericBo<Alimento, AlimentoFilter, AlimentoDao, AlimentoBuilder, AlimentoExampleBuilder> {

	private static AlimentoBo instance;

	private AlimentoBo() {
		super();
	}

	public static AlimentoBo getInstance() {
		if (instance == null)
			instance = new AlimentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = nutricaoAlimento -> {
			nutricaoAlimento.loadNutricaoAlimentoMedidaAlimentar().loadSubstituicoes();
			return nutricaoAlimento;
		};
		this.functionLoadAll = nutricaoAlimento -> {
			nutricaoAlimento.loadSubstituicoes();
			return nutricaoAlimento;
		};
	}

	@Override
	public Alimento save(Alimento entity) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		entity.getAlimentoMedidaAlimentares().forEach(n -> {
			n.setAlimento(entity);
		});
		
		return super.save(entity);
	}

	@Override
	public Alimento getById(Object id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getById(id, functionLoad);
	}

	public PagedList<Alimento> getListAll(AlimentoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}

}
