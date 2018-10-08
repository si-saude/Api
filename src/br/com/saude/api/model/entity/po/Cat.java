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
import javax.persistence.Transient;
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
	private ClassificacaoAfastamento classificacao;
	
	private int tempoPrevisto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Diagnostico cid;
	
	private boolean ferimentoGrave;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Profissional profissionalClassificacao;
	
	private Date dataClassificacao;
	
	private double remuneracao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private AgenteCausador agenteCausador;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ParteCorpoAtingida parteCorpoAtingida;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private NaturezaLesao naturezaLesao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cidade municipio;
	
	private Date dataObito;

	@Size(max = 64, message="Tamanho máximo para RTA do CAT: 64")
	private String rta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Instalacao instalacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cnae cnae;
	
	private int grauRiscoEmpresa;
	
	@Size(max = 8, message="Tamanho máximo para Número SISIN do CAT: 8")
	private String numeroSisin;
	
	private int classificacaoSisin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ClassificacaoGravidade classificacaoGravidade;
	
	private Date dataAvaliacaoMedica;
	
	private boolean registroSd2000;
	
	private boolean catSd2000;
	
	private Date dataEmissao;
	
	private boolean pendenciaCorrecao;
	
	@Size(max = 1024, message="Tamanho máximo para Justificativa do Atraso da Emissão da CAT do CAT: 1024")
	private String justificativaAtrasoEmissaoCat;
	
	@Size(max = 64, message="Tamanho máximo para Número da Carta da Multa do CAT: 64")
	private String numeroCartaMulta;
	
	@Size(max = 16, message="Tamanho máximo para Tipo do Acidente do CAT: 16")
	private String tipoAcidente;
	
	@Size(max = 16, message="Tamanho máximo para Tipo da CAT do CAT: 16")
	private String tipoCat;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Diagnostico diagnosticoProvavel;
	
	private boolean comunicavelSus;
	
	@Size(max = 32, message="Tamanho máximo para Número do CAT do CAT: 32")
	private String numeroCat;
	
	@Size(max = 16, message="Tamanho máximo para Código da Carta do Sindicato do CAT: 16")
	private String codigoCartaSindicato;
	
	private int classificacaoAnomalia;
	
	private Date dataComunicacaoSindicato;
	
	@Size(max = 1024, message="Tamanho máximo para Justificativa no Atraso da Emissão da Carta do CAT: 1024")
	private String justificativaAtrasoEmissaoCarta;
	
	private boolean catInss;
	
	private boolean convocado;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="exame_cat", 
				joinColumns = {@JoinColumn(name="cat_id")}, 
				inverseJoinColumns = {@JoinColumn(name="exame_id")})
	private List<Exame> examesConvocacao;
	
	private boolean ausenciaExames;

	@Size(max = 4096, message="Tamanho máximo para Recomendações do CAT: 2048")
	private String recomendacoes;
	
	@Transient
	private Map<Integer,Integer> arquivo;
	
	@Transient
	private String arquivoBase64;
	
	private int jornadaTrabalho;
	
	@Size(max = 512, message="Tamanho máximo para Ato1 do CAT: 512")
	private String ato1;
	
	@Size(max = 512, message="Tamanho máximo para Ato2 do CAT: 512")
	private String ato2;
	
	@Size(max = 512, message="Tamanho máximo para Ato3 do CAT: 512")
	private String ato3;
	
	@Size(max = 512, message="Tamanho máximo para Ato4 do CAT: 512")
	private String ato4;
	
	@Size(max = 512, message="Tamanho máximo para Ato5 do CAT: 512")
	private String ato5;
	
	@Size(max = 2048, message="Tamanho máximo para Justificativa do CAT: 2048")
	private String justificativa;
	
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
	
	public double getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(double remuneracao) {
		this.remuneracao = remuneracao;
	}

	public AgenteCausador getAgenteCausador() {
		return agenteCausador;
	}

	public void setAgenteCausador(AgenteCausador agenteCausador) {
		this.agenteCausador = agenteCausador;
	}

	public ParteCorpoAtingida getParteCorpoAtingida() {
		return parteCorpoAtingida;
	}

	public void setParteCorpoAtingida(ParteCorpoAtingida parteCorpoAtingida) {
		this.parteCorpoAtingida = parteCorpoAtingida;
	}

	public NaturezaLesao getNaturezaLesao() {
		return naturezaLesao;
	}

	public void setNaturezaLesao(NaturezaLesao naturezaLesao) {
		this.naturezaLesao = naturezaLesao;
	}

	public Date getDataObito() {
		return dataObito;
	}

	public void setDataObito(Date dataObito) {
		this.dataObito = dataObito;
	}
	
	public Cidade getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Cidade municipio) {
		this.municipio = municipio;
	}
	
	public String getRta() {
		return rta;
	}

	public void setRta(String rta) {
		this.rta = rta;
	}

	public Instalacao getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(Instalacao instalacao) {
		this.instalacao = instalacao;
	}
	
	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public int getGrauRiscoEmpresa() {
		return grauRiscoEmpresa;
	}

	public void setGrauRiscoEmpresa(int grauRiscoEmpresa) {
		this.grauRiscoEmpresa = grauRiscoEmpresa;
	}

	public String getNumeroSisin() {
		return numeroSisin;
	}

	public void setNumeroSisin(String numeroSisin) {
		this.numeroSisin = numeroSisin;
	}

	public int getClassificacaoSisin() {
		return classificacaoSisin;
	}

	public void setClassificacaoSisin(int classificacaoSisin) {
		this.classificacaoSisin = classificacaoSisin;
	}

	public ClassificacaoGravidade getClassificacaoGravidade() {
		return classificacaoGravidade;
	}

	public void setClassificacaoGravidade(ClassificacaoGravidade classificacaoGravidade) {
		this.classificacaoGravidade = classificacaoGravidade;
	}

	public Date getDataAvaliacaoMedica() {
		return dataAvaliacaoMedica;
	}

	public void setDataAvaliacaoMedica(Date dataAvaliacaoMedica) {
		this.dataAvaliacaoMedica = dataAvaliacaoMedica;
	}

	public boolean isRegistroSd2000() {
		return registroSd2000;
	}

	public void setRegistroSd2000(boolean registroSd2000) {
		this.registroSd2000 = registroSd2000;
	}

	public boolean isCatSd2000() {
		return catSd2000;
	}

	public void setCatSd2000(boolean catSd2000) {
		this.catSd2000 = catSd2000;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public boolean isPendenciaCorrecao() {
		return pendenciaCorrecao;
	}

	public void setPendenciaCorrecao(boolean pendenciaCorrecao) {
		this.pendenciaCorrecao = pendenciaCorrecao;
	}

	public String getJustificativaAtrasoEmissaoCat() {
		return justificativaAtrasoEmissaoCat;
	}

	public void setJustificativaAtrasoEmissaoCat(String justificativaAtrasoEmissaoCat) {
		this.justificativaAtrasoEmissaoCat = justificativaAtrasoEmissaoCat;
	}

	public String getNumeroCartaMulta() {
		return numeroCartaMulta;
	}

	public void setNumeroCartaMulta(String numeroCartaMulta) {
		this.numeroCartaMulta = numeroCartaMulta;
	}

	public String getTipoAcidente() {
		return tipoAcidente;
	}

	public void setTipoAcidente(String tipoAcidente) {
		this.tipoAcidente = tipoAcidente;
	}

	public String getTipoCat() {
		return tipoCat;
	}

	public void setTipoCat(String tipoCat) {
		this.tipoCat = tipoCat;
	}

	public Diagnostico getDiagnosticoProvavel() {
		return diagnosticoProvavel;
	}

	public void setDiagnosticoProvavel(Diagnostico diagnosticoProvavel) {
		this.diagnosticoProvavel = diagnosticoProvavel;
	}

	public boolean isComunicavelSus() {
		return comunicavelSus;
	}

	public void setComunicavelSus(boolean comunicavelSus) {
		this.comunicavelSus = comunicavelSus;
	}

	public String getNumeroCat() {
		return numeroCat;
	}

	public void setNumeroCat(String numeroCat) {
		this.numeroCat = numeroCat;
	}

	public String getCodigoCartaSindicato() {
		return codigoCartaSindicato;
	}

	public void setCodigoCartaSindicato(String codigoCartaSindicato) {
		this.codigoCartaSindicato = codigoCartaSindicato;
	}

	public int getClassificacaoAnomalia() {
		return classificacaoAnomalia;
	}

	public void setClassificacaoAnomalia(int classificacaoAnomalia) {
		this.classificacaoAnomalia = classificacaoAnomalia;
	}

	public Date getDataComunicacaoSindicato() {
		return dataComunicacaoSindicato;
	}

	public void setDataComunicacaoSindicato(Date dataComunicacaoSindicato) {
		this.dataComunicacaoSindicato = dataComunicacaoSindicato;
	}

	public String getJustificativaAtrasoEmissaoCarta() {
		return justificativaAtrasoEmissaoCarta;
	}

	public void setJustificativaAtrasoEmissaoCarta(String justificativaAtrasoEmissaoCarta) {
		this.justificativaAtrasoEmissaoCarta = justificativaAtrasoEmissaoCarta;
	}
	
	public boolean isCatInss() {
		return catInss;
	}

	public void setCatInss(boolean catInss) {
		this.catInss = catInss;
	}

	public boolean isConvocado() {
		return convocado;
	}

	public void setConvocado(boolean convocado) {
		this.convocado = convocado;
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
	
	public String getRecomendacoes() {
		return recomendacoes;
	}

	public void setRecomendacoes(String recomendacoes) {
		this.recomendacoes = recomendacoes;
	}
	
	public Map<Integer, Integer> getArquivo() {
		return arquivo;
	}

	public void setArquivo(Map<Integer, Integer> arquivo) {
		this.arquivo = arquivo;
	}

	public String getArquivoBase64() {
		return arquivoBase64;
	}

	public void setArquivoBase64(String arquivoBase64) {
		this.arquivoBase64 = arquivoBase64;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public int getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(int jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public String getAto1() {
		return ato1;
	}

	public void setAto1(String ato1) {
		this.ato1 = ato1;
	}

	public String getAto2() {
		return ato2;
	}

	public void setAto2(String ato2) {
		this.ato2 = ato2;
	}

	public String getAto3() {
		return ato3;
	}

	public void setAto3(String ato3) {
		this.ato3 = ato3;
	}

	public String getAto4() {
		return ato4;
	}

	public void setAto4(String ato4) {
		this.ato4 = ato4;
	}

	public String getAto5() {
		return ato5;
	}

	public void setAto5(String ato5) {
		this.ato5 = ato5;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
}
