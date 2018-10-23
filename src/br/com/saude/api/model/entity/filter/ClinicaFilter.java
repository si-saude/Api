package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ClinicaFilter extends GenericFilter {

	private String nome;
	private String uf;
	private String endereco;
	private String telefones;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}
	
}
