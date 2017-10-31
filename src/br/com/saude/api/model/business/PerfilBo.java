package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.PerfilBuilder;
import br.com.saude.api.model.creation.builder.example.PerfilExampleBuilder;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.persistence.PerfilDao;

public class PerfilBo extends GenericBo<Perfil, PerfilFilter, PerfilDao, 
										PerfilBuilder, PerfilExampleBuilder>{
	
	private static PerfilBo instance;
	
	private PerfilBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadPermissoes();
		};
	}
	
	public static PerfilBo getInstance() {
		if(instance == null)
			instance = new PerfilBo();
		return instance;
	}
	
	public PagedList<Perfil> getListLoadPermissoes(PerfilFilter filter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return this.getList(getDao().getListLoadPermissoes(getExampleBuilder(filter).example()),
							this.functionLoadAll);
	}
	
	@Override
	public Perfil getById(Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return this.getByEntity(getDao().getByIdLoadPermissoes(id), this.functionLoadAll);
	}
	
	@Override
	public Perfil save(Perfil perfil) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		perfil.getPermissoes().forEach(p-> p.setPerfil(perfil));
		return super.save(perfil);
	}
}
