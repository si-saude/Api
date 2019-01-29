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

import org.hibernate.annotations.Formula;

@Entity
public class AvaliacaoFisica {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	private int pass;
	private int pad;
	private double fcRepouso;
	
	@Formula("(select get_ipaq_atendimento(id))")
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
	private double aptidaoCardiorrespiratoriaValor;
	@Size(max = 32, message="Tamanho máximo para o Classificação da Aptidão Cardiorespiratória da Avaliação Física: 32")
	private String aptidaoCardiorrespiratoriaClassificacao;
	@Size(max = 2048, message="Tamanho máximo para o Observação da Aptidão Cardiorespiratória da Avaliação Física: 2048")
	private String aptidaoCardiorrespiratoriaObservacao;
	private double forcaAbdominalValor;
	@Size(max = 32, message="Tamanho máximo para o Classificação da Força Abdominal da Avaliação Física: 32")
	private String forcaAbdominalClassificacao;
	@Size(max = 2048, message="Tamanho máximo para o Observação da Força Abdominal da Avaliação Física: 2048")
	private String forcaAbdominalObservacao;
	private double flexibilidadeValor;
	@Size(max = 32, message="Tamanho máximo para o Classificação da Flexibilidade da Avaliação Física: 32")
	private String flexibilidadeClassificacao;
	@Size(max = 2048, message="Tamanho máximo para o Observação da Flexibilidade da Avaliação Física: 2048")
	private String flexibilidadeObservacao;
	private double forcaPreensaoManualValor;
	@Size(max = 32, message="Tamanho máximo para o Classificação da Força de Preensão Manual da Avaliação Física: 32")
	private String forcaPreensaoManualClassificacao;
	@Size(max = 2048, message="Tamanho máximo para o Observação da Força de Preensão Manual da Avaliação Física: 2048")
	private String forcaPreensaoManualObservacao;
	private double dobraTricipital;
	private double dobraSubscapular;
	private double dobraToracica;
	private double dobraAuxiliarMedia;
	private double dobraSupraIliaca;
	private double dobraAbdominal;
	private double dobraCoxaMedial;
	private double pressaoArterialSistolica;
	private double pressaoArterialDiastolica;
	private double frequenciaCardiaca;
	
	@Version
	private long version;
	
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
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
	public double getAptidaoCardiorrespiratoriaValor() {
		return aptidaoCardiorrespiratoriaValor;
	}
	public void setAptidaoCardiorrespiratoriaValor(double aptidaoCardiorespiratoriaValor) {
		this.aptidaoCardiorrespiratoriaValor = aptidaoCardiorespiratoriaValor;
	}
	public String getAptidaoCardiorrespiratoriaClassificacao() {
		return aptidaoCardiorrespiratoriaClassificacao;
	}
	public void setAptidaoCardiorrespiratoriaClassificacao(String aptidaoCardiorespiratoriaClassificacao) {
		this.aptidaoCardiorrespiratoriaClassificacao = aptidaoCardiorespiratoriaClassificacao;
	}
	public String getAptidaoCardiorrespiratoriaObservacao() {
		return aptidaoCardiorrespiratoriaObservacao;
	}
	public void setAptidaoCardiorrespiratoriaObservacao(String aptidaoCardiorespiratoriaObservacao) {
		this.aptidaoCardiorrespiratoriaObservacao = aptidaoCardiorespiratoriaObservacao;
	}
	public double getForcaAbdominalValor() {
		return forcaAbdominalValor;
	}
	public void setForcaAbdominalValor(double forcaAbdominalValor) {
		this.forcaAbdominalValor = forcaAbdominalValor;
	}
	public String getForcaAbdominalClassificacao() {
		return forcaAbdominalClassificacao;
	}
	public void setForcaAbdominalClassificacao(String forcaAbdominalClassificacao) {
		this.forcaAbdominalClassificacao = forcaAbdominalClassificacao;
	}
	public String getForcaAbdominalObservacao() {
		return forcaAbdominalObservacao;
	}
	public void setForcaAbdominalObservacao(String forcaAbdominalObservacao) {
		this.forcaAbdominalObservacao = forcaAbdominalObservacao;
	}
	public double getFlexibilidadeValor() {
		return flexibilidadeValor;
	}
	public void setFlexibilidadeValor(double flexibilidadeValor) {
		this.flexibilidadeValor = flexibilidadeValor;
	}
	public String getFlexibilidadeClassificacao() {
		return flexibilidadeClassificacao;
	}
	public void setFlexibilidadeClassificacao(String flexibilidadeClassificacao) {
		this.flexibilidadeClassificacao = flexibilidadeClassificacao;
	}
	public String getFlexibilidadeObservacao() {
		return flexibilidadeObservacao;
	}
	public void setFlexibilidadeObservacao(String flexibilidadeObservacao) {
		this.flexibilidadeObservacao = flexibilidadeObservacao;
	}
	public double getForcaPreensaoManualValor() {
		return forcaPreensaoManualValor;
	}
	public void setForcaPreensaoManualValor(double forcaPreensaoManualValor) {
		this.forcaPreensaoManualValor = forcaPreensaoManualValor;
	}
	public String getForcaPreensaoManualClassificacao() {
		return forcaPreensaoManualClassificacao;
	}
	public void setForcaPreensaoManualClassificacao(String forcaPreensaoManualClassificacao) {
		this.forcaPreensaoManualClassificacao = forcaPreensaoManualClassificacao;
	}
	public String getForcaPreensaoManualObservacao() {
		return forcaPreensaoManualObservacao;
	}
	public void setForcaPreensaoManualObservacao(String forcaPreensaoManualObservacao) {
		this.forcaPreensaoManualObservacao = forcaPreensaoManualObservacao;
	}
	public double getDobraTricipital() {
		return dobraTricipital;
	}
	public void setDobraTricipital(double dobraTricipital) {
		this.dobraTricipital = dobraTricipital;
	}
	public double getDobraSubscapular() {
		return dobraSubscapular;
	}
	public void setDobraSubscapular(double dobraSubscapular) {
		this.dobraSubscapular = dobraSubscapular;
	}
	public double getDobraToracica() {
		return dobraToracica;
	}
	public void setDobraToracica(double dobraToracica) {
		this.dobraToracica = dobraToracica;
	}
	public double getDobraAuxiliarMedia() {
		return dobraAuxiliarMedia;
	}
	public void setDobraAuxiliarMedia(double dobraAuxiliarMedia) {
		this.dobraAuxiliarMedia = dobraAuxiliarMedia;
	}
	public double getDobraSupraIliaca() {
		return dobraSupraIliaca;
	}
	public void setDobraSupraIliaca(double dobraSupraIliaca) {
		this.dobraSupraIliaca = dobraSupraIliaca;
	}
	public double getDobraAbdominal() {
		return dobraAbdominal;
	}
	public void setDobraAbdominal(double dobraAbdominal) {
		this.dobraAbdominal = dobraAbdominal;
	}
	public double getDobraCoxaMedial() {
		return dobraCoxaMedial;
	}
	public void setDobraCoxaMedial(double dobraCoxaMedial) {
		this.dobraCoxaMedial = dobraCoxaMedial;
	}
	public double getPressaoArterialSistolica() {
		return pressaoArterialSistolica;
	}
	public void setPressaoArterialSistolica(double pressaoArterialSistolica) {
		this.pressaoArterialSistolica = pressaoArterialSistolica;
	}
	public double getPressaoArterialDiastolica() {
		return pressaoArterialDiastolica;
	}
	public void setPressaoArterialDiastolica(double pressaoArterialDiastolica) {
		this.pressaoArterialDiastolica = pressaoArterialDiastolica;
	}
	public double getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}
	public void setFrequenciaCardiaca(double frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}
}
