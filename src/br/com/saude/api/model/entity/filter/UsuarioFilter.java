package br.com.saude.api.model.entity.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.saude.api.generic.GenericFilter;

public class UsuarioFilter extends GenericFilter {
	
	@NotNull(message="� necess�rio informar a Chave do Usu�rio.")
	@Size(max = 8, message="Tamanho m�ximo para Chave: 8")
	private String chave;
	
	@NotNull(message="� necess�rio informar a Senha do Usu�rio.")
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
