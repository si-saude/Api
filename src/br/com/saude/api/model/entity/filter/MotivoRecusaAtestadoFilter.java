package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class MotivoRecusaAtestadoFilter extends GenericFilter {

	private String descricao;
	private BooleanFilter permiteReabertura;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BooleanFilter getPermiteReabertura() {
		return permiteReabertura;
	}
	public void setPermiteReabertura(BooleanFilter permiteReabertura) {
		this.permiteReabertura = permiteReabertura;
	}

}
