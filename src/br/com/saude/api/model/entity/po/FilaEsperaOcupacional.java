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
import javax.validation.constraints.Size;

@Entity
public class FilaEsperaOcupacional {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Tarefa da Fila.")
	private Servico servico;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Localização da Fila.")
	private Localizacao localizacao;
	
	@NotNull(message="É necessário informar o Empregado da Fila.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@NotNull(message="É necessário informar o Horário do Check-in.")
	private Date horarioCheckin;
	
	@NotNull(message="É necessário informar a Atualização.")
	private Date atualizacao;
	
	@NotNull(message="É necessário informar o Status da Fila.")
	@Size(max = 64, message="Tamanho máximo para Status da Fila: 64")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private FichaColeta fichaColeta;
	
	private long tempoEspera;
	
	private Date saida;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Date getHorarioCheckin() {
		return horarioCheckin;
	}

	public void setHorarioCheckin(Date horarioCheckin) {
		this.horarioCheckin = horarioCheckin;
	}

	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}
	
	public long getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(long tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public FichaColeta getFichaColeta() {
		return fichaColeta;
	}

	public void setFichaColeta(FichaColeta fichaColeta) {
		this.fichaColeta = fichaColeta;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
