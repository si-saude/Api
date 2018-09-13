package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmailFilter extends GenericFilter {
	private String anexos;
	private DateFilter dataEnvio;
	private String conteudo;
	private String assunto;
	
	public String getAnexos() {
		return anexos;
	}
	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}
	public DateFilter getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(DateFilter dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
}
