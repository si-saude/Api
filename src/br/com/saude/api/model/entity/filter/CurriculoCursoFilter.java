package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class CurriculoCursoFilter extends GenericFilter {
	
	private CurriculoFilter curriculo;
	private CursoFilter curso;
	private DateFilter data;
	public CurriculoFilter getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(CurriculoFilter curriculo) {
		this.curriculo = curriculo;
	}
	public CursoFilter getCurso() {
		return curso;
	}
	public void setCurso(CursoFilter curso) {
		this.curso = curso;
	}
	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
}
