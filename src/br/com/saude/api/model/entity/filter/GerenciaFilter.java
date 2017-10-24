package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class GerenciaFilter extends GenericFilter {
	private String codigo;
	private String codigoCompleto;
	private String descricao;
	private EmpregadoFilter gerente;
	private EmpregadoFilter secretario1;
	private EmpregadoFilter secretario2;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoCompleto() {
		return codigoCompleto;
	}
	public void setCodigoCompleto(String codigoCompleto) {
		this.codigoCompleto = codigoCompleto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public EmpregadoFilter getGerente() {
		return gerente;
	}
	public void setGerente(EmpregadoFilter gerente) {
		this.gerente = gerente;
	}
	public EmpregadoFilter getSecretario1() {
		return secretario1;
	}
	public void setSecretario1(EmpregadoFilter secretario1) {
		this.secretario1 = secretario1;
	}
	public EmpregadoFilter getSecretario2() {
		return secretario2;
	}
	public void setSecretario2(EmpregadoFilter secretario2) {
		this.secretario2 = secretario2;
	}
}
