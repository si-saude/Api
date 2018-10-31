package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Exame {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Código do Exame.")
	@Size(max = 10, message="Tamanho máximo para Código do Exame: 10")
	@Column(unique=true)
	private String codigo;
	
	@NotNull(message="É necessário informar a Descrição do Exame.")
	@Size(max = 150, message="Tamanho máximo para Descrição do Exame: 150")
	private String descricao;
	
	@OneToMany(mappedBy="exame", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CampoExame> campoExames;
	
	private int ordem;
	
	@Version
	private long version;
	
	@Transient
	private boolean exigeRelatorio;
	
	private boolean opcional;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public boolean isExigeRelatorio() {
		return exigeRelatorio;
	}
	public void setExigeRelatorio(boolean exigeRelatorio) {
		this.exigeRelatorio = exigeRelatorio;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public List<CampoExame> getCampoExames() {
		return campoExames;
	}
	public void setCampoExames(List<CampoExame> campoExames) {
		this.campoExames = campoExames;
	}
	
	public boolean isOpcional() {
		return opcional;
	}
	public void setOpcional(boolean opcional) {
		this.opcional = opcional;
	}
	@Override
	public boolean equals(Object exame) {
		return ((Exame)exame).id == this.id && this.id > 0;
	}
}
