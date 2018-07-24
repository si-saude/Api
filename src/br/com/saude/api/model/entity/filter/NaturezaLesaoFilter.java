package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class NaturezaLesaoFilter extends GenericFilter {
	private String codigo;
	private String Descricao;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
}
