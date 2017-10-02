package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {
	
	private static UsuarioDao instance;
	
	private UsuarioDao() {
		super();
		
		this.functionLoadAll = usuario -> {
			if(usuario.getPerfis() != null)
				Hibernate.initialize(usuario.getPerfis());
			return usuario;
		};
		
		this.functionBeforeSave = pair -> {
			Usuario usuario = pair.getValue0();
			Session session = pair.getValue1();
			
			//CARREGA OS PERFIS SELECIONADOS PARA QUE O HIBERNATE SALVE
			if(usuario.getPerfis()!=null)
				for(int i=0; i < usuario.getPerfis().size(); i++)
					usuario.getPerfis().set(i, session.get(Perfil.class, usuario.getPerfis().get(i).getId()));
			return usuario;
		};
	}
	
	public static UsuarioDao getInstance() {
		if(instance == null)
			instance = new UsuarioDao();
		return instance;
	}
	
	public Usuario getByIdLoadPerfis(Object id) throws Exception {
		return this.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Usuario> getListLoadPerfis(GenericExampleBuilder<?, ?> usuarioExampleBuilder) throws Exception{
		return this.getList(usuarioExampleBuilder, this.functionLoadAll);
	}
}
