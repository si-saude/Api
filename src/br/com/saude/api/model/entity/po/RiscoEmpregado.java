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

@Entity
public class RiscoEmpregado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private double valor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipe;
	
	@OneToMany(mappedBy="riscoEmpregado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Triagem> triagens;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Triagem> getTriagens() {
		return triagens;
	}

	public void setTriagens(List<Triagem> triagens) {
		this.triagens = triagens;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
