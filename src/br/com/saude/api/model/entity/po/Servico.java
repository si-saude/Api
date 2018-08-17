package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Servico {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome do Serviço.")
	@Size(max = 128, message="Tamanho máximo para Nome do Serviço: 128")
	@Column(unique=true)
	private String nome;
	
	@NotNull(message="É necessário informar Código do Serviço.")
	@Size(max = 4, message="Tamanho máximo para Código do Serviço: 4")
	private String codigo;
	
	@Size(max = 32, message="Tamanho máximo para Grupo do Serviço: 32")
	@NotNull(message="É necessário informar Grupo do Serviço.")
	private String grupo;
	
	@Size(max = 256, message="Tamanho máximo para URL do Serviço: 256")
	private String url;
	
	private boolean publico;
	
	@OneToMany(mappedBy="servico", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Atividade> atividades;

	private int intervalo;

	@Column(name="quantidadesolicitacaointervalo")
	private int quantidadeSolicitacaoIntervalo;
	
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public int getQuantidadeSolicitacaoIntervalo() {
		return quantidadeSolicitacaoIntervalo;
	}

	public void setQuantidadeSolicitacaoIntervalo(int quantidadeSolicitacaoIntervalo) {
		this.quantidadeSolicitacaoIntervalo = quantidadeSolicitacaoIntervalo;
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
