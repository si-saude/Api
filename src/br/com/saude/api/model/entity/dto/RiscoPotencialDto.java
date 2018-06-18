package br.com.saude.api.model.entity.dto;

public class RiscoPotencialDto {
	private int id;
	private double ranking;
	private String statusRPSat;
	private String empregadoNome;
	private String equipeResponsavelNome;
	private String data;
	private String status;
	private String abreviacaoequipeacolhimento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getRanking() {
		return ranking;
	}
	public void setRanking(double ranking) {
		this.ranking = ranking;
	}
	public String getStatusRPSat() {
		return statusRPSat;
	}
	public void setStatusRPSat(String statusRPSat) {
		this.statusRPSat = statusRPSat;
	}
	public String getEmpregadoNome() {
		return empregadoNome;
	}
	public void setEmpregadoNome(String empregadoNome) {
		this.empregadoNome = empregadoNome;
	}
	public String getEquipeResponsavelNome() {
		return equipeResponsavelNome;
	}
	public void setEquipeResponsavelNome(String equipeResponsavelNome) {
		this.equipeResponsavelNome = equipeResponsavelNome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAbreviacaoequipeacolhimento() {
		return abreviacaoequipeacolhimento;
	}
	public void setAbreviacaoequipeacolhimento(String abreviacaoequipeacolhimento) {
		this.abreviacaoequipeacolhimento = abreviacaoequipeacolhimento;
	}
	
}
