package br.com.saude.api.model.persistence;


import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.PerfilExampleBuilder;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilDao extends GenericDao<Perfil> {
	
	private static PerfilDao instance;
	
	private PerfilDao() {
		super();
	}
	
	public static PerfilDao getInstance() {
		if(instance == null)
			instance = new PerfilDao();
		return instance;
	}
	
	public Perfil getbyIdLoadPermissoes(Object id) throws Exception {
		return this.getById(id, "loadPermissoes");
	}
	
	public PagedList<Perfil> getListLoadPermissoes(PerfilExampleBuilder perfilExampleBuilder) throws Exception{
		return this.getList(perfilExampleBuilder, "loadPermissoes");
	}
	
	@SuppressWarnings("unused")
	private Perfil loadPermissoes(Perfil perfil) {
		if(perfil.getPermissoes() != null)
			Hibernate.initialize(perfil.getPermissoes());
		return perfil;
	}
}
