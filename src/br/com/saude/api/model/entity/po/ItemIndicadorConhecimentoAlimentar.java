package br.com.saude.api.model.entity.po;

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
public class ItemIndicadorConhecimentoAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar o Indicador de Conhecimento Alimentar do Item do Indicador de Conhecimento Alimentar.")
	private IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar;
	
	@Size(max = 1024, message="Tamanho máximo para o Descrição do Item do Indicador de Conhecimento Alimentar: 1024")
	private String descricao;
	
	private int ordem;
	
	private boolean certo;
	
	@Version
	private long version;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public IndicadorConhecimentoAlimentar getIndicadorConhecimentoAlimentar() {
		return indicadorConhecimentoAlimentar;
	}

	public void setIndicadorConhecimentoAlimentar(IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar) {
		this.indicadorConhecimentoAlimentar = indicadorConhecimentoAlimentar;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public boolean isCerto() {
		return certo;
	}

	public void setCerto(boolean certo) {
		this.certo = certo;
	}
	
}
