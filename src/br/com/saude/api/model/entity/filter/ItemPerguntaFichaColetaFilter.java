package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ItemPerguntaFichaColetaFilter extends GenericFilter {
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
