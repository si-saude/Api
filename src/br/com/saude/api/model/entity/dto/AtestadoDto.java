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
	
	private String dataHomologacao;
	private String dataEntrega;
	private int prazoHomologacao;
	private boolean homologacaoNoPrazo;
	private String mesHomologacao;
	private String medicoOdonto;
	private String nomeProfissionalHomologacao;
	private String observacao;
	
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
	public String getDataHomologacao() {
		return dataHomologacao;
	}
	public void setDataHomologacao(String dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public int getPrazoHomologacao() {
		return prazoHomologacao;
	}
	public void setPrazoHomologacao(int prazoHomologacao) {
		this.prazoHomologacao = prazoHomologacao;
	}
	public boolean isHomologacaoNoPrazo() {
		return homologacaoNoPrazo;
	}
	public void setHomologacaoNoPrazo(boolean homologacaoNoPrazo) {
		this.homologacaoNoPrazo = homologacaoNoPrazo;
	}
	public String getMesHomologacao() {
		return mesHomologacao;
	}
	public void setMesHomologacao(String mesHomologacao) {
		this.mesHomologacao = mesHomologacao;
	}
	public String getMedicoOdonto() {
		return medicoOdonto;
	}
	public void setMedicoOdonto(String medicoOdonto) {
		this.medicoOdonto = medicoOdonto;
	}
	public String getNomeProfissionalHomologacao() {
		return nomeProfissionalHomologacao;
	}
	public void setNomeProfissionalHomologacao(String nomeProfissionalHomologacao) {
		this.nomeProfissionalHomologacao = nomeProfissionalHomologacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
