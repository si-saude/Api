package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoDao extends GenericDao<Funcao> {
	
	private static FuncaoDao instance;
	
	private FuncaoDao() {
		super();
	}
	
	public static FuncaoDao getInstance() {
		if(instance == null)
			instance = new FuncaoDao();
		return instance;
	}
	
	public Funcao getByIdLoadCursos(Object id) throws Exception {
		return super.getById(id,"loadCursos");
	}
	
	@SuppressWarnings("unused")
	private Funcao loadCursos(Funcao funcao) {
		if(funcao.getCursos()!=null)
			Hibernate.initialize(funcao.getCursos());
		return funcao;
	}
	
	@Override
	public Funcao save(Funcao funcao) throws Exception {
		return super.save(funcao,"beforeCommitSave");
	}
	
	@SuppressWarnings({ "unused" })
	private Funcao beforeCommitSave(Funcao funcao, Session session) {
		if(funcao.getCursos() != null)
			for(int i=0; i < funcao.getCursos().size(); i++)
				funcao.getCursos().set(i, session.get(Curso.class, funcao.getCursos().get(i).getId()));
		return funcao;
	}
}
