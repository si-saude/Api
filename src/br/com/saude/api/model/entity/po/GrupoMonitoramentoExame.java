package br.com.saude.api.model.entity.po;

import java.util.List;

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

@Entity
public class GrupoMonitoramentoExame {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Exame do Grupo de Monitoramento.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Exame exame;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Periodicidade periodicidade;
	
	@NotNull(message="É necessário informar o Grupo de Monitoramento do Exame.")
	@ManyToOne(fetch=FetchType.LAZY)
	private GrupoMonitoramento grupoMonitoramento;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="grupomonitoramentoexame_criterio", 
	joinColumns = {@JoinColumn(name="grupomonitoramentoexame_id")}, 
	inverseJoinColumns = {@JoinColumn(name="criterio_id")})
	private List<Criterio> criterios;
	
	private boolean opcional;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public GrupoMonitoramento getGrupoMonitoramento() {
		return grupoMonitoramento;
	}

	public void setGrupoMonitoramento(GrupoMonitoramento grupoMonitoramento) {
		this.grupoMonitoramento = grupoMonitoramento;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}

	public boolean isOpcional() {
		return opcional;
	}

	public void setOpcional(boolean opcional) {
		this.opcional = opcional;
	}
}
