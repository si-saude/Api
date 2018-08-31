package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Atestado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int numeroDias;
	
	@Size(max = 16, message="Tamanho máximo para CID do Atestado: 16")
	private String cid;
	
	@Transient
	private Map<Integer,Integer> anexo;
	
	@Transient
	private String anexoBase64;
	
	@Transient
	private Map<Integer,Integer> anexoRelatorioMedico;
	
	@Transient
	private String anexoRelatorioMedicoBase64;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Tarefa tarefa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cat cat;
	
	private boolean impossibilidadeLocomocao;
	
	@Size(max = 64, message="Tamanho máximo para Status do Atestado: 64")
	@NotNull(message="É necessário informar o Status do Atestado.")
	private String status;
	
	private boolean lancadoSap;
	
	private boolean atestadoFisicoRecebido;
	
	private boolean controleLicenca;
	
	private Date dataAgendamento;
	
	@NotNull(message="É necessário informar a Data da Solicitação.")
	private Date dataSolicitacao;
	
	@Size(max = 64, message="Tamanho máximo para Tipo Beneficio do Atestado: 64")
    private String tipoBeneficio;
    
	@Size(max = 64, message="Tamanho máximo para Causa Afastamento do Atestado: 64")
    private String causaAfastamento;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Profissional profissionalRealizouVisita;
    
    @Size(max = 2056, message="Tamanho máximo para Ultimo Contato do Atestado: 2056")
    private String ultimoContato;
    
    @Size(max = 2056, message="Tamanho máximo para Proximo Contato do Atestado: 2056")
    private String proximoContato;
    
    @Size(max = 64, message="Tamanho máximo para Situação Empregado do Atestado: 64")
    private String situacaoEmpregado;
    
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private HomologacaoAtestado homologacaoAtestado;
	
	private Date inicio;
	
	@NotNull(message="É necessário informar o Contado do Médico da Solicitação.")
	@Size(max = 64, message="Tamanho máximo para Contado do Médico do Atestado: 64")
	private String contatoMedico;
	
	@NotNull(message="É necessário informar a Clínica da Solicitação.")
	@Size(max = 64, message="Tamanho máximo para Clínica do Atestado: 64")
	private String clinica;
	
	@NotNull(message="É necessário informar o Local do Atendimento da Solicitação.")
	@Size(max = 64, message="Tamanho máximo para Local do Atendimento do Atestado: 64")
	private String localAtendimento;
	
	@NotNull(message="É necessário informar o Telefone Externo da Solicitação.")
	@Size(max = 16, message="Tamanho máximo para Telefone Externo do Atestado: 16")
	private String telefoneExterno; 
	
	@Size(max = 64, message="Tamanho máximo para Email Externo do Atestado: 64")
	private String emailExterno; 
	
	private boolean aposentadoInss;
	
	private boolean presencial;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Regime regime;
	
	private Date dataInicioEscalaTrabalho;
	
	private Date dataFimEscalaTrabalho;
	
	private Date dataInicioFerias;
	
	private Date dataFimFerias;
	
	private boolean possuiFeriasAgendadas;
	
	private boolean ciente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message = "É necessário informar o Empregado da Solicitação.")
	private Empregado empregado;
	
	private int limiteAuditar, limiteHomologar, limiteLancar;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Map<Integer, Integer> getAnexo() {
		return anexo;
	}

	public void setAnexo(Map<Integer, Integer> anexo) {
		this.anexo = anexo;
	}

	public String getAnexoBase64() {
		return anexoBase64;
	}

	public void setAnexoBase64(String anexoBase64) {
		this.anexoBase64 = anexoBase64;
	}

	public Map<Integer, Integer> getAnexoRelatorioMedico() {
		return anexoRelatorioMedico;
	}

	public void setAnexoRelatorioMedico(Map<Integer, Integer> anexoRelatorioMedico) {
		this.anexoRelatorioMedico = anexoRelatorioMedico;
	}

	public String getAnexoRelatorioMedicoBase64() {
		return anexoRelatorioMedicoBase64;
	}

	public void setAnexoRelatorioMedicoBase64(String anexoRelatorioMedicoBase64) {
		this.anexoRelatorioMedicoBase64 = anexoRelatorioMedicoBase64;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
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

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public String getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(String tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	public String getCausaAfastamento() {
		return causaAfastamento;
	}

	public void setCausaAfastamento(String causaAfastamento) {
		this.causaAfastamento = causaAfastamento;
	}

	public Profissional getProfissionalRealizouVisita() {
		return profissionalRealizouVisita;
	}

	public void setProfissionalRealizouVisita(Profissional profissionalRealizouVisita) {
		this.profissionalRealizouVisita = profissionalRealizouVisita;
	}

	public String getUltimoContato() {
		return ultimoContato;
	}

	public void setUltimoContato(String ultimoContato) {
		this.ultimoContato = ultimoContato;
	}

	public String getProximoContato() {
		return proximoContato;
	}

	public void setProximoContato(String proximoContato) {
		this.proximoContato = proximoContato;
	}

	public String getSituacaoEmpregado() {
		return situacaoEmpregado;
	}

	public void setSituacaoEmpregado(String situacaoEmpregado) {
		this.situacaoEmpregado = situacaoEmpregado;
	}
	
	public HomologacaoAtestado getHomologacaoAtestado() {
		return homologacaoAtestado;
	}

	public void setHomologacaoAtestado(HomologacaoAtestado homologacaoAtestado) {
		this.homologacaoAtestado = homologacaoAtestado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getContatoMedico() {
		return contatoMedico;
	}

	public void setContatoMedico(String contatoMedico) {
		this.contatoMedico = contatoMedico;
	}

	public String getClinica() {
		return clinica;
	}

	public void setClinica(String clinica) {
		this.clinica = clinica;
	}

	public String getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public String getTelefoneExterno() {
		return telefoneExterno;
	}

	public void setTelefoneExterno(String telefoneExterno) {
		this.telefoneExterno = telefoneExterno;
	}

	public String getEmailExterno() {
		return emailExterno;
	}

	public void setEmailExterno(String emailExterno) {
		this.emailExterno = emailExterno;
	}

	public boolean isAposentadoInss() {
		return aposentadoInss;
	}

	public void setAposentadoInss(boolean aposentadoInss) {
		this.aposentadoInss = aposentadoInss;
	}

	public boolean isPresencial() {
		return presencial;
	}

	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	public Date getDataInicioEscalaTrabalho() {
		return dataInicioEscalaTrabalho;
	}

	public void setDataInicioEscalaTrabalho(Date dataInicioEscalaTrabalho) {
		this.dataInicioEscalaTrabalho = dataInicioEscalaTrabalho;
	}

	public Date getDataFimEscalaTrabalho() {
		return dataFimEscalaTrabalho;
	}

	public void setDataFimEscalaTrabalho(Date dataFimEscalaTrabalho) {
		this.dataFimEscalaTrabalho = dataFimEscalaTrabalho;
	}

	public boolean isPossuiFeriasAgendadas() {
		return possuiFeriasAgendadas;
	}

	public void setPossuiFeriasAgendadas(boolean possuiFeriasAgendadas) {
		this.possuiFeriasAgendadas = possuiFeriasAgendadas;
	}

	public boolean isCiente() {
		return ciente;
	}

	public void setCiente(boolean ciente) {
		this.ciente = ciente;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public int getLimiteAuditar() {
		return limiteAuditar;
	}

	public void setLimiteAuditar(int limiteAuditar) {
		this.limiteAuditar = limiteAuditar;
	}

	public int getLimiteHomologar() {
		return limiteHomologar;
	}

	public void setLimiteHomologar(int limiteHomologar) {
		this.limiteHomologar = limiteHomologar;
	}

	public int getLimiteLancar() {
		return limiteLancar;
	}

	public void setLimiteLancar(int limiteLancar) {
		this.limiteLancar = limiteLancar;
	}

	public Date getDataInicioFerias() {
		return dataInicioFerias;
	}

	public void setDataInicioFerias(Date dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias;
	}

	public Date getDataFimFerias() {
		return dataFimFerias;
	}

	public void setDataFimFerias(Date dataFimFerias) {
		this.dataFimFerias = dataFimFerias;
	}
	
}
