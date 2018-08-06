package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Imovel;

public class ImovelDao extends GenericDao<Imovel> {
	
	private static ImovelDao instance;
	
	private ImovelDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = imovel -> {
			
			if(imovel.getBase()!=null)
				Hibernate.initialize(imovel.getBase());
			
			return imovel;
		};
	}
	
	public static ImovelDao getInstance() {
		if(instance == null)
			instance = new ImovelDao();
		return instance;
	}
	
	public Imovel getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}

}
