package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

@Entity
public class ItemRefeicaoPlano {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Alimento alimento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MedidaAlimentar medidaCaseira;
	
	@NotNull(message="É necessário informar a Refeição do Plano Alimentar do Item Refeição.")
	@ManyToOne(fetch=FetchType.EAGER)
	private RefeicaoPlano refeicaoPlano;
	
	private double quantidade;
	
	@Size(max = 1024, message="Tamanho máximo para Observação do Item Refeição: 1024")
	private String observacao;	
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="alimento_alimentos", 
	joinColumns = {@JoinColumn(name="itemrefeicao_id")}, 
	inverseJoinColumns = {@JoinColumn(name="alimento_id")})
	private List<Alimento> alimentos;
	
	@Formula("(select get_ve_plano(id))")
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

	public RefeicaoPlano getRefeicaoPlano() {
		return refeicaoPlano;
	}

	public void setRefeicaoPlano(RefeicaoPlano refeicaoPlano) {
		this.refeicaoPlano = refeicaoPlano;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	
	
}
