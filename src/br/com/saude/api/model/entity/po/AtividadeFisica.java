package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AtividadeFisica {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(max = 512, message="Tamanho máximo para Observação da Atividade Física: 512")
	@NotNull(message="É necessário informar a Descrição da Atividade Física.")
	private String descricao;
	
	private boolean domingo;
	private boolean segunda;
	private boolean terca;
	private boolean quarta;
	private boolean quinta;
	private boolean sexta;
	private boolean sabado;
	
	private int minuto;
	
	@Transient 
	private int totalMinuto;
	
	@Size(max = 128, message="Tamanho máximo para Classificação da Atividade Física: 128")
	private String classificacao;
	
	@Size(max = 2048, message="Tamanho máximo para Observação da Atividade Física: 2048")
	private String observacao;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getTotalMinuto() {
		int mult = 0;
		if ( this.getMinuto() != 0 ) {
			if ( this.isDomingo() )
				mult++;
			if ( this.isSegunda() )
				mult++;
			if ( this.isTerca() )
				mult++;
			if ( this.isQuarta() )
				mult++;
			if ( this.isQuinta() )
				mult++;
			if ( this.isSexta() )
				mult++;
			if ( this.isSabado() )
				mult++;
			this.setTotalMinuto(mult*this.getMinuto());
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
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
