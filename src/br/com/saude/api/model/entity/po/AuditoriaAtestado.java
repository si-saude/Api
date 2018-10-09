package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class AuditoriaAtestado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar o Atestado da Auditoria Atestado.")
	private Atestado atestado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Item de Auditoria de Atestado.")
	private ItemAuditoriaAtestado itemAuditoriaAtestado;
	
	private boolean conforme;
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Atestado getAtestado() {
		return atestado;
	}



	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}



	public ItemAuditoriaAtestado getItemAuditoriaAtestado() {
		return itemAuditoriaAtestado;
	}



	public void setItemAuditoriaAtestado(ItemAuditoriaAtestado itemAuditoriaAtestado) {
		this.itemAuditoriaAtestado = itemAuditoriaAtestado;
	}



	public boolean isConforme() {
		return conforme;
	}



	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}



	public long getVersion() {
		return version;
	}



	public void setVersion(long version) {
		this.version = version;
	}



	@Version
	private long version;
}
