package br.com.saude.api.model.entity.po;

import java.util.Date;
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
public class FilaAtendimentoOcupacional {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Profissional.")
	private Profissional profissional;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Localização da Fila.")
	private Localizacao localizacao;
	
	@NotNull(message="É necessário informar o Horário de Início.")
	private Date inicio;
	
	@NotNull(message="É necessário informar a Atualização.")
	private Date atualizacao;
	
	private Date fim;
	
	@NotNull(message="É necessário informar o Status.")
	@Size(max = 64, message="Tamanho máximo para Status: 64")
	private String status;
	
	@OneToMany(mappedBy="fila", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<FilaAtendimentoOcupacionalAtualizacao> atualizacoes;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<FilaAtendimentoOcupacionalAtualizacao> getAtualizacoes() {
		return atualizacoes;
	}

	public void setAtualizacoes(List<FilaAtendimentoOcupacionalAtualizacao> atualizacoes) {
		this.atualizacoes = atualizacoes;
	}
	
	@Override
	public boolean equals(Object fila) {
		return ((FilaAtendimentoOcupacional)fila).id == this.id && this.id > 0;
	}
}
