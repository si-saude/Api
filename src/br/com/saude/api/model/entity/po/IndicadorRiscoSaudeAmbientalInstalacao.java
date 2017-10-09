package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class IndicadorRiscoSaudeAmbientalInstalacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Instalação do Indicador de Risco.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Instalacao instalacao;
	
	@NotNull(message="É necessário informar o Indicador de Risco.")
	@ManyToOne(fetch=FetchType.EAGER)
	private IndicadorRiscoSaudeAmbiental indicadorRisco;
	
	private Date dataInspecao;
	
	@Min(value=0, message="Valor mínimo para Avaliação do Indicador de Risco: 0")
	@Max(value=5, message="Valor máximo para Avaliação do Indicador de Risco: 5")
	private int avaliacao;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instalacao getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(Instalacao instalacao) {
		this.instalacao = instalacao;
	}

	public IndicadorRiscoSaudeAmbiental getIndicadorRisco() {
		return indicadorRisco;
	}

	public void setIndicadorRisco(IndicadorRiscoSaudeAmbiental indicadorRisco) {
		this.indicadorRisco = indicadorRisco;
	}

	public Date getDataInspecao() {
		return dataInspecao;
	}

	public void setDataInspecao(Date dataInspecao) {
		this.dataInspecao = dataInspecao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
