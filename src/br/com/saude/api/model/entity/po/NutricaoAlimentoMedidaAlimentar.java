package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class NutricaoAlimentoMedidaAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Alimento do Alimento Medida Alimentar.")
	@ManyToOne(fetch=FetchType.EAGER)
	private NutricaoAlimento nutricaoAlimento;
	
	@NotNull(message="É necessário informar a Medida Alimentar do Alimento Medida Alimentar.")
	@ManyToOne(fetch=FetchType.LAZY)
	private MedidaAlimentar medidaAlimentar;
	
	private String referencia;
	private double quantidade;

	@Version
	private long version;
	
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
	public NutricaoAlimento getNutricaoAlimento() {
		return nutricaoAlimento;
	}
	public void setNutricaoAlimento(NutricaoAlimento nutricaoAlimento) {
		this.nutricaoAlimento = nutricaoAlimento;
	}
	public MedidaAlimentar getMedidaAlimentar() {
		return medidaAlimentar;
	}
	public void setMedidaAlimentar(MedidaAlimentar medidaAlimentar) {
		this.medidaAlimentar = medidaAlimentar;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
}
