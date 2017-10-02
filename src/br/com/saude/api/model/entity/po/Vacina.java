package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vacina {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Descrição da Vacina.")
	@Size(max = 200, message="Tamanho máximo para Descrição da Vacina: 200")
	private String descricao;
	
	@NotNull(message="É necessário informar a Data da Vacina.")
	private Date data;
	
	@Size(max = 32, message="Tamanho máximo para Lote da Vacina: 32")
	private String lote;
	
	@Size(max = 128, message="Tamanho máximo para Laboratório da Vacina: 128")
	private String laboratorio;
	
	@Min(value=1, message="Valor mínimo para Dose da Vacina: 1")
	private int dose;
	
	private Date proximaDose;
	
	@NotNull(message="É necessário informar o Profissional da Vacina.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Profissional profissional;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public Date getProximaDose() {
		return proximaDose;
	}

	public void setProximaDose(Date proximaDose) {
		this.proximaDose = proximaDose;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
}
