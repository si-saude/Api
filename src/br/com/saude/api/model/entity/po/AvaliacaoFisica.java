package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class AvaliacaoFisica {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	@Size(max = 128, message="Tamanho máximo para o Tipo do Atendimento da Avaliação Física: 128")
	private String tipoAtendimento;
	
	private int pass;
	private int pad;
	private double fcRepouso;
	
	@Size(max = 128, message="Tamanho máximo para o IPAQ Anterior da Avaliação Física: 128")
	private String ipaqAnterior;
	
	@OneToMany(mappedBy="avaliacaoFisica", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AvaliacaoFisicaAtividadeFisica> avaliacaoFisicaAtividadeFisicas;
	
	private boolean praticaExercicioFisico;
	private boolean interesseProgramaFisico;
	private boolean acaoIniciarExercicioFisico;
	private String observacaoEstagioContemplacao;
	private String protocoloComposicaoCorporal;
	private double imc;
	private double razaoCinturaEstatura;
	private double percentualGordura;
	private double percentualMassaMagra;
	private double massaMagra;
	private double gorduraAbsoluta;
	private double carenciaMuscular;
	private double percentualGorduraIdeal;
	private double percentualMassaMagraIdeal;
	private double pesoIdeal;
	private double pesoExcesso;
	private double percentualGorduraNegociada;
	private double percentualMassaMagraNegociada;
	private double pesoNegociado;
	private double pesoExcessoNegociado;
	private String observacoes;
	private double aptidaoCardiorespiratoria;
	private double forcaAbdominal;
	private double flexibilidade;
	private double forcaPreensaoManual;
	
	@Version
	private long version;
	
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getPad() {
		return pad;
	}
	public void setPad(int pad) {
		this.pad = pad;
	}
	public double getFcRepouso() {
		return fcRepouso;
	}
	public void setFcRepouso(double fcRepouso) {
		this.fcRepouso = fcRepouso;
	}
	public String getIpaqAnterior() {
		return ipaqAnterior;
	}
	public void setIpaqAnterior(String ipaqAnterior) {
		this.ipaqAnterior = ipaqAnterior;
	}
	public boolean isPraticaExercicioFisico() {
		return praticaExercicioFisico;
	}
	public void setPraticaExercicioFisico(boolean praticaExercicioFisico) {
		this.praticaExercicioFisico = praticaExercicioFisico;
	}
	public boolean isInteresseProgramaFisico() {
		return interesseProgramaFisico;
	}
	public void setInteresseProgramaFisico(boolean interesseProgramaFisico) {
		this.interesseProgramaFisico = interesseProgramaFisico;
	}
	public boolean isAcaoIniciarExercicioFisico() {
		return acaoIniciarExercicioFisico;
	}
	public void setAcaoIniciarExercicioFisico(boolean acaoIniciarExercicioFisico) {
		this.acaoIniciarExercicioFisico = acaoIniciarExercicioFisico;
	}
	public String getProtocoloComposicaoCorporal() {
		return protocoloComposicaoCorporal;
	}
	public void setProtocoloComposicaoCorporal(String protocoloComposicaoCorporal) {
		this.protocoloComposicaoCorporal = protocoloComposicaoCorporal;
	}
	public double getImc() {
		return imc;
	}
	public void setImc(double imc) {
		this.imc = imc;
	}
	public double getRazaoCinturaEstatura() {
		return razaoCinturaEstatura;
	}
	public void setRazaoCinturaEstatura(double razaoCinturaEstatura) {
		this.razaoCinturaEstatura = razaoCinturaEstatura;
	}
	public double getPercentualGordura() {
		return percentualGordura;
	}
	public void setPercentualGordura(double percentualGordura) {
		this.percentualGordura = percentualGordura;
	}
	public double getPercentualMassaMagra() {
		return percentualMassaMagra;
	}
	public void setPercentualMassaMagra(double percentualMassaMagra) {
		this.percentualMassaMagra = percentualMassaMagra;
	}
	public double getMassaMagra() {
		return massaMagra;
	}
	public void setMassaMagra(double massaMagra) {
		this.massaMagra = massaMagra;
	}
	public double getGorduraAbsoluta() {
		return gorduraAbsoluta;
	}
	public void setGorduraAbsoluta(double gorduraAbsoluta) {
		this.gorduraAbsoluta = gorduraAbsoluta;
	}
	public double getCarenciaMuscular() {
		return carenciaMuscular;
	}
	public void setCarenciaMuscular(double carenciaMuscular) {
		this.carenciaMuscular = carenciaMuscular;
	}
	public double getPercentualGorduraIdeal() {
		return percentualGorduraIdeal;
	}
	public void setPercentualGorduraIdeal(double percentualGorduraIdeal) {
		this.percentualGorduraIdeal = percentualGorduraIdeal;
	}
	public double getPercentualMassaMagraIdeal() {
		return percentualMassaMagraIdeal;
	}
	public void setPercentualMassaMagraIdeal(double percentualMassaMagraIdeal) {
		this.percentualMassaMagraIdeal = percentualMassaMagraIdeal;
	}
	public double getPesoIdeal() {
		return pesoIdeal;
	}
	public void setPesoIdeal(double pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}
	public double getPesoExcesso() {
		return pesoExcesso;
	}
	public void setPesoExcesso(double pesoExcesso) {
		this.pesoExcesso = pesoExcesso;
	}
	public double getPercentualGorduraNegociada() {
		return percentualGorduraNegociada;
	}
	public void setPercentualGorduraNegociada(double percentualGorduraNegociada) {
		this.percentualGorduraNegociada = percentualGorduraNegociada;
	}
	public double getPesoNegociado() {
		return pesoNegociado;
	}
	public void setPesoNegociado(double pesoNegociado) {
		this.pesoNegociado = pesoNegociado;
	}
	public double getPesoExcessoNegociado() {
		return pesoExcessoNegociado;
	}
	public void setPesoExcessoNegociado(double pesoExcessoNegociado) {
		this.pesoExcessoNegociado = pesoExcessoNegociado;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public double getAptidaoCardiorespiratoria() {
		return aptidaoCardiorespiratoria;
	}
	public void setAptidaoCardiorespiratoria(double aptidaoCardiorespiratoria) {
		this.aptidaoCardiorespiratoria = aptidaoCardiorespiratoria;
	}
	public double getForcaAbdominal() {
		return forcaAbdominal;
	}
	public void setForcaAbdominal(double forcaAbdominal) {
		this.forcaAbdominal = forcaAbdominal;
	}
	public double getFlexibilidade() {
		return flexibilidade;
	}
	public void setFlexibilidade(double flexibilidade) {
		this.flexibilidade = flexibilidade;
	}
	public double getForcaPreensaoManual() {
		return forcaPreensaoManual;
	}
	public void setForcaPreensaoManual(double forcaPreensaoManual) {
		this.forcaPreensaoManual = forcaPreensaoManual;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public List<AvaliacaoFisicaAtividadeFisica> getAvaliacaoFisicaAtividadeFisicas() {
		return avaliacaoFisicaAtividadeFisicas;
	}
	public void setAvaliacaoFisicaAtividadeFisicas(List<AvaliacaoFisicaAtividadeFisica> avaliacaoFisicaAtividadeFisicas) {
		this.avaliacaoFisicaAtividadeFisicas = avaliacaoFisicaAtividadeFisicas;
	}
	public String getObservacaoEstagioContemplacao() {
		return observacaoEstagioContemplacao;
	}
	public void setObservacaoEstagioContemplacao(String observacaoEstagioContemplacao) {
		this.observacaoEstagioContemplacao = observacaoEstagioContemplacao;
	}
	public double getPercentualMassaMagraNegociada() {
		return percentualMassaMagraNegociada;
	}
	public void setPercentualMassaMagraNegociada(double percentualMassaMagraNegociada) {
		this.percentualMassaMagraNegociada = percentualMassaMagraNegociada;
	}
}
