package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class IndicadorConhecimentoAlimentarFilter extends GenericFilter {
	private String enunciado;
	private BooleanFilter inativo;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public BooleanFilter getInativo() {
		return inativo;
	}

	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}
	
}
