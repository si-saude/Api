package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;

public class FilaEsperaOcupacionalDao extends GenericDao<FilaEsperaOcupacional> {

	private static FilaEsperaOcupacionalDao instance;
	
	private FilaEsperaOcupacionalDao() {
		super();
	}
	
	public static FilaEsperaOcupacionalDao getInstance() {
		if(instance == null)
			instance = new FilaEsperaOcupacionalDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
