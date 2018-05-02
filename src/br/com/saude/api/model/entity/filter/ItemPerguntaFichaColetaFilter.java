package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ItemPerguntaFichaColetaFilter extends GenericFilter {
	private String label;
	private String path;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
