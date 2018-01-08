package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FilaEsperaOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.FilaEsperaOcupacionalExampleBuilder;
import br.com.saude.api.model.entity.filter.FilaEsperaOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.persistence.FilaEsperaOcupacionalDao;

public class FilaEsperaOcupacionalBo 
	extends GenericBo<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter, 
					FilaEsperaOcupacionalDao, FilaEsperaOcupacionalBuilder, 
					FilaEsperaOcupacionalExampleBuilder> {

	private static FilaEsperaOcupacionalBo instance;
	
	private FilaEsperaOcupacionalBo() {
		super();
	}
	
	public static FilaEsperaOcupacionalBo getInstance() {
		if(instance == null)
			instance = new FilaEsperaOcupacionalBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
