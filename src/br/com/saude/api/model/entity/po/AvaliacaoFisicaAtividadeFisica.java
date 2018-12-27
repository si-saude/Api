package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AvaliacaoFisicaAtividadeFisica {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Avaliação Física da Avalicação Física Atividade Física.")
	@ManyToOne(fetch=FetchType.EAGER)
	private AvaliacaoFisica avaliacaoFisica;
	
	@NotNull(message="É necessário informar a Atividade Física da Avalicação Física Atividade Física.")
	@ManyToOne(fetch=FetchType.LAZY)
	private AtividadeFisica atividadeFisica;

	private boolean domingo;
	private boolean segunda;
	private boolean terca;
	private boolean quarta;
	private boolean quinta;
	private boolean sexta;
	private boolean sabado;
	
	@Size(max = 128, message="Tamanho máximo para o Tipo da Avaliação Física Atividade Física: 128")
	private String tipo;
	
	private int minuto;
	
	@Transient
	private int totalMinuto;
	
	@Size(max = 128, message="Tamanho máximo para o Classificação da Avaliação Física Atividade Física: 128")
	private String classificacao;
	
	@Size(max = 2048, message="Tamanho máximo para o Observação da Avaliação Física Atividade Física: 2048")
	private String observacao;
	
	@Version
	private long version;
	
	public AvaliacaoFisica getAvaliacaoFisica() {
		return avaliacaoFisica;
	}

	public void setAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
		this.avaliacaoFisica = avaliacaoFisica;
	}

	public AtividadeFisica getAtividadeFisica() {
		return atividadeFisica;
	}

	public void setAtividadeFisica(AtividadeFisica atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}

	public boolean isDomingo() {
		return domingo;
	}

	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}

	public boolean isSegunda() {
		return segunda;
	}

	public void setSegunda(boolean segunda) {
		this.segunda = segunda;
	}

	public boolean isTerca() {
		return terca;
	}

	public void setTerca(boolean terca) {
		this.terca = terca;
	}

	public boolean isQuarta() {
		return quarta;
	}

	public void setQuarta(boolean quarta) {
		this.quarta = quarta;
	}

	public boolean isQuinta() {
		return quinta;
	}

	public void setQuinta(boolean quinta) {
		this.quinta = quinta;
	}

	public boolean isSexta() {
		return sexta;
	}

	public void setSexta(boolean sexta) {
		this.sexta = sexta;
	}

	public boolean isSabado() {
		return sabado;
	}

	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getTotalMinuto() {
		int sumMinuto = 0;
		if ( this.getMinuto() > 0 ) {
			if ( this.isDomingo() )
				sumMinuto++;
			if ( this.isSegunda() )
				sumMinuto++;
			if ( this.isTerca() )
				sumMinuto++;
			if ( this.isQuarta() )
				sumMinuto++;
			if ( this.isQuinta() )
				sumMinuto++;
			if ( this.isSexta() )
				sumMinuto++;
			if ( this.isSabado() )
				sumMinuto++;
			totalMinuto = this.getMinuto() * sumMinuto;
		}
		
		return totalMinuto;
	}

	public void setTotalMinuto(int totalMinuto) {
		this.totalMinuto = totalMinuto;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
}
