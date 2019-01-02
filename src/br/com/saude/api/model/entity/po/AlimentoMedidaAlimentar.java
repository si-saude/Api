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
public class AlimentoMedidaAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar o Alimento do Alimento Medida Alimentar.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Alimento alimento;
	
	@NotNull(message="� necess�rio informar a Medida Alimentar do Alimento Medida Alimentar.")
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
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
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
