package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CurriculoCursoFilter extends GenericFilter {
	
	private CurriculoFilter curriculo;
	private CursoFilter curso;
	private int validade;
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
	public int getValidade() {
		return validade;
	}
	public void setValidade(int validade) {
		this.validade = validade;
	}
}
