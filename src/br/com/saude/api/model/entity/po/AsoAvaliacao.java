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
public class AsoAvaliacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Avaliação.")
	@Size(max = 128, message="Tamanho máximo para Nome da Avaliação: 128")
	private String descricao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Aso aso;

	private boolean conforme;
	
	@Version
	private long version;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Aso getAso() {
		return aso;
	}

	public void setAso(Aso aso) {
		this.aso = aso;
	}

	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
