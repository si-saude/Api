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
public class Refeicao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 128, message="Tamanho máximo para Nome da Refeição: 128")
	private String nome;
	
	@OneToMany(mappedBy="refeicao", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ItemRefeicao> itens;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Recordatório da Refeição.")
	private Recordatorio recordatorio;
	
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

	public List<ItemRefeicao> getItens() {
		return itens;
	}

	public void setItens(List<ItemRefeicao> itens) {
		this.itens = itens;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Recordatorio getRecordatorio() {
		return recordatorio;
	}

	public void setRecordatorio(Recordatorio recordatorio) {
		this.recordatorio = recordatorio;
	}
	
}
