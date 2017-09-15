package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;

import br.com.saude.api.generic.GenericDao;
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
	
	public List<Perfil> getListLoadPermissoes(List<Criterion> criterions) throws Exception{
		return this.getList(criterions, "loadPermissoes");
	}
	
	@SuppressWarnings("unused")
	private Perfil loadPermissoes(Perfil perfil) {
		if(perfil.getPermissoes() != null)
			Hibernate.initialize(perfil.getPermissoes());
		return perfil;
	}
}
