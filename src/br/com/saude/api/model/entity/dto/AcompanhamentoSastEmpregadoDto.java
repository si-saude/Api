package br.com.saude.api.model.entity.dto;

import java.util.List;

public class AcompanhamentoSastEmpregadoDto {
	private String nome;
	private String matricula;
	private String gerencia;
	private String statusRisco;
	private String statusRPSat;
	private List<AcompanhamentoSastEquipeDto> equipes;
	
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
	public List<AcompanhamentoSastEquipeDto> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<AcompanhamentoSastEquipeDto> equipes) {
		this.equipes = equipes;
	}
	
}
