package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class UsuarioFilter extends GenericFilter {
	
	private String chave;
	private String senha;
	private PessoaFilter pessoa;
	private BooleanFilter gestorCss;
	
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
	public PessoaFilter getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFilter pessoa) {
		this.pessoa = pessoa;
	}
	public BooleanFilter getGestorCss() {
		return gestorCss;
	}
	public void setGestorCss(BooleanFilter gestorCss) {
		this.gestorCss = gestorCss;
	}
	
}
