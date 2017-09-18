package br.com.saude.api.model.business;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.UsuarioBuilder;
import br.com.saude.api.model.creation.builder.example.UsuarioExampleBuilder;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;
import br.com.saude.api.model.persistence.UsuarioDao;

public class UsuarioBo {
	
	private static UsuarioBo instance;
	
	private UsuarioBo() {
		
	}
	
	public static UsuarioBo getInstance() {
		if(instance == null)
			instance = new UsuarioBo();
		return instance;
	}
	
	public PagedList<Usuario> getList(UsuarioFilter filter) throws Exception{
		PagedList<Usuario> usuarios = UsuarioDao.getInstance()
									.getList(UsuarioExampleBuilder.newInstance(filter).example());
		usuarios.setList(UsuarioBuilder.newInstance(usuarios.getList()).getEntityList());
		return usuarios;
	}
	
	public PagedList<Usuario> getListLoadPerfis(UsuarioFilter filter) throws Exception{
		PagedList<Usuario> usuarios = UsuarioDao.getInstance()
									.getListLoadPerfis(UsuarioExampleBuilder.newInstance(filter).example());
		usuarios.setList(UsuarioBuilder.newInstance(usuarios.getList()).loadPerfis().getEntityList());
		return usuarios;
	}
	
	public Usuario getById(int id) throws Exception {
		Usuario usuario = UsuarioDao.getInstance().getById(id);
		return UsuarioBuilder.newInstance(usuario).getEntity();
	}
	
	public Usuario getByIdLoadPerfis(int id) throws Exception {
		Usuario usuario = UsuarioDao.getInstance().getByIdLoadPerfis(id);
		return UsuarioBuilder.newInstance(usuario).loadPerfis().getEntity();
	}
	
	public Usuario save(Usuario usuario) throws Exception {
		usuario = UsuarioDao.getInstance().save(usuario);
		return UsuarioBuilder.newInstance(usuario).getEntity();
	}
	
	public void delete(int id) {
		UsuarioDao.getInstance().delete(id);
	}
	
	public Usuario getFirstToAutenticacao(UsuarioFilter filter) throws Exception {
		Usuario usuario = UsuarioDao.getInstance()
			.getFirst(UsuarioExampleBuilder.newInstance(filter).getExampleAutenticacao());
		return UsuarioBuilder.newInstance(usuario).getEntity();
	}
}
