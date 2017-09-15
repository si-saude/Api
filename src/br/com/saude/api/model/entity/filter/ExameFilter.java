package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ExameFilter extends GenericFilter {
	private String codigo;
	private String descricao;
	private EmpregadoFilter empregado;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
}
