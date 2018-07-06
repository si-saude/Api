package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoDao extends GenericDao<Atestado> {

	private static AtestadoDao instance;
	
	private AtestadoDao() {
		super();
	}
	
	public static AtestadoDao getInstance() {
		if(instance==null)
			instance = new AtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atestado -> {
			atestado = loadCat(atestado);
			
			return atestado;
		};
	}
	
	private Atestado loadCat(Atestado atestado) {
		if(atestado.getCat()!=null) {
			Hibernate.initialize(atestado.getCat());
		}
		return atestado;
	}
	
	public Atestado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}