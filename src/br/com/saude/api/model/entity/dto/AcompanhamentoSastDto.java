package br.com.saude.api.model.entity.dto;

import java.util.ArrayList;

public class AcompanhamentoSastDto {
	private String nome;
	private String matricula;
	private String gerencia;
	private String statusRisco;
	private String statusRPSat;
	private String equipe;
	private String indicador;
	private String diagnostico;
	private String intervencao;
	private int idTriagem;
	private int idAcao;
	private ArrayList<AcaoDto> acoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getStatusRisco() {
		return statusRisco;
	}
	public void setStatusRisco(String statusRisco) {
		this.statusRisco = statusRisco;
	}
	public String getStatusRPSat() {
		return statusRPSat;
	}
	public void setStatusRPSat(String statusRPSat) {
		this.statusRPSat = statusRPSat;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getIntervencao() {
		return intervencao;
	}
	public void setIntervencao(String intervencao) {
		this.intervencao = intervencao;
	}
	public int getIdTriagem() {
		return idTriagem;
	}
	public void setIdTriagem(int idTriagem) {
		this.idTriagem = idTriagem;
	}
	public int getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(int idAcao) {
		this.idAcao = idAcao;
	}
	public String getDescricaoAcao() {
		return descricaoAcao;
	}
	public void setDescricaoAcao(String descricaoAcao) {
		this.descricaoAcao = descricaoAcao;
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
	public String getDescricaoAcompanhamento() {
		return descricaoAcompanhamento;
	}
	public void setDescricaoAcompanhamento(String descricaoAcompanhamento) {
		this.descricaoAcompanhamento = descricaoAcompanhamento;
	}
	public ArrayList<AcaoDto> getAcoes() {
		return acoes;
	}
	public void setAcoes(ArrayList<AcaoDto> acoes) {
		this.acoes = acoes;
	}
	
}
