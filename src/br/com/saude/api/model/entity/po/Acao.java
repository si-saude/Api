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
public class Acao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 64, message="Tamanho máximo para Tipo da Ação: 64")
	private String tipo;
	
	@Size(max = 64, message="Tamanho máximo para Status da Ação: 64")
	private String status;
	
	@Size(max = 128, message="Tamanho máximo para Tipo do Contato: 128")
	private String tipoContato;

	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Ação.")
	private AcaoIntervencao acaoIntervencao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Triagem da Ação.")
	private Triagem triagem;
	
	@OneToMany(mappedBy="acao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Acompanhamento> acompanhamentos; 
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

	public AcaoIntervencao getAcaoIntervencao() {
		return acaoIntervencao;
	}

	public void setAcaoIntervencao(AcaoIntervencao acaoIntervencao) {
		this.acaoIntervencao = acaoIntervencao;
	}
	
	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}
	
	public List<Acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
