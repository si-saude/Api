package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ProfissionalConselho {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome do Conselho.")
	@Size(max = 200, message="Tamanho máximo para Nome do Conselho: 200")
	private String conselho;
	
	@Size(max = 2, message="Tamanho máximo para UF do Conselho: 2")
	private String uf;
	
	@NotNull(message="É necessário informar o Número do Registro do Conselho.")
	@Size(max = 26, message="Tamanho máximo para Nome do Registro do Conselho: 26")
	private String numero;
	
	private Date vencimento;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConselho() {
		return conselho;
	}

	public void setConselho(String conselho) {
		this.conselho = conselho;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
}
