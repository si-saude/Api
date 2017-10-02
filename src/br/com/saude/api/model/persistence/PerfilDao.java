package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.javatuples.Pair;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.PerfilExampleBuilder;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilDao extends GenericDao<Perfil> {
	
	private static PerfilDao instance;
	private Function<Perfil,Perfil> functionLoadAll;
	private Function<Pair<Perfil,Session>,Perfil> functionBeforeSave;
	
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
	
	public PagedList<Perfil> getListLoadPermissoes(PerfilExampleBuilder perfilExampleBuilder) throws Exception{
		return this.getList(perfilExampleBuilder, this.functionLoadAll);
	}
	
	@Override
	public Perfil save(Perfil perfil) throws Exception {
		return super.save(perfil,this.functionBeforeSave);
	}
}
