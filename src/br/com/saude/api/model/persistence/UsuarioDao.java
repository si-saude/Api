package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {
	
	private Function<Usuario,Usuario> functionLoadPermissoes;

	
	private static UsuarioDao instance;
	
	private UsuarioDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = usuario -> {
			//passei para a funcao loadPerfil o que estava sendo feito aqui 
			usuario = loadPerfil(usuario);
			return usuario;
		};
		
		this.functionLoadPermissoes = usuario -> {
			usuario = this.functionLoadAll.apply(usuario);
			//criei a funcao loadPermissao para inicializar as permissoes
			usuario = loadPermissao(usuario);
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
	
	public Usuario getByIdLoadPermissoes(Object id) throws Exception {
		return this.getById(id,this.functionLoadPermissoes);
	}
	
	public PagedList<Usuario> getListLoadPerfis(GenericExampleBuilder<?, ?> usuarioExampleBuilder) throws Exception{
		return this.getList(usuarioExampleBuilder, this.functionLoadAll);
	}
	
	private Usuario loadPerfil(Usuario usuario) {
		if(usuario.getPerfis() != null)
			Hibernate.initialize(usuario.getPerfis());
		return usuario;
	}
	
	private Usuario loadPermissao(Usuario usuario) {
		if (usuario.getPerfis() != null) {
			Hibernate.initialize(usuario.getPerfis());
			
			usuario.getPerfis().forEach(u -> {
				if (u.getPermissoes() != null) {
					Hibernate.initialize(u.getPermissoes());
				}
			});
		}
		return usuario;
	}
}
