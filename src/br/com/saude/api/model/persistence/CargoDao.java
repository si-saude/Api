package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.entity.po.Vacina;

public class CargoDao extends GenericDao<Cargo> {
	
	private static CargoDao instance;
	
	private CargoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = cargo -> {
			
			if(cargo.getCursos()!=null)
				Hibernate.initialize(cargo.getCursos());
			
			if(cargo.getVacinas()!=null)
				Hibernate.initialize(cargo.getVacinas());
			
			return cargo;
		};
		
		this.functionBeforeSave = pair -> {
			Cargo cargo = pair.getValue0();
			Session session = pair.getValue1();
			
			if(cargo.getCursos() != null)
				for(int i=0; i < cargo.getCursos().size(); i++)
					cargo.getCursos().set(i, session.get(Curso.class, cargo.getCursos().get(i).getId()));
			
			if(cargo.getVacinas() != null)
				for(int i=0; i < cargo.getVacinas().size(); i++)
					cargo.getVacinas().set(i, session.get(Vacina.class, cargo.getVacinas().get(i).getId()));
			
			return cargo;
		};
	}
	
	public static CargoDao getInstance() {
		if(instance == null)
			instance = new CargoDao();
		return instance;
	}
	
	public Cargo getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
