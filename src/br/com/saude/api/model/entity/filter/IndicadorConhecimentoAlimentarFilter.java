package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class IndicadorConhecimentoAlimentarFilter extends GenericFilter {
	private String enunciado;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
}
