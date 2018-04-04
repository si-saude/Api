package br.com.saude.api.model.entity.dto;

import java.util.Date;
import java.util.List;

public class PanoramaDto {

	private String gerencia;
	private String matricula;
	private String nome;
	private Date dataConvocacao;
	private String base;
	private Date dataAsoAnoAnterior;
	private Date dataAsoAnoAtual;
	private Date dataRealizacaoPreClinico;
	private String situacaoAso;
	private String prazoAso;
	private List<String> pendencias;
	private String gerenciaPrimeiraLinha;
	
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataConvocacao() {
		return dataConvocacao;
	}
	public void setDataConvocacao(Date dataConvocacao) {
		this.dataConvocacao = dataConvocacao;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Date getDataAsoAnoAnterior() {
		return dataAsoAnoAnterior;
	}
	public void setDataAsoAnoAnterior(Date dataAsoAnoAnterior) {
		this.dataAsoAnoAnterior = dataAsoAnoAnterior;
	}
	public Date getDataAsoAnoAtual() {
		return dataAsoAnoAtual;
	}
	public void setDataAsoAnoAtual(Date dataAsoAnoAtual) {
		this.dataAsoAnoAtual = dataAsoAnoAtual;
	}
	public Date getDataRealizacaoPreClinico() {
		return dataRealizacaoPreClinico;
	}
	public void setDataRealizacaoPreClinico(Date dataRealizacaoPreClinico) {
		this.dataRealizacaoPreClinico = dataRealizacaoPreClinico;
	}
	public String getSituacaoAso() {
		return situacaoAso;
	}
	public void setSituacaoAso(String situacaoAso) {
		this.situacaoAso = situacaoAso;
	}
	public String getPrazoAso() {
		return prazoAso;
	}
	public void setPrazoAso(String prazoAso) {
		this.prazoAso = prazoAso;
	}
	public List<String> getPendencias() {
		return pendencias;
	}
	public void setPendencias(List<String> pendencias) {
		this.pendencias = pendencias;
	}
	public String getGerenciaPrimeiraLinha() {
		return gerenciaPrimeiraLinha;
	}
	public void setGerenciaPrimeiraLinha(String gerenciaPrimeiraLinha) {
		this.gerenciaPrimeiraLinha = gerenciaPrimeiraLinha;
	}
}
