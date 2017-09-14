package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;

public class EmpregadoFilter {
	private int id;
	private String nome;
	private String cpf;
	private DateFilter dataNascimento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public DateFilter getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(DateFilter dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
