package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class HistoricoGrupoMonitoramento {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="� necess�rio informar a Data de Remo��o do Hist�rico.")
	private Date dataRemocao;
	
	@NotNull(message="� necess�rio informar o Grupo de Monitoramento do Hist�rico.")
	@ManyToOne(fetch=FetchType.EAGER)
	private GrupoMonitoramento grupoMonitoramento;
	
	@NotNull(message="� necess�rio informar o Empregado do Hist�rico.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	public GrupoMonitoramento getGrupoMonitoramento() {
		return grupoMonitoramento;
	}

	public void setGrupoMonitoramento(GrupoMonitoramento grupoMonitoramento) {
		this.grupoMonitoramento = grupoMonitoramento;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
