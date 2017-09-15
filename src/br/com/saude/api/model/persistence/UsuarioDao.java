package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {
	
	private static UsuarioDao instance;
	
	private UsuarioDao() {
		super();
	}
	
	public static UsuarioDao getInstance() {
		if(instance == null)
			instance = new UsuarioDao();
		return instance;
	}
	
	public Usuario getByIdLoadPerfis(Object id) throws Exception {
		return this.getById(id,"loadPerfis");
	}
	
	public List<Usuario> getListLoadPerfis(List<Criterion> criterions) throws Exception{
		return this.getList(criterions, "loadPerfis");
	}
	
	@SuppressWarnings("unused")
	private Usuario loadPerfis(Usuario usuario) {
		if(usuario.getPerfis() != null)
			Hibernate.initialize(usuario.getPerfis());
		return usuario;
	}
}
