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
public class FilaAtendimentoOcupacionalAtualizacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="� necess�rio informar a Fila da Atualiza��o.")
	private FilaAtendimentoOcupacional fila;
	
	@NotNull(message="� necess�rio informar o Status da Atualiza��o.")
	@Size(max = 64, message="Tamanho m�ximo para Status da Atualiza��o: 64")
	private String status;
	
	private long tempo;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FilaAtendimentoOcupacional getFila() {
		return fila;
	}

	public void setFila(FilaAtendimentoOcupacional fila) {
		this.fila = fila;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
