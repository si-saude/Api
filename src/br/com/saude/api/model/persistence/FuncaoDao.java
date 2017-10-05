package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.model.entity.po.Vacina;

public class FuncaoDao extends GenericDao<Funcao> {
	
	private static FuncaoDao instance;
	
	private FuncaoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = funcao -> {
			
			if(funcao.getCursos()!=null)
				Hibernate.initialize(funcao.getCursos());
			
			if(funcao.getVacinas()!=null)
				Hibernate.initialize(funcao.getVacinas());
			
			return funcao;
		};
		
		this.functionBeforeSave = pair -> {
			Funcao funcao = pair.getValue0();
			Session session = pair.getValue1();
			
			if(funcao.getCursos() != null)
				for(int i=0; i < funcao.getCursos().size(); i++)
					funcao.getCursos().set(i, session.get(Curso.class, funcao.getCursos().get(i).getId()));
			
			if(funcao.getVacinas() != null)
				for(int i=0; i < funcao.getVacinas().size(); i++)
					funcao.getVacinas().set(i, session.get(Vacina.class, funcao.getVacinas().get(i).getId()));
			
			return funcao;
		};
	}
	
	public static FuncaoDao getInstance() {
		if(instance == null)
			instance = new FuncaoDao();
		return instance;
	}
	
	public Funcao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
