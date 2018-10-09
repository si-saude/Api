package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CnaeFilter extends GenericFilter {
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
