package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class FuncaoFilter extends GenericFilter {
	private String nome;
	private String cursosObrigatorios;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCursosObrigatorios() {
		return cursosObrigatorios;
	}
	public void setCursosObrigatorios(String cursosObrigatorios) {
		this.cursosObrigatorios = cursosObrigatorios;
	}
}
