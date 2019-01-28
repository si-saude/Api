package br.com.saude.api.model.entity.dto;

public class ControleAtestadoDto {
	private String nomeEmpregado;
	private String matricula;
	private String gerencia;
	private String base;
	private String inicioAtestado;
	private String fimAtestado;
	private int numeroDias;
	private String dataRecebimento;
	private String dataAgendamento;
	
	private int prazoRecebimento;
	private boolean recebidoNoPrazo;
	private String mesRecebimento;
	private String dataEntrega;
	private String dataHomologacao;
	
	private int prazoHomologacao;
	private boolean homologadoNoPrazo;
	
	private String mesHomologacao;
	private String abreviacaoEquipe;
	private String tarefaProfissional;
	private boolean atestadoFisicoRecebido;
	private String observacao;
	private String statusAtestado;
	private String justificativa;
	private String codigoCid;
	
	public String getNomeEmpregado() {
		return nomeEmpregado;
	}
	public void setNomeEmpregado(String nomeEmpregado) {
		this.nomeEmpregado = nomeEmpregado;
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
	public String getInicioAtestado() {
		return inicioAtestado;
	}
	public void setInicioAtestado(String inicioAtestado) {
		this.inicioAtestado = inicioAtestado;
	}
	public String getFimAtestado() {
		return fimAtestado;
	}
	public void setFimAtestado(String fimAtestado) {
		this.fimAtestado = fimAtestado;
	}
	public int getNumeroDias() {
		return numeroDias;
	}
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}
	public String getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getDataHomologacao() {
		return dataHomologacao;
	}
	public void setDataHomologacao(String dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}
	public int getPrazoHomologacao() {
		return prazoHomologacao;
	}
	public void setPrazoHomologacao(int prazoHomologacao) {
		this.prazoHomologacao = prazoHomologacao;
	}
	public boolean isHomologadoNoPrazo() {
		return homologadoNoPrazo;
	}
	public void setHomologadoNoPrazo(boolean homologadoNoPrazo) {
		this.homologadoNoPrazo = homologadoNoPrazo;
	}
	public String getMesHomologacao() {
		return mesHomologacao;
	}
	public void setMesHomologacao(String mesHomologacao) {
		this.mesHomologacao = mesHomologacao;
	}
	public String getAbreviacaoEquipe() {
		return abreviacaoEquipe;
	}
	public void setAbreviacaoEquipe(String abreviacaoEquipe) {
		this.abreviacaoEquipe = abreviacaoEquipe;
	}
	public String getTarefaProfissional() {
		return tarefaProfissional;
	}
	public void setTarefaProfissional(String tarefaProfissional) {
		this.tarefaProfissional = tarefaProfissional;
	}
	public boolean isAtestadoFisicoRecebido() {
		return atestadoFisicoRecebido;
	}
	public void setAtestadoFisicoRecebido(boolean atestadoFisicoRecebido) {
		this.atestadoFisicoRecebido = atestadoFisicoRecebido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatusAtestado() {
		return statusAtestado;
	}
	public void setStatusAtestado(String statusAtestado) {
		this.statusAtestado = statusAtestado;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public String getCodigoCid() {
		return codigoCid;
	}
	public void setCodigoCid(String codigoCid) {
		this.codigoCid = codigoCid;
	}
}