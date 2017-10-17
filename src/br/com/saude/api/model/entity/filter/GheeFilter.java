package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class GheeFilter extends GenericFilter {

	private String nome;
	private String codigo;
	private DateFilter dataCriacao;
	private DateFilter dataDesativacao;
	private String descricao;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public DateFilter getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(DateFilter dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public DateFilter getDataDesativacao() {
		return dataDesativacao;
	}
	public void setDataDesativacao(DateFilter dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
