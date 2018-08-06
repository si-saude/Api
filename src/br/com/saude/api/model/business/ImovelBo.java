package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ImovelBuilder;
import br.com.saude.api.model.creation.builder.example.ImovelExampleBuilder;
import br.com.saude.api.model.entity.filter.ImovelFilter;
import br.com.saude.api.model.entity.po.Imovel;
import br.com.saude.api.model.persistence.ImovelDao;

public class ImovelBo extends GenericBo<Imovel, ImovelFilter, ImovelDao, ImovelBuilder, ImovelExampleBuilder> {

	private static ImovelBo instance;

	private ImovelBo() {
		super();
	}

	public static ImovelBo getInstance() {
		if (instance == null)
			instance = new ImovelBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadBase();
		};
	}

	@Override
	protected Imovel getById(Object id, Function<ImovelBuilder, ImovelBuilder> loadFunction)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

}
