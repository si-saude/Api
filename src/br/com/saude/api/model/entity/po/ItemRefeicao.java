package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@Entity
public class ItemRefeicao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Alimento alimento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MedidaAlimentar medidaCaseira;
	
	@NotNull(message="� necess�rio informar a Refei��o do Item Refei��o.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Refeicao refeicao;
	
	private double quantidade;
	
	@Formula("(select get_ve(id))")
	private float ve;

	@Version
	private long version;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public MedidaAlimentar getMedidaCaseira() {
		return medidaCaseira;
	}

	public void setMedidaCaseira(MedidaAlimentar medidaCaseira) {
		this.medidaCaseira = medidaCaseira;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public float getVe() {
		return this.ve;
	}

	public void setVe(float ve) {
		this.ve = ve;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	
	
}
