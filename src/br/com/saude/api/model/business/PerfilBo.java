package br.com.saude.api.model.business;

import java.util.List;

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
	
	public List<Perfil> getList(PerfilFilter filter) throws Exception{
		List<Perfil> perfis = PerfilDao.getInstance()
								.getList(PerfilExampleBuilder.newInstance(filter).getExample());
		return PerfilBuilder.newInstance(perfis).getEntityList();
	}
	
	public List<Perfil> getListLoadPermissoes(PerfilFilter filter) throws Exception{
		List<Perfil> perfis = PerfilDao.getInstance()
								.getListLoadPermissoes(PerfilExampleBuilder.newInstance(filter).getExample());
		return PerfilBuilder.newInstance(perfis).loadPermissoes().getEntityList();
	}
	
	public Perfil getById(int id) throws Exception {
		Perfil perfil = PerfilDao.getInstance().getById(id);
		return PerfilBuilder.newInstance(perfil).getEntity();
	}
	
	public Perfil getByIdLoadPermissoes(int id) throws Exception {
		Perfil perfil = PerfilDao.getInstance().getbyIdLoadPermissoes(id);
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
