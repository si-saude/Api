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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

@Entity
public class RiscoEmpregado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Formula("(select get_valor_ponderado_risco_empregado_by_id(id))")
	private double valorPonderado;	
	
	private double valor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private RiscoPotencial riscoPotencial;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipe;
	
	@OneToMany(mappedBy="riscoEmpregado", fetch=FetchType.LAZY,
			cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Triagem> triagens;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional profissional;
	
	private Date data;
	
	@Size(max = 32, message="Tamanho m�ximo para Status do Risco Potencial: 32")
	private String status;
	
	@Transient
	private String statusRPSat;
	
	private boolean ativo;
	
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
	
	public double getValorPonderado() {
		return valorPonderado;
	}

	public void setValorPonderado(double valorPonderado) {
		this.valorPonderado = valorPonderado;
	}

	public RiscoPotencial getRiscoPotencial() {
		return riscoPotencial;
	}

	public void setRiscoPotencial(RiscoPotencial riscoPotencial) {
		this.riscoPotencial = riscoPotencial;
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
	
	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusRPSat() {
		
		if ( this.valor > 0 && this.valor < 0.57 ) {
			this.statusRPSat = "ACEIT�VEL";
		} else if ( this.valor >= 0.57 && this.valor < 0.72 ) {
			this.statusRPSat = "TOLER�VEL";
		} else if ( this.valor >= 0.72 ) {
			this.statusRPSat = "INACEIT�VEL";
		}
		
		return statusRPSat;
	}
	
	@Override
	public boolean equals(Object riscoEmpregado) {
		return ((RiscoEmpregado)riscoEmpregado).id == this.id && this.id > 0;
	}
	
}