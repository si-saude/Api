package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Instalacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Instalação.")
	@Size(max = 120, message="Tamanho máximo para Nome da Instalação: 120")
	@Column(unique=true)
	private String nome;
	
	private float latitude;
	
	private float longitude;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoAcidenteInstalacao> indicadorRiscoAcidenteInstalacoes;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoAmbientalInstalacao> indicadorRiscoAmbientalInstalacoes;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoErgonomicoInstalacao> indicadorRiscoErgonomicoInstalacoes;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoSanitarioInstalacao> indicadorRiscoSanitarioInstalacoes;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoSaudeAmbientalInstalacao> indicadorRiscoSaudeAmbientalInstalacoes;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<IndicadorRiscoAcidenteInstalacao> getIndicadorRiscoAcidenteInstalacoes() {
		return indicadorRiscoAcidenteInstalacoes;
	}

	public void setIndicadorRiscoAcidenteInstalacoes(
			List<IndicadorRiscoAcidenteInstalacao> indicadorRiscoAcidenteInstalacoes) {
		this.indicadorRiscoAcidenteInstalacoes = indicadorRiscoAcidenteInstalacoes;
	}

	public List<IndicadorRiscoAmbientalInstalacao> getIndicadorRiscoAmbientalInstalacoes() {
		return indicadorRiscoAmbientalInstalacoes;
	}

	public void setIndicadorRiscoAmbientalInstalacoes(
			List<IndicadorRiscoAmbientalInstalacao> indicadorRiscoAmbientalInstalacoes) {
		this.indicadorRiscoAmbientalInstalacoes = indicadorRiscoAmbientalInstalacoes;
	}

	public List<IndicadorRiscoErgonomicoInstalacao> getIndicadorRiscoErgonomicoInstalacoes() {
		return indicadorRiscoErgonomicoInstalacoes;
	}

	public void setIndicadorRiscoErgonomicoInstalacoes(
			List<IndicadorRiscoErgonomicoInstalacao> indicadorRiscoErgonomicoInstalacoes) {
		this.indicadorRiscoErgonomicoInstalacoes = indicadorRiscoErgonomicoInstalacoes;
	}

	public List<IndicadorRiscoSanitarioInstalacao> getIndicadorRiscoSanitarioInstalacoes() {
		return indicadorRiscoSanitarioInstalacoes;
	}

	public void setIndicadorRiscoSanitarioInstalacoes(
			List<IndicadorRiscoSanitarioInstalacao> indicadorRiscoSanitarioInstalacoes) {
		this.indicadorRiscoSanitarioInstalacoes = indicadorRiscoSanitarioInstalacoes;
	}

	public List<IndicadorRiscoSaudeAmbientalInstalacao> getIndicadorRiscoSaudeAmbientalInstalacoes() {
		return indicadorRiscoSaudeAmbientalInstalacoes;
	}

	public void setIndicadorRiscoSaudeAmbientalInstalacoes(
			List<IndicadorRiscoSaudeAmbientalInstalacao> indicadorRiscoSaudeAmbientalInstalacoes) {
		this.indicadorRiscoSaudeAmbientalInstalacoes = indicadorRiscoSaudeAmbientalInstalacoes;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
