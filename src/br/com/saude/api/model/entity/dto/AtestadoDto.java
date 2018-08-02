package br.com.saude.api.model.entity.dto;

public class AtestadoDto {
	private String nomeEmpregado;
	private String cid;
	
	private String matricula;
	private String gerencia;
	private String base;
	
	private int prazoRecebimento;
	private boolean recebidoNoPrazo;
	private String mesRecebimento;
	private String fim;
	
	private int numeroDias;
	private String inicio;
	private boolean impossibilidadeLocomocao;
	private String status;
	private boolean lancadoSap;
	private boolean atestadoFisicoRecebido;
	private boolean controleLicenca;
	private String dataSolicitacao;
	private String dataAgendamento;
	private String numeroCat;
	
	public String getNomeEmpregado() {
		return nomeEmpregado;
	}
	public void setNomeEmpregado(String nomeEmpregado) {
		this.nomeEmpregado = nomeEmpregado;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getNumeroDias() {
		return numeroDias;
	}
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public boolean isImpossibilidadeLocomocao() {
		return impossibilidadeLocomocao;
	}
	public void setImpossibilidadeLocomocao(boolean impossibilidadeLocomocao) {
		this.impossibilidadeLocomocao = impossibilidadeLocomocao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isLancadoSap() {
		return lancadoSap;
	}
	public void setLancadoSap(boolean lancadoSap) {
		this.lancadoSap = lancadoSap;
	}
	public boolean isAtestadoFisicoRecebido() {
		return atestadoFisicoRecebido;
	}
	public void setAtestadoFisicoRecebido(boolean atestadoFisicoRecebido) {
		this.atestadoFisicoRecebido = atestadoFisicoRecebido;
	}
	public boolean isControleLicenca() {
		return controleLicenca;
	}
	public void setControleLicenca(boolean controleLicenca) {
		this.controleLicenca = controleLicenca;
	}
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getNumeroCat() {
		return numeroCat;
	}
	public void setNumeroCat(String numeroCat) {
		this.numeroCat = numeroCat;
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
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public int getPrazoRecebimento() {
		return prazoRecebimento;
	}
	public void setPrazoRecebimento(int prazoRecebimento) {
		this.prazoRecebimento = prazoRecebimento;
	}
	public boolean isRecebidoNoPrazo() {
		return recebidoNoPrazo;
	}
	public void setRecebidoNoPrazo(boolean recebidoNoPrazo) {
		this.recebidoNoPrazo = recebidoNoPrazo;
	}
	public String getMesRecebimento() {
		return mesRecebimento;
	}
	public void setMesRecebimento(String mesRecebimento) {
		this.mesRecebimento = mesRecebimento;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	
}
