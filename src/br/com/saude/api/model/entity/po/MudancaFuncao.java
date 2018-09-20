package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity
public class MudancaFuncao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Enfase enfase;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Funcao funcao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Ghe ghe;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Ghee ghee;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Regime regime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Gerencia gerencia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Base base;
		
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="mudancaFuncao_tarefa", 
	joinColumns = {@JoinColumn(name="mudancaFuncao_id")}, 
	inverseJoinColumns = {@JoinColumn(name="tarefa_id")})
	private List<Tarefa> tarefas;
	
	@Transient
	private String status;
	
	@Transient
	private Empregado cliente;
	
	@Transient
	private Date abertura;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Empregado getCliente() {
		return cliente;
	}

	public void setCliente(Empregado cliente) {
		this.cliente = cliente;
	}

	public Enfase getEnfase() {
		return enfase;
	}

	public void setEnfase(Enfase enfase) {
		this.enfase = enfase;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}

	public Ghee getGhee() {
		return ghee;
	}

	public void setGhee(Ghee ghee) {
		this.ghee = ghee;
	}

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public List<Tarefa> getTarefas() {
 		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

}
