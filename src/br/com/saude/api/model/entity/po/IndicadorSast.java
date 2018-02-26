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
public class IndicadorSast {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 128, message="Tamanho máximo para Nome: 128")
	private String nome;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 0: 1024")
	private String indice0;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 1: 1024")
	private String indice1;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 2: 1024")
	private String indice2;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 3: 1024")
	private String indice3;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 4: 1024")
	private String indice4;
	
	private boolean obrigatorio;
	
	private boolean inativo;
	
	@Size(max = 2, message="Tamanho máximo para Código: 2")
	private String codigo;
	
	@Size(max = 2, message="Tamanho máximo para Código Excludente: 2")
	private String codigoExcludente;
	
	private int critico = -1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Equipe do Indicador Sast.")
	private Equipe equipe;
	
	@OneToMany(mappedBy="indicadorSast", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorAssociadoSast> indicadorAssociadoSasts;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndice0() {
		return indice0;
	}

	public void setIndice0(String indice0) {
		this.indice0 = indice0;
	}

	public String getIndice1() {
		return indice1;
	}

	public void setIndice1(String indice1) {
		this.indice1 = indice1;
	}

	public String getIndice2() {
		return indice2;
	}

	public void setIndice2(String indice2) {
		this.indice2 = indice2;
	}

	public String getIndice3() {
		return indice3;
	}

	public void setIndice3(String indice3) {
		this.indice3 = indice3;
	}

	public String getIndice4() {
		return indice4;
	}

	public void setIndice4(String indice4) {
		this.indice4 = indice4;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoExcludente() {
		return codigoExcludente;
	}

	public void setCodigoExcludente(String codigoExcludente) {
		this.codigoExcludente = codigoExcludente;
	}

	public int getCritico() {
		return critico;
	}

	public void setCritico(int critico) {
		this.critico = critico;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	public List<IndicadorAssociadoSast> getIndicadorAssociadoSasts() {
		return indicadorAssociadoSasts;
	}

	public void setIndicadorAssociadoSasts(List<IndicadorAssociadoSast> indicadorAssociadoSasts) {
		this.indicadorAssociadoSasts = indicadorAssociadoSasts;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
