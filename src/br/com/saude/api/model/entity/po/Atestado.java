package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

@Entity
public class Atestado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int numeroDias;
	
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
		
	private boolean impossibilidadeLocomocao;
	
	@Size(max = 64, message="Tamanho máximo para Status do Atestado: 64")
	@NotNull(message="É necessário informar o Status do Atestado.")
	private String status;
	
	private boolean lancadoSap;
	
	private boolean atestadoFisicoRecebido;
	
	private boolean controleLicenca;
	
	@NotNull(message="É necessário informar a Data da Solicitação.")
	private Date dataSolicitacao;
	
	@NotNull(message="É necessário informar o Início da Solicitação.")
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MotivoRecusaAtestado motivoRecusa;
	
	private int limiteAuditar, limiteHomologar, limiteLancar;
	
	@Transient
	private Date dataLimiteAuditar;
	
	@Transient
	private Date dataLimiteAgendamento;
	
	@Transient
	private Date dataLimiteLancar;
	
	@Transient
	private Date dataLimiteHomologar;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Diagnostico cid;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Tarefa agendamento;
	
	@Formula("(select somaDiasAtestados(id))")
	private int somaDiasAtestados;

	@Formula("(select concatenacaoDatasCidsAtestado(id))")
	private String concatenacaoDatasCids;
	
	@Size(max = 2048, message="Tamanho máximo para Observação do Atestado: 2048")
	private String observacao;
	
	private boolean lancamentoSd2000;
	
	@Formula("(select addNumeroDiasInicioAtestado(id))")
	private Date fim;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="exame_atestado", 
				joinColumns = {@JoinColumn(name="atestado_id")}, 
				inverseJoinColumns = {@JoinColumn(name="exame_id")})
	private List<Exame> examesConvocacao;
	
	private boolean ausenciaExames;
	
	@OneToMany(mappedBy="atestado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<HistoricoAtestado> historicoAtestados;
	
	@Transient
	private Profissional profissional;
	
	@Transient
	private String previewStatus;
	
	private Date dataAuditoria;
	
	private boolean convocado;
	
	private Date dataHomologacao;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
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

	public MotivoRecusaAtestado getMotivoRecusa() {
		return motivoRecusa;
	}

	public void setMotivoRecusa(MotivoRecusaAtestado motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}

	public Date getDataLimiteAuditar() {
		return dataLimiteAuditar;
	}

	public void setDataLimiteAuditar(Date dataLimiteAuditar) {
		this.dataLimiteAuditar = dataLimiteAuditar;
	}

	public Date getDataLimiteAgendamento() {
		return dataLimiteAgendamento;
	}

	public void setDataLimiteAgendamento(Date dataLimiteAgendamento) {
		this.dataLimiteAgendamento = dataLimiteAgendamento;
	}

	public Date getDataLimiteLancar() {
		return dataLimiteLancar;
	}

	public void setDataLimiteLancar(Date dataLimiteLancar) {
		this.dataLimiteLancar = dataLimiteLancar;
	}

	public Diagnostico getCid() {
		return cid;
	}

	public void setCid(Diagnostico cid) {
		this.cid = cid;
	}

	public Tarefa getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Tarefa agendamento) {
		this.agendamento = agendamento;
	}

	public Date getDataLimiteHomologar() {
		return dataLimiteHomologar;
	}

	public void setDataLimiteHomologar(Date dataLimiteHomologar) {
		this.dataLimiteHomologar = dataLimiteHomologar;
	}

	public int getSomaDiasAtestados() {
		return somaDiasAtestados;
	}

	public void setSomaDiasAtestados(int somaDiasAtestados) {
		this.somaDiasAtestados = somaDiasAtestados;
	}

	public String getConcatenacaoDatasCids() {
		return concatenacaoDatasCids;
	}

	public void setConcatenacaoDatasCids(String concatenacaoDatasCids) {
		this.concatenacaoDatasCids = concatenacaoDatasCids;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isLancamentoSd2000() {
		return lancamentoSd2000;
	}

	public void setLancamentoSd2000(boolean lancamentoSd2000) {
		this.lancamentoSd2000 = lancamentoSd2000;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public List<Exame> getExamesConvocacao() {
		return examesConvocacao;
	}

	public void setExamesConvocacao(List<Exame> examesConvocacao) {
		this.examesConvocacao = examesConvocacao;
	}

	public boolean isAusenciaExames() {
		return ausenciaExames;
	}

	public void setAusenciaExames(boolean ausenciaExames) {
		this.ausenciaExames = ausenciaExames;
	}

	public List<HistoricoAtestado> getHistoricoAtestados() {
		return historicoAtestados;
	}

	public void setHistoricoAtestados(List<HistoricoAtestado> historicoAtestados) {
		this.historicoAtestados = historicoAtestados;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getPreviewStatus() {
		return previewStatus;
	}

	public void setPreviewStatus(String previewStatus) {
		this.previewStatus = previewStatus;
	}

	public Date getDataAuditoria() {
		return dataAuditoria;
	}

	public void setDataAuditoria(Date dataAuditoria) {
		this.dataAuditoria = dataAuditoria;
	}

	public boolean isConvocado() {
		return convocado;
	}

	public void setConvocado(boolean convocado) {
		this.convocado = convocado;
	}

	public Date getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}
	
}
