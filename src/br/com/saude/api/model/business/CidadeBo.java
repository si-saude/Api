package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.CidadeBuilder;
import br.com.saude.api.model.creation.builder.example.CidadeExampleBuilder;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;
import br.com.saude.api.model.persistence.CidadeDao;

public class CidadeBo extends GenericBo<Cidade, CidadeFilter, CidadeDao, CidadeBuilder,
								CidadeExampleBuilder> {
	
	private static CidadeBo instance;
	
	private CidadeBo() {
		super();
	}
	
	public static CidadeBo getInstance() {
		if(instance==null)
			instance = new CidadeBo();
		return instance;
	}
}
