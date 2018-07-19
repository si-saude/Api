package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

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
		this.functionLoadAll = avaliacaoHigieneOcupacional -> {
			avaliacaoHigieneOcupacional = loadLocal(avaliacaoHigieneOcupacional);
			
			return avaliacaoHigieneOcupacional;
		};
	}

	private AvaliacaoHigieneOcupacional loadLocal(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		if(avaliacaoHigieneOcupacional.getLocal() != null)
			Hibernate.initialize(avaliacaoHigieneOcupacional.getLocal());
		return avaliacaoHigieneOcupacional;
	}
	
	public AvaliacaoHigieneOcupacional getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
}