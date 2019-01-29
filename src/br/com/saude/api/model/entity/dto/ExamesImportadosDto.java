package br.com.saude.api.model.entity.dto;

public class ExamesImportadosDto {
	private String matricula;
	private String chave;
	private String nome;
	private String gerencia;
	private String base;
	private boolean resultadoAuditado;
	private String titulo;
	private String statusPreclinico;
	private String datasPreclinico;
	private String examesPendentes;
	private String examesImportados;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public boolean isResultadoAuditado() {
		return resultadoAuditado;
	}
	public void setResultadoAuditado(boolean resultadoAuditado) {
		this.resultadoAuditado = resultadoAuditado;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getStatusPreclinico() {
		return statusPreclinico;
	}
	public void setStatusPreclinico(String statusPreclinico) {
		this.statusPreclinico = statusPreclinico;
	}
	public String getDatasPreclinico() {
		return datasPreclinico;
	}
	public void setDatasPreclinico(String datasPreclinico) {
		this.datasPreclinico = datasPreclinico;
	}
	public String getExamesPendentes() {
		return examesPendentes;
	}
	public void setExamesPendentes(String examesPendentes) {
		this.examesPendentes = examesPendentes;
	}
	public String getExamesImportados() {
		return examesImportados;
	}
	public void setExamesImportados(String examesImportados) {
		this.examesImportados = examesImportados;
	}
}
