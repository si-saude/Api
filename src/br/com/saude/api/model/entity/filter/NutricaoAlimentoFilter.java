package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class NutricaoAlimentoFilter extends GenericFilter {

	private BooleanFilter inativo;
	
	public BooleanFilter getInativo() {
		return inativo;
	}
	
	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}
	
}
