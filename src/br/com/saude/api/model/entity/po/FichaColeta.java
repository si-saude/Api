package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class FichaColeta {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="fichaColeta", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RespostaFichaColeta> respostaFichaColetas;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RespostaFichaColeta> getRespostaFichaColetas() {
		return respostaFichaColetas;
	}

	public void setRespostaFichaColetas(List<RespostaFichaColeta> respostaFichaColetas) {
		this.respostaFichaColetas = respostaFichaColetas;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
