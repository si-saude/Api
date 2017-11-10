package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.model.entity.po.Vacina;

public class FuncaoDao extends GenericDao<Funcao> {

	private static FuncaoDao instance;
	
	private FuncaoDao() {
		super();
	}
	
	public static FuncaoDao getInstance() {
		if(instance==null)
			instance = new FuncaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
		this.functionLoadAll = funcao -> {
			if (funcao.getVacinas()!=null)
				Hibernate.initialize(funcao.getVacinas());
			return funcao;
		};
		
		this.functionBeforeSave = pair -> {
			Funcao funcao = pair.getValue0();
			Session session = pair.getValue1();
			
			if (funcao.getVacinas()!=null) {
				for (int i=0; i<funcao.getVacinas().size();i++)
					funcao.getVacinas().set(i, session.get(Vacina.class, funcao.getVacinas().get(i).getId()));
			}
			
			return funcao;
		};
		
	}
	
	public Funcao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
