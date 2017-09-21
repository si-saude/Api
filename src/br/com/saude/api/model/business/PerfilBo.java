package br.com.saude.api.model.business;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.PerfilBuilder;
import br.com.saude.api.model.creation.builder.example.PerfilExampleBuilder;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.persistence.PerfilDao;

public class PerfilBo {
	
	private static PerfilBo instance;
	
	private PerfilBo() {
		
	}
	
	public static PerfilBo getInstance() {
		if(instance == null)
			instance = new PerfilBo();
		return instance;
	}
	
	public PagedList<Perfil> getList(PerfilFilter filter) throws Exception{
		PagedList<Perfil> perfis = PerfilDao.getInstance()
								.getList(PerfilExampleBuilder.newInstance(filter).example());
		perfis.setList(PerfilBuilder.newInstance(perfis.getList()).getEntityList());
		return perfis;
	}
	
	public PagedList<Perfil> getListLoadPermissoes(PerfilFilter filter) throws Exception{
		PagedList<Perfil> perfis = PerfilDao.getInstance()
								.getListLoadPermissoes(PerfilExampleBuilder.newInstance(filter).example());
		perfis.setList(PerfilBuilder.newInstance(perfis.getList()).loadPermissoes().getEntityList());
		return perfis;
	}
	
	public Perfil getById(int id) throws Exception {
		Perfil perfil = PerfilDao.getInstance().getById(id);
		return PerfilBuilder.newInstance(perfil).getEntity();
	}
	
	public Perfil getByIdLoadPermissoes(int id) throws Exception {
		Perfil perfil = PerfilDao.getInstance().getByIdLoadPermissoes(id);
		return PerfilBuilder.newInstance(perfil).loadPermissoes().getEntity();
	}
	
	public Perfil save(Perfil perfil) throws Exception {
		perfil = PerfilBuilder.newInstance(perfil).loadPermissoesSetPerfil().getEntity();
		perfil = PerfilDao.getInstance().save(perfil);
		return PerfilBuilder.newInstance(perfil).getEntity();
	}
	
	public void delete(int id) {
		PerfilDao.getInstance().delete(id);
	}
}
