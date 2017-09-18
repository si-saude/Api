package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class UsuarioFilter extends GenericFilter {
	
	private String chave;
	private String senha;
	
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
