package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cat {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Empregado do CAT.")
	private Empregado empregado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Empresa do CAT.")
	private Empresa empresa;

	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Gerência do CAT.")
	private Gerencia gerencia;
	
	@Size(max = 128, message="Tamanho máximo para Gerente de Contrato do CAT: 128")
	private String gerenteContrato;
	
	@Size(max = 16, message="Tamanho máximo para Telefone do Gerente do Contrato do CAT: 16")
	private String telefoneGerente;
	
	@Size(max = 128, message="Tamanho máximo para Fiscal de Contrato do CAT: 128")
	private String fiscalContrato;
	
	@Size(max = 16, message="Tamanho máximo para Telefone do Fiscal do Contrato do CAT: 16")
	private String telefoneFiscal;
	
	@NotNull(message="É necessário informar a Data de Ocorrência do CAT.")
	private Date dataOcorrencia;
	
	@Size(max = 512, message="Tamanho máximo para Local do CAT: 512")
	@NotNull(message="É necessário informar o Local do CAT.")
	private String local;
	
	@Size(max = 4096, message="Tamanho máximo para Descrição do CAT: 4096")
	@NotNull(message="É necessário informar a Descrição do CAT.")
	private String descricao;
	
	private boolean empregadoServicoCompanhia;
	
	private boolean ocorrenciaAmbienteTrabalho;
	
	private boolean ocorrenciaTrajeto;
	
	@Size(max = 256, message="Tamanho máximo para Responsável pela Informação do CAT: 256")
	private String responsavelInformacao;
	
	private Date dataInformacao;
	
	@Size(max = 4096, message="Tamanho máximo para Caracterização do CAT: 4096")
	@NotNull(message="É necessário informar a Caracterização do CAT.")
	private String caracterizacao;
	
	private boolean lesaoCorporal;
	
	@Size(max = 32, message="Tamanho máximo para Nexo Causal do CAT: 32")
	private String nexoCausal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Profissional profissionalCaracterizacao;
	
	private Date dataCaracterizacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Classificacao do Afastamento do CAT.")
	private ClassificacaoAfastamento classificacao;
	
	private int tempoPrevisto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Diagnostico cid;
	
	private boolean ferimentoGrave;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Profissional profissionalClassificacao;
	
	private Date dataClassificacao;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public String getGerenteContrato() {
		return gerenteContrato;
	}

	public void setGerenteContrato(String gerenteContrato) {
		this.gerenteContrato = gerenteContrato;
	}

	public String getTelefoneGerente() {
		return telefoneGerente;
	}

	public void setTelefoneGerente(String telefoneGerente) {
		this.telefoneGerente = telefoneGerente;
	}

	public String getFiscalContrato() {
		return fiscalContrato;
	}

	public void setFiscalContrato(String fiscalContrato) {
		this.fiscalContrato = fiscalContrato;
	}

	public String getTelefoneFiscal() {
		return telefoneFiscal;
	}

	public void setTelefoneFiscal(String telefoneFiscal) {
		this.telefoneFiscal = telefoneFiscal;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isEmpregadoServicoCompanhia() {
		return empregadoServicoCompanhia;
	}

	public void setEmpregadoServicoCompanhia(boolean empregadoServicoCompanhia) {
		this.empregadoServicoCompanhia = empregadoServicoCompanhia;
	}

	public boolean isOcorrenciaAmbienteTrabalho() {
		return ocorrenciaAmbienteTrabalho;
	}

	public void setOcorrenciaAmbienteTrabalho(boolean ocorrenciaAmbienteTrabalho) {
		this.ocorrenciaAmbienteTrabalho = ocorrenciaAmbienteTrabalho;
	}

	public boolean isOcorrenciaTrajeto() {
		return ocorrenciaTrajeto;
	}

	public void setOcorrenciaTrajeto(boolean ocorrenciaTrajeto) {
		this.ocorrenciaTrajeto = ocorrenciaTrajeto;
	}

	public String getResponsavelInformacao() {
		return responsavelInformacao;
	}

	public void setResponsavelInformacao(String responsavelInformacao) {
		this.responsavelInformacao = responsavelInformacao;
	}

	public Date getDataInformacao() {
		return dataInformacao;
	}

	public void setDataInformacao(Date dataInformacao) {
		this.dataInformacao = dataInformacao;
	}

	public String getCaracterizacao() {
		return caracterizacao;
	}

	public void setCaracterizacao(String caracterizacao) {
		this.caracterizacao = caracterizacao;
	}

	public boolean isLesaoCorporal() {
		return lesaoCorporal;
	}

	public void setLesaoCorporal(boolean lesaoCorporal) {
		this.lesaoCorporal = lesaoCorporal;
	}

	public String getNexoCausal() {
		return nexoCausal;
	}

	public void setNexoCausal(String nexoCausal) {
		this.nexoCausal = nexoCausal;
	}

	public Profissional getProfissionalCaracterizacao() {
		return profissionalCaracterizacao;
	}

	public void setProfissionalCaracterizacao(Profissional profissionalCaracterizacao) {
		this.profissionalCaracterizacao = profissionalCaracterizacao;
	}

	public Date getDataCaracterizacao() {
		return dataCaracterizacao;
	}

	public void setDataCaracterizacao(Date dataCaracterizacao) {
		this.dataCaracterizacao = dataCaracterizacao;
	}

	public ClassificacaoAfastamento getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoAfastamento classificacao) {
		this.classificacao = classificacao;
	}

	public int getTempoPrevisto() {
		return tempoPrevisto;
	}

	public void setTempoPrevisto(int tempoPrevisto) {
		this.tempoPrevisto = tempoPrevisto;
	}

	public Diagnostico getCid() {
		return cid;
	}

	public void setCid(Diagnostico cid) {
		this.cid = cid;
	}

	public boolean isFerimentoGrave() {
		return ferimentoGrave;
	}

	public void setFerimentoGrave(boolean ferimentoGrave) {
		this.ferimentoGrave = ferimentoGrave;
	}

	public Profissional getProfissionalClassificacao() {
		return profissionalClassificacao;
	}

	public void setProfissionalClassificacao(Profissional profissionalClassificacao) {
		this.profissionalClassificacao = profissionalClassificacao;
	}

	public Date getDataClassificacao() {
		return dataClassificacao;
	}

	public void setDataClassificacao(Date dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
