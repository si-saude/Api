package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;

public class CursoBuilder extends GenericEntityBuilder<Curso,CursoFilter> {

	public static CursoBuilder newInstance(Curso curso) {
		return new CursoBuilder(curso);
	}
	
	public static CursoBuilder newInstance(List<Curso> cursos) {
		return new CursoBuilder(cursos);
	}
	
	private CursoBuilder(List<Curso> cursos) {
		super(cursos);
	}

	private CursoBuilder(Curso curso) {
		super(curso);
	}

	@Override
	protected Curso clone(Curso curso) {
		Curso newCurso = new Curso();
		
		newCurso.setId(curso.getId());
		newCurso.setNome(curso.getNome());
		newCurso.setDescricao(curso.getDescricao());
		newCurso.setValidade(curso.getValidade());
		newCurso.setVersion(curso.getVersion());
		
		return newCurso;
	}

	@Override
	public Curso cloneFromFilter(CursoFilter filter) {
		return null;
	}

}
