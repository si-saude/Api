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
public class AsoAlteracao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o ASO da Alteração.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Aso aso;
	
	@NotNull(message="É necessário informar o Usuário da Alteração.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
	@NotNull(message="É necessário informar o Status da Alteração.")
	@Size(max = 32, message="Tamanho máximo para Status da Alteração: 32")
	private String status;
	
	@NotNull(message="É necessário informar a Data da Alteração.")
	private Date data;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aso getAso() {
		return aso;
	}

	public void setAso(Aso aso) {
		this.aso = aso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
