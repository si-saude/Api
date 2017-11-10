package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CargoFilter extends GenericFilter {
	private String nome;
	private String operador;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
}
