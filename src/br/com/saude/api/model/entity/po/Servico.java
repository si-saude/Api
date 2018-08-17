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
	
	@NotNull(message="� necess�rio informar Nome do Servi�o.")
	@Size(max = 128, message="Tamanho m�ximo para Nome do Servi�o: 128")
	@Column(unique=true)
	private String nome;
	
	@NotNull(message="� necess�rio informar C�digo do Servi�o.")
	@Size(max = 4, message="Tamanho m�ximo para C�digo do Servi�o: 4")
	private String codigo;
	
	@Size(max = 32, message="Tamanho m�ximo para Grupo do Servi�o: 32")
	@NotNull(message="� necess�rio informar Grupo do Servi�o.")
	private String grupo;
	
	@Size(max = 256, message="Tamanho m�ximo para URL do Servi�o: 256")
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
