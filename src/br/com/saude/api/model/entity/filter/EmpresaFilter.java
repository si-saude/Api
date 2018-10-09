package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class EmpresaFilter extends GenericFilter {
	private String nome;
	private EmpresaFilter empresa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EmpresaFilter getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaFilter empresa) {
		this.empresa = empresa;
	}
	
}
