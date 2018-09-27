package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GrupoMonitoramento {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome do Grupo de Monitoramento.")
	@Size(max = 128, message="Tamanho máximo para Nome do Grupo de Monitoramento: 128")
	@Column(unique=true)
	private String nome;
	
	@NotNull(message="É necessário informar o Tipo de Grupo de Monitoramento.")
	@ManyToOne(fetch=FetchType.LAZY)
	private TipoGrupoMonitoramento tipoGrupoMonitoramento;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="grupoMonitoramentos")
	private List<Empregado> empregados;
	
	@OneToMany(mappedBy="grupoMonitoramento", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Avaliacao> avaliacoes;
	
	private boolean recorrente;
	
	private boolean relatorio;
	
	private boolean auditoriaAso;
	
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

	public TipoGrupoMonitoramento getTipoGrupoMonitoramento() {
		return tipoGrupoMonitoramento;
	}

	public void setTipoGrupoMonitoramento(TipoGrupoMonitoramento tipoGrupoMonitoramento) {
		this.tipoGrupoMonitoramento = tipoGrupoMonitoramento;
	}

	public List<Empregado> getEmpregados() {
		return empregados;
	}

	public void setEmpregados(List<Empregado> empregados) {
		this.empregados = empregados;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isRecorrente() {
		return recorrente;
	}

	public void setRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
	}
	
	@Override
	public boolean equals(Object grupoMonitoramento) {
		return ((GrupoMonitoramento)grupoMonitoramento).id == this.id && this.id > 0;
	}

	public boolean isRelatorio() {
		return relatorio;
	}

	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}

	public boolean isAuditoriaAso() {
		return auditoriaAso;
	}

	public void setAuditoriaAso(boolean auditoriaAso) {
		this.auditoriaAso = auditoriaAso;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}
