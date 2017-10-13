package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.CargoBuilder;
import br.com.saude.api.model.creation.builder.example.CargoExampleBuilder;
import br.com.saude.api.model.entity.filter.CargoFilter;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.persistence.CargoDao;

public class CargoBo extends GenericBo<Cargo, CargoFilter, CargoDao, CargoBuilder, 
										CargoExampleBuilder> {
	
	private static CargoBo instance;
	
	private CargoBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadCursos().loadVacinas();
		};
	}
	
	public static CargoBo getInstance() {
		if(instance==null)
			instance = new CargoBo();
		return instance;
	}
	
	@Override
	public Cargo getById(Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadAll(id),this.functionLoadAll);
	}
}
