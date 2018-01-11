package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class FilaAtendimentoOcupacionalFilter extends GenericFilter {

	private ProfissionalFilter profissional;
	private LocalizacaoFilter localizacao;
	private DateFilter inicio;
	private DateFilter atualizacao;
	private DateFilter fim;
	private String status;
	
	public ProfissionalFilter getProfissional() {
		return profissional;
	}
	public void setProfissional(ProfissionalFilter profissional) {
		this.profissional = profissional;
	}
	public LocalizacaoFilter getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(LocalizacaoFilter localizacao) {
		this.localizacao = localizacao;
	}
	public DateFilter getInicio() {
		return inicio;
	}
	public void setInicio(DateFilter inicio) {
		this.inicio = inicio;
	}
	public DateFilter getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(DateFilter atualizacao) {
		this.atualizacao = atualizacao;
	}
	public DateFilter getFim() {
		return fim;
	}
	public void setFim(DateFilter fim) {
		this.fim = fim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
