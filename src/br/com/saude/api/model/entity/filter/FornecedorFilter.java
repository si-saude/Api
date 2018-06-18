package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class FornecedorFilter extends GenericFilter {

	private String razaoSocial;
	private String tipoPessoa;
	private String cpfCnpj;
	private String codigoSap;
	private String email;
	private String atividadeFornecedor;
	private EnderecoFilter endereco;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EnderecoFilter getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoFilter endereco) {
		this.endereco = endereco;
	}
	
	public String getAtividadeFornecedor() {
		return atividadeFornecedor;
	}
	public void setAtividadeFornecedor(String atividadeFornecedor) {
		this.atividadeFornecedor = atividadeFornecedor;
	}
	
	
}
