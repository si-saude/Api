package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class QuestionarioVedacaoMascara {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Data do Questionario.")
	private Date data;

	private boolean fumacaIrritante;	

	private boolean sacarina;	

	private boolean acetatoIsoamil;	

	private boolean benzoatoDetonium;	
	
	private String tipoRespirador;
	
	private String tamanhoRespirador;
		
	@Size(max = 128, message="Tamanho máximo para o Modelo do Respirador: 128")
	private String modelo;

	@Size(max = 128, message="Tamanho máximo para  o Número do Certificado: 128")
	private String numeroCertificadoAprovacao;	
	
	@Size(max = 128, message="Tamanho máximo para o Filtro Utilizado: 128")
	private String filtroUtilizado;		

	private boolean barba;
	
	private boolean bigode;
	
	private boolean costeleta;
	
	private boolean nAPelosFace;
	
	private boolean oculos;
	
	private boolean lenteContato;
	
	private boolean nACorrecaoVisao;
	
	private boolean satisfatoria;
	
	private boolean deficiente;
	
	private boolean nATesteQualitativo;
	
	private boolean satisfatoriaTestePressao;
	
	private boolean deficienteTestePressao;
	
	private boolean nATestePressao;
	
	private boolean resultadoTesteVedacao;
	
	@Size(max = 512, message="Tamanho máximo para o Comentário: 512")
	private String comentario;		
	
	@Size(max = 256, message="Tamanho máximo para o Exposição a aerodispersóides: 256")
	private String  exposicaoAerodispersoide;		
	
	@Size(max = 32, message="Tamanho máximo para as Horas utilizadas: 2")
	private String  horaUsada;
	
	@Size(max = 1, message="Tamanho máximo para os Dias utilizados: 1")
	private String  diaUsado;	
	
	private boolean esforcoFisicoUtilizandoMascara;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isFumacaIrritante() {
		return fumacaIrritante;
	}

	public void setFumacaIrritante(boolean fumacaIrritante) {
		this.fumacaIrritante = fumacaIrritante;
	}

	public boolean isSacarina() {
		return sacarina;
	}

	public void setSacarina(boolean sacarina) {
		this.sacarina = sacarina;
	}

	public boolean isAcetatoIsoamil() {
		return acetatoIsoamil;
	}

	public void setAcetatoIsoamil(boolean acetatoIsoamil) {
		this.acetatoIsoamil = acetatoIsoamil;
	}

	public boolean isBenzoatoDetonium() {
		return benzoatoDetonium;
	}

	public void setBenzoatoDetonium(boolean benzoatoDetonium) {
		this.benzoatoDetonium = benzoatoDetonium;
	}

	public String getTipoRespirador() {
		return tipoRespirador;
	}

	public void setTipoRespirador(String tipoRespirador) {
		this.tipoRespirador = tipoRespirador;
	}
	
	public String getTamanhoRespirador() {
		return tamanhoRespirador;
	}

	public void setTamanhoRespirador(String tamanhoRespirador) {
		this.tamanhoRespirador = tamanhoRespirador;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroCertificadoAprovacao() {
		return numeroCertificadoAprovacao;
	}

	public void setNumeroCertificadoAprovacao(String numeroCertificadoAprovacao) {
		this.numeroCertificadoAprovacao = numeroCertificadoAprovacao;
	}

	public String getFiltroUtilizado() {
		return filtroUtilizado;
	}

	public void setFiltroUtilizado(String filtroUtilizado) {
		this.filtroUtilizado = filtroUtilizado;
	}

	public boolean isBarba() {
		return barba;
	}

	public void setBarba(boolean barba) {
		this.barba = barba;
	}

	public boolean isBigode() {
		return bigode;
	}

	public void setBigode(boolean bigode) {
		this.bigode = bigode;
	}

	public boolean isCosteleta() {
		return costeleta;
	}

	public void setCosteleta(boolean costeleta) {
		this.costeleta = costeleta;
	}

	public boolean isnAPelosFace() {
		return nAPelosFace;
	}

	public void setnAPelosFace(boolean nAPelosFace) {
		this.nAPelosFace = nAPelosFace;
	}

	public boolean isOculos() {
		return oculos;
	}

	public void setOculos(boolean oculos) {
		this.oculos = oculos;
	}

	public boolean isLenteContato() {
		return lenteContato;
	}

	public void setLenteContato(boolean lenteContato) {
		this.lenteContato = lenteContato;
	}

	public boolean isnACorrecaoVisao() {
		return nACorrecaoVisao;
	}

	public void setnACorrecaoVisao(boolean nACorrecaoVisao) {
		this.nACorrecaoVisao = nACorrecaoVisao;
	}

	public boolean isSatisfatoria() {
		return satisfatoria;
	}

	public void setSatisfatoria(boolean satisfatoria) {
		this.satisfatoria = satisfatoria;
	}

	public boolean isDeficiente() {
		return deficiente;
	}

	public void setDeficiente(boolean deficiente) {
		this.deficiente = deficiente;
	}

	public boolean isnATesteQualitativo() {
		return nATesteQualitativo;
	}

	public void setnATesteQualitativo(boolean nATesteQualitativo) {
		this.nATesteQualitativo = nATesteQualitativo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isSatisfatoriaTestePressao() {
		return satisfatoriaTestePressao;
	}

	public void setSatisfatoriaTestePressao(boolean satisfatoriaTestePressao) {
		this.satisfatoriaTestePressao = satisfatoriaTestePressao;
	}

	public boolean isDeficienteTestePressao() {
		return deficienteTestePressao;
	}

	public void setDeficienteTestePressao(boolean deficienteTestePressao) {
		this.deficienteTestePressao = deficienteTestePressao;
	}

	public boolean isnATestePressao() {
		return nATestePressao;
	}

	public void setnATestePressao(boolean nATestePressao) {
		this.nATestePressao = nATestePressao;
	}

	public boolean isResultadoTesteVedacao() {
		return resultadoTesteVedacao;
	}

	public void setResultadoTesteVedacao(boolean resultadoTesteVedacao) {
		this.resultadoTesteVedacao = resultadoTesteVedacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getExposicaoAerodispersoide() {
		return exposicaoAerodispersoide;
	}

	public void setExposicaoAerodispersoide(String exposicaoAerodispersoide) {
		this.exposicaoAerodispersoide = exposicaoAerodispersoide;
	}

	public String getHoraUsada() {
		return horaUsada;
	}

	public void setHoraUsada(String horaUsada) {
		this.horaUsada = horaUsada;
	}

	public String getDiaUsado() {
		return diaUsado;
	}

	public void setDiaUsado(String diaUsado) {
		this.diaUsado = diaUsado;
	}

	public boolean isEsforcoFisicoUtilizandoMascara() {
		return esforcoFisicoUtilizandoMascara;
	}

	public void setEsforcoFisicoUtilizandoMascara(boolean esforcoFisicoUtilizandoMascara) {
		this.esforcoFisicoUtilizandoMascara = esforcoFisicoUtilizandoMascara;
	}	
}
