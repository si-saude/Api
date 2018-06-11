package br.com.saude.api.model.entity.dto;

import java.util.Date;

public class PanoramaDto {

	private int id;
	private String gerencia;
	private String matricula;
	private String nome;
	private String mesConvocacao;
	private String base;
	private Date dataAsoAnoAnterior;
	private Date dataAsoAnoAtual;
	private Date dataRealizacaoPreClinico;
	private String situacaoAso;
	private String asoNoPrazo;
	private String pendencias;
	private String gerenciaPrimeiraLinha;
	private String statusPreClinico;
	private String examesPendentes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getGerenciaPrimeiraLinha() {
		return gerenciaPrimeiraLinha;
	}
	public void setGerenciaPrimeiraLinha(String gerenciaPrimeiraLinha) {
		this.gerenciaPrimeiraLinha = gerenciaPrimeiraLinha;
	}
	public String getMesConvocacao() {
		return mesConvocacao;
	}
	public void setMesConvocacao(String mesConvocacao) {
		this.mesConvocacao = mesConvocacao;
	}
	public String getAsoNoPrazo() {
		return asoNoPrazo;
	}
	public void setAsoNoPrazo(String asoNoPrazo) {
		this.asoNoPrazo = asoNoPrazo;
	}
	public String getPendencias() {
		return pendencias;
	}
	public void setPendencias(String pendencias) {
		this.pendencias = pendencias;
	}
	public String getStatusPreClinico() {
		return statusPreClinico;
	}
	public void setStatusPreClinico(String statusPreClinico) {
		this.statusPreClinico = statusPreClinico;
	}
	public String getExamesPendentes() {
		return examesPendentes;
	}
	public void setExamesPendentes(String examesPendentes) {
		this.examesPendentes = examesPendentes;
	}
	
}
