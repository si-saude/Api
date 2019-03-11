package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;

public class AvaliacaoHigieneOcupacionalDao extends GenericDao<AvaliacaoHigieneOcupacional> {

	private static AvaliacaoHigieneOcupacionalDao instance;
	
	private AvaliacaoHigieneOcupacionalDao() {
		super();
	}
	
	public static AvaliacaoHigieneOcupacionalDao getInstance() {
		if(instance==null)
			instance = new AvaliacaoHigieneOcupacionalDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	public AvaliacaoHigieneOcupacional getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
}