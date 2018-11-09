package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RecordatorioBuilder;
import br.com.saude.api.model.creation.builder.example.RecordatorioExampleBuilder;
import br.com.saude.api.model.entity.filter.RecordatorioFilter;
import br.com.saude.api.model.entity.po.Recordatorio;
import br.com.saude.api.model.persistence.RecordatorioDao;

public class RecordatorioBo extends 
	GenericBo<Recordatorio, RecordatorioFilter, RecordatorioDao, RecordatorioBuilder, RecordatorioExampleBuilder> {
	
	private static RecordatorioBo instance;
	
	private RecordatorioBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			builder = builder.loadRefeicoes();
			return builder;
		};
	}
	
	public static RecordatorioBo getInstance() {
		if(instance==null)
			instance = new RecordatorioBo();
		return instance;
	}
	
	@Override
	public Recordatorio save(Recordatorio entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
	
		if ( entity.getRefeicoes() != null )
			entity.getRefeicoes().forEach(r -> {
				r.setRecordatorio(entity);
				r.getItens().forEach(i -> i.setRefeicao(r));
			});
		
		return super.save(entity);
	}
	
	@Override
	public Recordatorio getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id),functionLoadAll);
	}
}
