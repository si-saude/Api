package br.com.saude.api.model.entity.dto;

public class RiscoPotencialDto {
	private int id;
	private double ranking;
	private String statusRPSat;
	private String empregadoNome;
	private String equipeResponsavelNome;
	private String data;
	private String status;
	private String abreviacaoEquipeAcolhimento;
	private boolean pendenciaEncerramento;
	private boolean pendenciaValidacao;
	private boolean pendenciaReavaliacao;
	
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
	public String getAbreviacaoEquipeAcolhimento() {
		return abreviacaoEquipeAcolhimento;
	}
	public void setAbreviacaoEquipeAcolhimento(String abreviacaoEquipeAcolhimento) {
		this.abreviacaoEquipeAcolhimento = abreviacaoEquipeAcolhimento;
	}
	public boolean isPendenciaEncerramento() {
		return pendenciaEncerramento;
	}
	public void setPendenciaEncerramento(boolean pendenciaEncerramento) {
		this.pendenciaEncerramento = pendenciaEncerramento;
	}
	public boolean isPendenciaValidacao() {
		return pendenciaValidacao;
	}
	public void setPendenciaValidacao(boolean pendenciaValidacao) {
		this.pendenciaValidacao = pendenciaValidacao;
	}
	public boolean isPendenciaReavaliacao() {
		return pendenciaReavaliacao;
	}
	public void setPendenciaReavaliacao(boolean pendenciaReavaliacao) {
		this.pendenciaReavaliacao = pendenciaReavaliacao;
	}
	
}
