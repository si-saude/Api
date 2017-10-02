package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.javatuples.Pair;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoDao extends GenericDao<Funcao> {
	
	private static FuncaoDao instance;
	private Function<Funcao,Funcao> functionLoad;
	private Function<Pair<Funcao,Session>,Funcao> functionBeforeSave;
	
	private FuncaoDao() {
		super();
		
		this.functionLoad = funcao -> {
			if(funcao.getCursos()!=null)
				Hibernate.initialize(funcao.getCursos());
			return funcao;
		};
		
		this.functionBeforeSave = pair -> {
			Funcao funcao = pair.getValue0();
			Session session = pair.getValue1();
			
			if(funcao.getCursos() != null)
				for(int i=0; i < funcao.getCursos().size(); i++)
					funcao.getCursos().set(i, session.get(Curso.class, funcao.getCursos().get(i).getId()));
			
			return funcao;
		};
	}
	
	public static FuncaoDao getInstance() {
		if(instance == null)
			instance = new FuncaoDao();
		return instance;
	}
	
	public Funcao getByIdLoadCursos(Object id) throws Exception {
		return super.getById(id,this.functionLoad);
	}
	
	@Override
	public Funcao save(Funcao funcao) throws Exception {
		return super.save(funcao,this.functionBeforeSave);
	}
}
