package br.com.saude.api.model.entity.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.saude.api.generic.GenericFilter;

public class UsuarioFilter extends GenericFilter {
	
	@NotNull(message="É necessário informar a Chave do Usuário.")
	@Size(max = 8, message="Tamanho máximo para Chave: 8")
	private String chave;
	
	@NotNull(message="É necessário informar a Senha do Usuário.")
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
