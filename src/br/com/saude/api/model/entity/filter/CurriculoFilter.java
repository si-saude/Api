package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CurriculoFilter extends GenericFilter {

	private String historico;
	private String formacao;
	private String atuacao;
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public String getAtuacao() {
		return atuacao;
	}
	public void setAtuacao(String atuacao) {
		this.atuacao = atuacao;
	}
	
	
}
