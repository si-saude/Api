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
public class ItemRefeicao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private NutricaoAlimento alimento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MedidaAlimentar medidaCaseira;
	
	@NotNull(message="É necessário informar a Refeição do Item Refeição.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Refeicao refeicao;
	
	private int quantidade;
	
	private float ne;
	
	private float ve;

	@Version
	private long version;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NutricaoAlimento getAlimento() {
		return alimento;
	}

	public void setAlimento(NutricaoAlimento alimento) {
		this.alimento = alimento;
	}

	public MedidaAlimentar getMedidaCaseira() {
		return medidaCaseira;
	}

	public void setMedidaCaseira(MedidaAlimentar medidaCaseira) {
		this.medidaCaseira = medidaCaseira;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getNe() {
		return ne;
	}

	public void setNe(float ne) {
		this.ne = ne;
	}

	public float getVe() {
		return ve;
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
