package br.com.saude.api.model.entity.po;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aptidao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Grupo Monitoramento.")
	private GrupoMonitoramento grupoMonitoramento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar o Aso.")
	private Aso aso;
	
	@Size(max = 32, message="Tamanho máximo para Aptidão do Aso: 32")
	private String aptidaoAso;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAptidaoAso() {
		return aptidaoAso;
	}

	public void setAptidaoAso(String aptidaoAso) {
		this.aptidaoAso = aptidaoAso;
	}
	
	public Aso getAso() {
		return aso;
	}

	public void setAso(Aso aso) {
		this.aso = aso;
	}
	
	public GrupoMonitoramento getGrupoMonitoramento() {
		return grupoMonitoramento;
	}

	public void setGrupoMonitoramento(GrupoMonitoramento grupoMonitoramento) {
		this.grupoMonitoramento = grupoMonitoramento;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
