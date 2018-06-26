package br.com.saude.api.model.entity.dto;

public class AgendaPeriodicoDto {

	private int empregadoId;
	private String empregadoNome;
	private String status;
	private String data;
	private String nomeServico;
	private String pendencias;
	
	public int getEmpregadoId() {
		return empregadoId;
	}
	public void setEmpregadoId(int empregadoId) {
		this.empregadoId = empregadoId;
	}
	public String getEmpregadoNome() {
		return empregadoNome;
	}
	public void setEmpregadoNome(String empregadoNome) {
		this.empregadoNome = empregadoNome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public String getPendencias() {
		return pendencias;
	}
	public void setPendencias(String pendencias) {
		this.pendencias = pendencias;
	}
	
}
