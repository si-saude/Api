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
public class ResultadoExame {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar o Empregado/Convoca��o do Resultado.")
	@ManyToOne(fetch=FetchType.LAZY)
	private EmpregadoConvocacao empregadoConvocacao;
	
	@NotNull(message="� necess�rio informar o Exame do Resultado.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Exame exame;
	
	@OneToMany(mappedBy="resultadoExame", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ItemResultadoExame> itemResultadoExames;
	
//	@NotNull(message="� necess�rio informar a Data do Resultado.")
	private Date data;
	
	private Date dataRecebimento;
	
	private boolean conforme;
	
	@Size(max = 64, message="Tamanho m�ximo para Tipo: 64")
	private String tipo;
	
	@Size(max = 64, message="Tamanho m�ximo para Tipo: 64")
	private String acao;
	
	@Size(max = 64, message="Tamanho m�ximo para Tipo: 64")
	private String local;
	
	private boolean particular;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmpregadoConvocacao getEmpregadoConvocacao() {
		return empregadoConvocacao;
	}

	public void setEmpregadoConvocacao(EmpregadoConvocacao empregadoConvocacao) {
		this.empregadoConvocacao = empregadoConvocacao;
	}

	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	public List<ItemResultadoExame> getItemResultadoExames() {
		return itemResultadoExames;
	}

	public void setItemResultadoExames(List<ItemResultadoExame> itemResultadoExames) {
		this.itemResultadoExames = itemResultadoExames;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public boolean isParticular() {
		return particular;
	}

	public void setParticular(boolean particular) {
		this.particular = particular;
	}
	
}
