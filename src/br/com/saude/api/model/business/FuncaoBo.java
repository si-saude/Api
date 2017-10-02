package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FuncaoBuilder;
import br.com.saude.api.model.creation.builder.example.FuncaoExampleBuilder;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.model.persistence.FuncaoDao;

public class FuncaoBo extends GenericBo<Funcao, FuncaoFilter, FuncaoDao, FuncaoBuilder, 
										FuncaoExampleBuilder> {
	
	private static FuncaoBo instance;
	private Function<FuncaoBuilder,FuncaoBuilder> function;
	
	private FuncaoBo() {
		super();
		
		this.function = builder -> {
			return builder.loadCursos();
		};
	}
	
	public static FuncaoBo getInstance() {
		if(instance==null)
			instance = new FuncaoBo();
		return instance;
	}
	
	@Override
	public Funcao getById(Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadCursos(id),this.function);
	}
}
