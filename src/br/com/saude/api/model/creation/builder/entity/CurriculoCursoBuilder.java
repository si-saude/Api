package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CurriculoCursoFilter;
import br.com.saude.api.model.entity.po.CurriculoCurso;

public class CurriculoCursoBuilder extends GenericEntityBuilder<CurriculoCurso,CurriculoCursoFilter> {

	private Function<Map<String,CurriculoCurso>,CurriculoCurso> loadCurso;
	
	public static CurriculoCursoBuilder newInstance(CurriculoCurso curriculoCurso) {
		return new CurriculoCursoBuilder(curriculoCurso);
	}
	
	public static CurriculoCursoBuilder newInstance(List<CurriculoCurso> curriculoCursos) {
		return new CurriculoCursoBuilder(curriculoCursos);
	}
	
	private CurriculoCursoBuilder(List<CurriculoCurso> curriculoCursos) {
		super(curriculoCursos);
	}

	private CurriculoCursoBuilder(CurriculoCurso curriculoCurso) {
		super(curriculoCurso);
	}

	@Override
	protected CurriculoCurso clone(CurriculoCurso curriculoCurso) {
		CurriculoCurso newCurriculoCurso = new CurriculoCurso();
		
		newCurriculoCurso.setId(curriculoCurso.getId());
		newCurriculoCurso.setData(curriculoCurso.getData());
		newCurriculoCurso.setVersion(curriculoCurso.getVersion());
		
		return newCurriculoCurso;
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCurso = curriculoCursos -> {
			if(curriculoCursos.get("origem").getCurso() != null) {
				curriculoCursos.get("destino").setCurso(CursoBuilder.newInstance(curriculoCursos.get("origem").getCurso()).getEntity());
			}
			return curriculoCursos.get("destino");
		};
	}

	public CurriculoCursoBuilder loadCurso() {
		return (CurriculoCursoBuilder) this.loadProperty(this.loadCurso);
	}

	@Override
	public CurriculoCurso cloneFromFilter(CurriculoCursoFilter filter) {
		return null;
	}
}
