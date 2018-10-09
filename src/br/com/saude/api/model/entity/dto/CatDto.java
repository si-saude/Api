package br.com.saude.api.model.entity.dto;

public class CatDto {
	private String orgao;
	private String rta;
	private String mes;
	private String diaSemana;
	private String instalacao;
	private String proprioContratado;
	private String empresa;
	private String cnae;
	private int grauRiscoEmpresa;
	private String nomeAcidentado;
	private String regimeTrabalho;
	private int jornadaTrabalho;
	private String acidenteComSemAfastamento;
	private String parteCorpoAtingida;
	private String horaAcidente;
	private String municipioAcidente;
	private String numeroSisin;
	private int classificacaoSisin;
	private String dataAcidente;
	private String dataEmissaoCat;
	private int diasAtraso;
	private String prazo;
	private String classificacaoGravidade;
	private String dataAvaliacaoMedica;
	private boolean registroSd2000;
	private boolean catSd2000;
	private String situacaoAvaliacaoMedica;
	private String dataLiberacao;
	private boolean pendenciaCorrecaoCat;
	private String justificativaAtrasoEmissaoCat;
	private String numeroCartaMultaEmpresa;
	private String tipoAcidente;
	private String tipoCat;
	private String diagnosticoProvavel;
	private String agenteCausador;
	private boolean comunicavelSus;
	private boolean ferimentoGraveConformeANP;
	private String numeroCat;
	private String localizacaoLesao;
	private String naturezaLesao;
	private String diasAfastamento;
	private String intervaloAfastamento;
	private String horasPerdidas;
	private double salarioHora;
	private String custoAcidente2;
	private String codigoCartaSindicato;
	private int classificacaoAnomalia;
	private String dataComunicacaoSindicato;
	private String diasAcidenteComunicacaoSindicato;
	private String justificativaAtrasoEmissaoCarta;
	private String dataNascimento;
	private int idade;
	private String sexo;
	private String grauInstrucao;
	private String estadoCivil;
	private double remuneracao;
	private String cargo;
	private String ato1;
	private String ato2;
	private String ato3;
	private String ato4;
	
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public String getRta() {
		return rta;
	}
	public void setRta(String rta) {
		this.rta = rta;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getInstalacao() {
		return instalacao;
	}
	public void setInstalacao(String instalacao) {
		this.instalacao = instalacao;
	}
	public String getProprioContratado() {
		return proprioContratado;
	}
	public void setProprioContratado(String proprioContratado) {
		this.proprioContratado = proprioContratado;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnae() {
		return cnae;
	}
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	public int getGrauRiscoEmpresa() {
		return grauRiscoEmpresa;
	}
	public void setGrauRiscoEmpresa(int grauRiscoEmpresa) {
		this.grauRiscoEmpresa = grauRiscoEmpresa;
	}
	public String getNomeAcidentado() {
		return nomeAcidentado;
	}
	public void setNomeAcidentado(String nomeAcidentado) {
		this.nomeAcidentado = nomeAcidentado;
	}
	public String getRegimeTrabalho() {
		return regimeTrabalho;
	}
	public void setRegimeTrabalho(String regimeTrabalho) {
		this.regimeTrabalho = regimeTrabalho;
	}
	public int getJornadaTrabalho() {
		return jornadaTrabalho;
	}
	public void setJornadaTrabalho(int jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}
	public String getAcidenteComSemAfastamento() {
		return acidenteComSemAfastamento;
	}
	public void setAcidenteComSemAfastamento(String acidenteComSemAfastamento) {
		this.acidenteComSemAfastamento = acidenteComSemAfastamento;
	}
	public String getParteCorpoAtingida() {
		return parteCorpoAtingida;
	}
	public void setParteCorpoAtingida(String parteCorpoAtingida) {
		this.parteCorpoAtingida = parteCorpoAtingida;
	}
	public String getHoraAcidente() {
		return horaAcidente;
	}
	public void setHoraAcidente(String horaAcidente) {
		this.horaAcidente = horaAcidente;
	}
	public String getMunicipioAcidente() {
		return municipioAcidente;
	}
	public void setMunicipioAcidente(String municipioAcidente) {
		this.municipioAcidente = municipioAcidente;
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
	public String getDataAcidente() {
		return dataAcidente;
	}
	public void setDataAcidente(String dataAcidente) {
		this.dataAcidente = dataAcidente;
	}
	public String getDataEmissaoCat() {
		return dataEmissaoCat;
	}
	public void setDataEmissaoCat(String dataEmissaoCat) {
		this.dataEmissaoCat = dataEmissaoCat;
	}
	public int getDiasAtraso() {
		return diasAtraso;
	}
	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
	public String getPrazo() {
		return prazo;
	}
	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}
	public String getClassificacaoGravidade() {
		return classificacaoGravidade;
	}
	public void setClassificacaoGravidade(String classificacaoGravidade) {
		this.classificacaoGravidade = classificacaoGravidade;
	}
	public String getDataAvaliacaoMedica() {
		return dataAvaliacaoMedica;
	}
	public void setDataAvaliacaoMedica(String dataAvaliacaoMedica) {
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
	public String getSituacaoAvaliacaoMedica() {
		return situacaoAvaliacaoMedica;
	}
	public void setSituacaoAvaliacaoMedica(String situacaoAvaliacaoMedica) {
		this.situacaoAvaliacaoMedica = situacaoAvaliacaoMedica;
	}
	public String getDataLiberacao() {
		return dataLiberacao;
	}
	public void setDataLiberacao(String dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	public boolean getPendenciaCorrecaoCat() {
		return pendenciaCorrecaoCat;
	}
	public void setPendenciaCorrecaoCat(boolean pendenciaCorrecaoCat) {
		this.pendenciaCorrecaoCat = pendenciaCorrecaoCat;
	}
	public String getJustificativaAtrasoEmissaoCat() {
		return justificativaAtrasoEmissaoCat;
	}
	public void setJustificativaAtrasoEmissaoCat(String justificativaAtrasoEmissaoCat) {
		this.justificativaAtrasoEmissaoCat = justificativaAtrasoEmissaoCat;
	}
	public String getNumeroCartaMultaEmpresa() {
		return numeroCartaMultaEmpresa;
	}
	public void setNumeroCartaMultaEmpresa(String numeroCartaMultaEmpresa) {
		this.numeroCartaMultaEmpresa = numeroCartaMultaEmpresa;
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
	public String getDiagnosticoProvavel() {
		return diagnosticoProvavel;
	}
	public void setDiagnosticoProvavel(String diagnosticoProvavel) {
		this.diagnosticoProvavel = diagnosticoProvavel;
	}
	public String getAgenteCausador() {
		return agenteCausador;
	}
	public void setAgenteCausador(String agenteCausador) {
		this.agenteCausador = agenteCausador;
	}
	public boolean isComunicavelSus() {
		return comunicavelSus;
	}
	public void setComunicavelSus(boolean comunicavelSus) {
		this.comunicavelSus = comunicavelSus;
	}
	public boolean isFerimentoGraveConformeANP() {
		return ferimentoGraveConformeANP;
	}
	public void setFerimentoGraveConformeANP(boolean ferimentoGraveConformeANP) {
		this.ferimentoGraveConformeANP = ferimentoGraveConformeANP;
	}
	public String getNumeroCat() {
		return numeroCat;
	}
	public void setNumeroCat(String numeroCat) {
		this.numeroCat = numeroCat;
	}
	public String getLocalizacaoLesao() {
		return localizacaoLesao;
	}
	public void setLocalizacaoLesao(String localizacaoLesao) {
		this.localizacaoLesao = localizacaoLesao;
	}
	public String getNaturezaLesao() {
		return naturezaLesao;
	}
	public void setNaturezaLesao(String naturezaLesao) {
		this.naturezaLesao = naturezaLesao;
	}
	public String getDiasAfastamento() {
		return diasAfastamento;
	}
	public void setDiasAfastamento(String diasAfastamento) {
		this.diasAfastamento = diasAfastamento;
	}
	public String getIntervaloAfastamento() {
		return intervaloAfastamento;
	}
	public void setIntervaloAfastamento(String intervaloAfastamento) {
		this.intervaloAfastamento = intervaloAfastamento;
	}
	public String getHorasPerdidas() {
		return horasPerdidas;
	}
	public void setHorasPerdidas(String horasPerdidas) {
		this.horasPerdidas = horasPerdidas;
	}
	public double getSalarioHora() {
		return salarioHora;
	}
	public void setSalarioHora(double salarioHora) {
		this.salarioHora = salarioHora;
	}
	public String getCustoAcidente2() {
		return custoAcidente2;
	}
	public void setCustoAcidente2(String custoAcidente2) {
		this.custoAcidente2 = custoAcidente2;
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
	public String getDataComunicacaoSindicato() {
		return dataComunicacaoSindicato;
	}
	public void setDataComunicacaoSindicato(String dataComunicacaoSindicato) {
		this.dataComunicacaoSindicato = dataComunicacaoSindicato;
	}
	public String getDiasAcidenteComunicacaoSindicato() {
		return diasAcidenteComunicacaoSindicato;
	}
	public void setDiasAcidenteComunicacaoSindicato(String diasAcidenteComunicacaoSindicato) {
		this.diasAcidenteComunicacaoSindicato = diasAcidenteComunicacaoSindicato;
	}
	public String getJustificativaAtrasoEmissaoCarta() {
		return justificativaAtrasoEmissaoCarta;
	}
	public void setJustificativaAtrasoEmissaoCarta(String justificativaAtrasoEmissaoCarta) {
		this.justificativaAtrasoEmissaoCarta = justificativaAtrasoEmissaoCarta;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getGrauInstrucao() {
		return grauInstrucao;
	}
	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public double getRemuneracao() {
		return remuneracao;
	}
	public void setRemuneracao(double remuneracao) {
		this.remuneracao = remuneracao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
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
	
}
