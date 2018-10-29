package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OrderBy;

@Entity
public class IndicadorConhecimentoAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Enunciado do Indicador de Conhecimento Alimentar.")
	@Size(max = 1024, message="Tamanho máximo para o Enunciado do Indicador de Conhecimento Alimentar: 1024")
	private String enunciado;
	
	private int ordem;
	
	@OrderBy(clause = "ordem")
	@OneToMany(mappedBy="indicadorConhecimentoAlimentar", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ItemIndicadorConhecimentoAlimentar> itemIndicadorConhecimentoAlimentares;
	
	private boolean inativo;
	
	@Version
	private long version;
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public List<ItemIndicadorConhecimentoAlimentar> getItemIndicadorConhecimentoAlimentares() {
		return itemIndicadorConhecimentoAlimentares;
	}

	public void setItemIndicadorConhecimentoAlimentares(
			List<ItemIndicadorConhecimentoAlimentar> itemIndicadorConhecimentoAlimentares) {
		this.itemIndicadorConhecimentoAlimentares = itemIndicadorConhecimentoAlimentares;
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

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	
}