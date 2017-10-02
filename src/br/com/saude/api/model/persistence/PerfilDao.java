package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilDao extends GenericDao<Perfil> {
	
	private static PerfilDao instance;
	
	private PerfilDao() {
		super();
		
		this.functionLoadAll = perfil -> {
			if(perfil.getPermissoes() != null)
				Hibernate.initialize(perfil.getPermissoes());
			return perfil;
		};
		
		this.functionBeforeSave = pair -> {
			Perfil perfil = pair.getValue0();
			
			//SETA O PERFIL NAS PERMISSÕES
			perfil.getPermissoes().forEach(p-> p.setPerfil(perfil));
			
			return perfil;
		};
	}
	
	public static PerfilDao getInstance() {
		if(instance == null)
			instance = new PerfilDao();
		return instance;
	}
	
	public Perfil getByIdLoadPermissoes(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	public PagedList<Perfil> getListLoadPermissoes(GenericExampleBuilder<?,?> perfilExampleBuilder) throws Exception{
		return this.getList(perfilExampleBuilder, this.functionLoadAll);
	}
}
