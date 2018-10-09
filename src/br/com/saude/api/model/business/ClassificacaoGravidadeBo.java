package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ClassificacaoGravidadeBuilder;
import br.com.saude.api.model.creation.builder.example.ClassificacaoGravidadeExampleBuilder;
import br.com.saude.api.model.entity.filter.ClassificacaoGravidadeFilter;
import br.com.saude.api.model.entity.po.ClassificacaoGravidade;
import br.com.saude.api.model.persistence.ClassificacaoGravidadeDao;

public class ClassificacaoGravidadeBo 
	extends GenericBo<ClassificacaoGravidade, ClassificacaoGravidadeFilter, ClassificacaoGravidadeDao, 
		ClassificacaoGravidadeBuilder, ClassificacaoGravidadeExampleBuilder> {
	
	private static ClassificacaoGravidadeBo instance;
	
	private ClassificacaoGravidadeBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static ClassificacaoGravidadeBo getInstance() {
		if(instance==null)
			instance = new ClassificacaoGravidadeBo();
		return instance;
	}
}