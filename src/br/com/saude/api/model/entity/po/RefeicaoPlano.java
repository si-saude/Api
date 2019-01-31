package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RefeicaoPlano {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 128, message="Tamanho máximo para Nome da Refeição do Plano: 128")
	private String nome;
	
	@OneToMany(mappedBy="refeicaoPlano", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ItemRefeicaoPlano> itens;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Plano Alimentar da Refeição.")
	private PlanoAlimentar planoAlimentar;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ItemRefeicaoPlano> getItens() {
		return itens;
	}

	public void setItens(List<ItemRefeicaoPlano> itens) {
		this.itens = itens;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public PlanoAlimentar getPlanoAlimentar() {
		return planoAlimentar;
	}

	public void setPlanoAlimentar(PlanoAlimentar planoAlimentar) {
		this.planoAlimentar = planoAlimentar;
	}
	
}
