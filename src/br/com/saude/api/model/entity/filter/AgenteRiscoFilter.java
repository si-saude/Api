package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class AgenteRiscoFilter extends GenericFilter {

	private String descricao;
	private String categoriaAgenteRisco;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoriaAgenteRisco() {
		return categoriaAgenteRisco;
	}

	public void setCategoriaAgenteRisco(String categoriaAgenteRisco) {
		this.categoriaAgenteRisco = categoriaAgenteRisco;
	}
	
}
