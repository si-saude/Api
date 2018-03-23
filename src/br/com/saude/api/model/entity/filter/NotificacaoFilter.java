package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class NotificacaoFilter extends GenericFilter {

	private UsuarioFilter usuario;
	private String descricao;
	public UsuarioFilter getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioFilter usuario) {
		this.usuario = usuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
