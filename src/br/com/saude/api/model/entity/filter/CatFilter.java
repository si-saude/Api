package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class CatFilter extends GenericFilter {

	private EmpregadoFilter empregado;
	private EmpresaFilter empresa;
	private GerenciaFilter gerencia;
	private String telefoneGerente;
	private String fiscalContrato;
	private String telefoneFiscal;
	private DateFilter dataOcorrencia;
	private String local;
	private String descricao;
	private BooleanFilter empregadoServicoCompanhia;
	private BooleanFilter ocorrenciaAmbienteTrabalho;
	private BooleanFilter ocorrenciaTrajeto;
	private String responsavelInformacao;
	private DateFilter dataInformacao;
	private String caracterizacao;
	private BooleanFilter lesaoCorporal;
	private String nexoCausal;
	private ProfissionalFilter profissionalCaracterizacao;
	private DateFilter dataCaracterizacao;
	private ClassificacaoAfastamentoFilter classificacao;
	private int tempoPrevisto;
	private DiagnosticoFilter cid;
	private BooleanFilter ferimentoGrave;
	private ProfissionalFilter profissionalClassificacao;
	private DateFilter dataClassificacao;
	private BooleanFilter registroSd2000;
	private BooleanFilter catSd2000;
	private BooleanFilter comunicavelSus;
	private BooleanFilter pendenciaCorrecao;
	private BooleanFilter catInss;
	private BooleanFilter convocado;
	private BooleanFilter ausenciaExames;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public EmpresaFilter getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaFilter empresa) {
		this.empresa = empresa;
	}
	public GerenciaFilter getGerencia() {
		return gerencia;
	}
	public void setGerencia(GerenciaFilter gerencia) {
		this.gerencia = gerencia;
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
	public DateFilter getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(DateFilter dataOcorrencia) {
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
	public BooleanFilter getEmpregadoServicoCompanhia() {
		return empregadoServicoCompanhia;
	}
	public void setEmpregadoServicoCompanhia(BooleanFilter empregadoServicoCompanhia) {
		this.empregadoServicoCompanhia = empregadoServicoCompanhia;
	}
	public BooleanFilter getOcorrenciaAmbienteTrabalho() {
		return ocorrenciaAmbienteTrabalho;
	}
	public void setOcorrenciaAmbienteTrabalho(BooleanFilter ocorrenciaAmbienteTrabalho) {
		this.ocorrenciaAmbienteTrabalho = ocorrenciaAmbienteTrabalho;
	}
	public BooleanFilter getOcorrenciaTrajeto() {
		return ocorrenciaTrajeto;
	}
	public void setOcorrenciaTrajeto(BooleanFilter ocorrenciaTrajeto) {
		this.ocorrenciaTrajeto = ocorrenciaTrajeto;
	}
	public String getResponsavelInformacao() {
		return responsavelInformacao;
	}
	public void setResponsavelInformacao(String responsavelInformacao) {
		this.responsavelInformacao = responsavelInformacao;
	}
	public DateFilter getDataInformacao() {
		return dataInformacao;
	}
	public void setDataInformacao(DateFilter dataInformacao) {
		this.dataInformacao = dataInformacao;
	}
	public String getCaracterizacao() {
		return caracterizacao;
	}
	public void setCaracterizacao(String caracterizacao) {
		this.caracterizacao = caracterizacao;
	}
	public BooleanFilter getLesaoCorporal() {
		return lesaoCorporal;
	}
	public void setLesaoCorporal(BooleanFilter lesaoCorporal) {
		this.lesaoCorporal = lesaoCorporal;
	}
	public String getNexoCausal() {
		return nexoCausal;
	}
	public void setNexoCausal(String nexoCausal) {
		this.nexoCausal = nexoCausal;
	}
	public ProfissionalFilter getProfissionalCaracterizacao() {
		return profissionalCaracterizacao;
	}
	public void setProfissionalCaracterizacao(ProfissionalFilter profissionalCaracterizacao) {
		this.profissionalCaracterizacao = profissionalCaracterizacao;
	}
	public DateFilter getDataCaracterizacao() {
		return dataCaracterizacao;
	}
	public void setDataCaracterizacao(DateFilter dataCaracterizacao) {
		this.dataCaracterizacao = dataCaracterizacao;
	}
	public ClassificacaoAfastamentoFilter getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(ClassificacaoAfastamentoFilter classificacao) {
		this.classificacao = classificacao;
	}
	public int getTempoPrevisto() {
		return tempoPrevisto;
	}
	public void setTempoPrevisto(int tempoPrevisto) {
		this.tempoPrevisto = tempoPrevisto;
	}
	public DiagnosticoFilter getCid() {
		return cid;
	}
	public void setCid(DiagnosticoFilter cid) {
		this.cid = cid;
	}
	public BooleanFilter getFerimentoGrave() {
		return ferimentoGrave;
	}
	public void setFerimentoGrave(BooleanFilter ferimentoGrave) {
		this.ferimentoGrave = ferimentoGrave;
	}
	public ProfissionalFilter getProfissionalClassificacao() {
		return profissionalClassificacao;
	}
	public void setProfissionalClassificacao(ProfissionalFilter profissionalClassificacao) {
		this.profissionalClassificacao = profissionalClassificacao;
	}
	public DateFilter getDataClassificacao() {
		return dataClassificacao;
	}
	public void setDataClassificacao(DateFilter dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}
	public BooleanFilter getRegistroSd2000() {
		return registroSd2000;
	}
	public void setRegistroSd2000(BooleanFilter registroSd2000) {
		this.registroSd2000 = registroSd2000;
	}
	public BooleanFilter getCatSd2000() {
		return catSd2000;
	}
	public void setCatSd2000(BooleanFilter catSd2000) {
		this.catSd2000 = catSd2000;
	}
	public BooleanFilter getComunicavelSus() {
		return comunicavelSus;
	}
	public void setComunicavelSus(BooleanFilter comunicavelSus) {
		this.comunicavelSus = comunicavelSus;
	}
	public BooleanFilter getPendenciaCorrecao() {
		return pendenciaCorrecao;
	}
	public void setPendenciaCorrecao(BooleanFilter pendenciaCorrecao) {
		this.pendenciaCorrecao = pendenciaCorrecao;
	}
	public BooleanFilter getCatInss() {
		return catInss;
	}
	public void setCatInss(BooleanFilter catInss) {
		this.catInss = catInss;
	}
	public BooleanFilter getConvocado() {
		return convocado;
	}
	public void setConvocado(BooleanFilter convocado) {
		this.convocado = convocado;
	}
	public BooleanFilter getAusenciaExames() {
		return ausenciaExames;
	}
	public void setAusenciaExames(BooleanFilter ausenciaExames) {
		this.ausenciaExames = ausenciaExames;
	}
	
}