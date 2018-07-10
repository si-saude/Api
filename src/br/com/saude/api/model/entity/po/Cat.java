package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Column;
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
	
	@Column(unique = true)
	@NotNull(message="É necessário informar o Número do Cat.")
	@Size(max = 16, message="Tamanho máximo para Número do Cat: 16")
	private String numero;

	@NotNull(message="É necessário informar a Gerência do Cat.")
	@ManyToOne(fetch=FetchType.LAZY)	
	private Gerencia gerencia;
	
	@Size(max = 64, message="Tamanho máximo para RTA do Cat: 64")
	@NotNull(message="É necessário informar o RTA do CAT.")
	private String rta;
	
	@Size(max = 128, message="Tamanho máximo para Instalação do Cat: 128")
	private String instalacao;
	
	private boolean contratado;

	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@NotNull(message="É necessário informar o Nome do Cat.")
	@Size(max = 256, message="Tamanho máximo para Nome do Cat: 256")
	private String nome;
	
	@NotNull(message="É necessário informar a Data de Nascimento do Cat.")
	private Date dataNascimento;
	
	@NotNull(message="É necessário informar o Sexo do Cat.")
	@Size(max = 16, message="Tamanho máximo para Sexo do Cat: 16")
	private String sexo;
	
	@NotNull(message="É necessário informar o CPF do Cat.")
	@Size(max = 11, message="Tamanho máximo para CPF do Cat: 11")
	private String cpf;
	
	@Size(max = 128, message="Tamanho máximo para Cargo do Cat: 128")
	private String cargo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Fornecedor empresa;
	
	@Size(max = 32, message="Tamanho máximo para Regime do Cat: 32")
	private String regime;
	
	private boolean afastamento;
	
	@Size(max = 32, message="Tamanho máximo para Partes do Corpo do Cat: 32")
	private String partesCorpo;
	
	@NotNull(message="É necessário informar o Dia/Hora do Acidente do CAT.")
	private Date diaHoraAcidente;
	
	@Size(max = 16, message="Tamanho máximo para Número de SISIN do Cat: 16")
	private String numeroSisin;
	
	private int classificacaoSisin;
	
	private Date dataEmissaoCat;
	
	@Size(max = 32, message="Tamanho máximo para Gravidade do Cat: 32")
	private String gravidade;
	
	private Date dataAvaliacaoMedica;
	
	private boolean registroSd2000;
	
	private boolean catSd2000;

	@Size(max = 16, message="Tamanho máximo para Tipo de Acidente do Cat: 16")
	private String tipoAcidente;
	
	@Size(max = 16, message="Tamanho máximo para Tipo CAT do Cat: 16")
	private String tipoCat;

	@ManyToOne(fetch=FetchType.LAZY)	
	private Diagnostico diagnostico;
	
	@NotNull(message="É necessário informar a Parte do Corpo Atingida do Cat.")
	@ManyToOne(fetch=FetchType.LAZY)	
	private ParteCorpoAtingida parteCorpoAtingida;
	
	@NotNull(message="É necessário informar o Agente Causador do Cat.")
	@ManyToOne(fetch=FetchType.LAZY)	
	private AgenteCausador agenteCausador;
	
	@NotNull(message="É necessário informar a Natureza da Lesão do Cat.")
	@ManyToOne(fetch=FetchType.LAZY)	
	private NaturezaLesao naturezaLesao;
	
	private boolean comunicavelSus;
	
	private boolean ferimentoGraveConformeAnp;
	
	@Size(max = 16, message="Tamanho máximo para Código da Carta do Sindicato do Cat: 16")
	private String codigoCartaSindicato;
	
	private Date dataComunicacaoSindicato;
	
	private float remuneracao;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public String getRta() {
		return rta;
	}

	public void setRta(String Rta) {
		rta = Rta;
	}

	public String getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(String instalacao) {
		this.instalacao = instalacao;
	}

	public boolean isContratado() {
		return contratado;
	}

	public void setContratado(boolean contratado) {
		this.contratado = contratado;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Fornecedor getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Fornecedor empresa) {
		this.empresa = empresa;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public boolean isAfastamento() {
		return afastamento;
	}

	public void setAfastamento(boolean afastamento) {
		this.afastamento = afastamento;
	}

	public Date getDiaHoraAcidente() {
		return diaHoraAcidente;
	}

	public void setDiaHoraAcidente(Date diaHoraAcidente) {
		this.diaHoraAcidente = diaHoraAcidente;
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

	public Date getDataEmissaoCat() {
		return dataEmissaoCat;
	}

	public void setDataEmissaoCat(Date dataEmissaoCat) {
		this.dataEmissaoCat = dataEmissaoCat;
	}

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
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

	public void setCatSd2000(boolean CatSd2000) {
		this.catSd2000 = CatSd2000;
	}

	public String getTipoAcidente() {
		return tipoAcidente;
	}

	public void setTipoAcidente(String tipoAcidente) {
		this.tipoAcidente = tipoAcidente;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public boolean isComunicavelSus() {
		return comunicavelSus;
	}

	public void setComunicavelSus(boolean comunicavelSus) {
		this.comunicavelSus = comunicavelSus;
	}

	public boolean isFerimentoGraveConformeAnp() {
		return ferimentoGraveConformeAnp;
	}

	public void setFerimentoGraveConformeAnp(boolean ferimentoGraveConformeAnp) {
		this.ferimentoGraveConformeAnp = ferimentoGraveConformeAnp;
	}

	public String getCodigoCartaSindicato() {
		return codigoCartaSindicato;
	}

	public void setCodigoCartaSindicato(String codigoCartaSindicato) {
		this.codigoCartaSindicato = codigoCartaSindicato;
	}

	public Date getDataComunicacaoSindicato() {
		return dataComunicacaoSindicato;
	}

	public void setDataComunicacaoSindicato(Date dataComunicacaoSindicato) {
		this.dataComunicacaoSindicato = dataComunicacaoSindicato;
	}

	public float getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(float remuneracao) {
		this.remuneracao = remuneracao;
	}
	
	public String getPartesCorpo() {
		return partesCorpo;
	}

	public void setPartesCorpo(String partesCorpo) {
		this.partesCorpo = partesCorpo;
	}

	public String getTipoCat() {
		return tipoCat;
	}

	public void setTipoCat(String tipoCat) {
		this.tipoCat = tipoCat;
	}
	
	public ParteCorpoAtingida getParteCorpoAtingida() {
		return parteCorpoAtingida;
	}

	public void setParteCorpoAtingida(ParteCorpoAtingida parteCorpoAtingida) {
		this.parteCorpoAtingida = parteCorpoAtingida;
	}

	public AgenteCausador getAgenteCausador() {
		return agenteCausador;
	}

	public void setAgenteCausador(AgenteCausador agenteCausador) {
		this.agenteCausador = agenteCausador;
	}

	public NaturezaLesao getNaturezaLesao() {
		return naturezaLesao;
	}

	public void setNaturezaLesao(NaturezaLesao naturezaLesao) {
		this.naturezaLesao = naturezaLesao;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
