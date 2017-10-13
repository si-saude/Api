package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class GheFilter extends GenericFilter {

	private String nome;
	private String codigo;
	private DateFilter dataCriacao;
	private DateFilter dataDesativacao;
	private String descricao;
	private String descricaoAmbiente;
	private String descricaoTarefas;
	private int duracaoJornada;
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
	public String getDescricaoAmbiente() {
		return descricaoAmbiente;
	}
	public void setDescricaoAmbiente(String descricaoAmbiente) {
		this.descricaoAmbiente = descricaoAmbiente;
	}
	public String getDescricaoTarefas() {
		return descricaoTarefas;
	}
	public void setDescricaoTarefas(String descricaoTarefas) {
		this.descricaoTarefas = descricaoTarefas;
	}
	public int getDuracaoJornada() {
		return duracaoJornada;
	}
	public void setDuracaoJornada(int duracaoJornada) {
		this.duracaoJornada = duracaoJornada;
	}
}
