package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class EnderecoFilter extends GenericFilter {
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private CidadeFilter cidade;
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public CidadeFilter getCidade() {
		return cidade;
	}
	public void setCidade(CidadeFilter cidade) {
		this.cidade = cidade;
	}
	
	
}
