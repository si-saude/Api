package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class ItemIndicadorConhecimentoAlimentarFilter extends GenericFilter {
	private String descricao;
	private BooleanFilter certo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BooleanFilter getCerto() {
		return certo;
	}

	public void setCerto(BooleanFilter certo) {
		this.certo = certo;
	}
	
}
