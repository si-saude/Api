package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CatFilter extends GenericFilter {

	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
