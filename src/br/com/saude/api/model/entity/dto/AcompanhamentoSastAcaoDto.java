package br.com.saude.api.model.entity.dto;

import java.util.List;

public class AcompanhamentoSastAcaoDto {
	private String acao;
	private String tipoAcao;
	private String contatoAcao;
	private String statusAcao;
	private int idAcao;
	private List<String> acompanhamentos;
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getTipoAcao() {
		return tipoAcao;
	}
	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}
	public String getContatoAcao() {
		return contatoAcao;
	}
	public void setContatoAcao(String contatoAcao) {
		this.contatoAcao = contatoAcao;
	}
	public String getStatusAcao() {
		return statusAcao;
	}
	public void setStatusAcao(String statusAcao) {
		this.statusAcao = statusAcao;
	}
	public List<String> getAcompanhamentos() {
		return acompanhamentos;
	}
	public void setAcompanhamentos(List<String> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}
	public int getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(int idAcao) {
		this.idAcao = idAcao;
	}
}
