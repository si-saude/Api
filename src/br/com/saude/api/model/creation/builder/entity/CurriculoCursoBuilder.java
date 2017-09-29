package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CurriculoCursoFilter;
import br.com.saude.api.model.entity.po.CurriculoCurso;

public class CurriculoCursoBuilder extends GenericEntityBuilder<CurriculoCurso,CurriculoCursoFilter> {

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
	

	public CurriculoCursoBuilder loadCurso() {
		if(this.entity != null) {
			this.newEntity = loadCurso(this.entity,this.newEntity);
		}else {
			for(CurriculoCurso curriculoCurso:this.entityList) {
				CurriculoCurso newCurriculoCurso = this.newEntityList.stream()
						.filter(e->e.getId() == curriculoCurso.getId())
						.iterator().next();
				newCurriculoCurso = loadCurso(curriculoCurso,newCurriculoCurso);
			}
		}
		return this;
	}
	
	private CurriculoCurso loadCurso(CurriculoCurso origem,CurriculoCurso destino) {
		if(origem.getCurso() != null) {
			destino.setCurso(CursoBuilder.newInstance(origem.getCurso()).getEntity());
		}
		
		return destino;
	}

	@Override
	public CurriculoCurso cloneFromFilter(CurriculoCursoFilter filter) {
		return null;
	}
}
