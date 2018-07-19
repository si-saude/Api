package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AvaliacaoHigieneOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.AvaliacaoHigieneOcupacionalExampleBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;
import br.com.saude.api.model.persistence.AvaliacaoHigieneOcupacionalDao;

public class AvaliacaoHigieneOcupacionalBo 
	extends GenericBo<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter, AvaliacaoHigieneOcupacionalDao, AvaliacaoHigieneOcupacionalBuilder, AvaliacaoHigieneOcupacionalExampleBuilder> {

private static AvaliacaoHigieneOcupacionalBo instance;
	
	private AvaliacaoHigieneOcupacionalBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadLocal();
		};
	}
	
	public static AvaliacaoHigieneOcupacionalBo getInstance() {
		if(instance==null)
			instance = new AvaliacaoHigieneOcupacionalBo();
		return instance;
	}
	
	@Override
	public AvaliacaoHigieneOcupacional getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id),this.functionLoadAll);
	}
}