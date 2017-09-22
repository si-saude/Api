package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class GerenciaFilter extends GenericFilter {
	private String codigo;
	private String codigoCompleto;
	private String descricao;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoCompleto() {
		return codigoCompleto;
	}
	public void setCodigoCompleto(String codigoCompleto) {
		this.codigoCompleto = codigoCompleto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
