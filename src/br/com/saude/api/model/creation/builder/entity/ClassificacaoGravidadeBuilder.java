package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ClassificacaoGravidadeFilter;
import br.com.saude.api.model.entity.po.ClassificacaoGravidade;

public class ClassificacaoGravidadeBuilder extends GenericEntityBuilder<ClassificacaoGravidade,ClassificacaoGravidadeFilter> {
	public static ClassificacaoGravidadeBuilder newInstance(ClassificacaoGravidade classificacaoGravidade) {
		return new ClassificacaoGravidadeBuilder(classificacaoGravidade);
	}
	
	public static ClassificacaoGravidadeBuilder newInstance(List<ClassificacaoGravidade> classificacaoGravidades) {
		return new ClassificacaoGravidadeBuilder(classificacaoGravidades);
	}
	
	private ClassificacaoGravidadeBuilder(List<ClassificacaoGravidade> classificacaoGravidades) {
		super(classificacaoGravidades);
	}

	private ClassificacaoGravidadeBuilder(ClassificacaoGravidade classificacaoGravidade) {
		super(classificacaoGravidade);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected ClassificacaoGravidade clone(ClassificacaoGravidade classificacaoGravidade) {
		ClassificacaoGravidade newClassificacaoGravidade = new ClassificacaoGravidade();
		
		newClassificacaoGravidade.setId(classificacaoGravidade.getId());
		newClassificacaoGravidade.setTitulo(classificacaoGravidade.getTitulo());
		newClassificacaoGravidade.setVersion(classificacaoGravidade.getVersion());
		
		return newClassificacaoGravidade;
	}

	@Override
	public ClassificacaoGravidade cloneFromFilter(ClassificacaoGravidadeFilter filter) {
		return null;
	}

}
