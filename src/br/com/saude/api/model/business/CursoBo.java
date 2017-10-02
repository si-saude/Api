package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.CursoBuilder;
import br.com.saude.api.model.creation.builder.example.CursoExampleBuilder;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.persistence.CursoDao;

public class CursoBo extends GenericBo<Curso, CursoFilter, CursoDao, CursoBuilder, 
								CursoExampleBuilder> {

	private static CursoBo instance;
	
	private CursoBo() {
		super();
	}
	
	public static CursoBo getInstance() {
		if(instance==null)
			instance = new CursoBo();
		return instance;
	}
}
