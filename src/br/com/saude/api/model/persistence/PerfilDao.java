package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.PerfilExampleBuilder;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.entity.po.Permissao;

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
	
	public Perfil getByIdLoadPermissoes(Object id) throws Exception {
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
	
	@Override
	public Perfil save(Perfil perfil) throws Exception {
		return super.save(perfil,"deleteList");
	}
	
	@SuppressWarnings({ "unused", "deprecation", "unchecked" })
	private Perfil deleteList(Perfil perfil, Session session) {
		List<Permissao> permissoes = 
				(List<Permissao>)session.createCriteria(Permissao.class)
										.add(Restrictions.eq("perfil", perfil))
										.list();
		if(perfil.getPermissoes() != null)
			permissoes.removeIf(x->perfil.getPermissoes().stream().filter(y->y.getId() == x.getId()).count() > 0);
		
		permissoes.forEach(p-> session.delete(p));
		return perfil;
	}
}
