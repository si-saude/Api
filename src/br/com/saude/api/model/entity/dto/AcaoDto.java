package br.com.saude.api.model.entity.dto;

import java.util.ArrayList;

public class AcaoDto {
	private String descricao;
	private String status;
    private String tipoAcao;
    private String contatoAcao;
	private ArrayList<String> acompanhamentos;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public ArrayList<String> getAcompanhamentos() {
		return acompanhamentos;
	}
	public void setAcompanhamentos(ArrayList<String> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}
	
}
