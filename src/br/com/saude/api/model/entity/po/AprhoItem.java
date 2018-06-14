package br.com.saude.api.model.entity.po;

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
public class AprhoItem {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar o Aprho.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Aprho aprho;
	
	@NotNull(message="� necess�rio informar o Agente de Risco.")
	@ManyToOne(fetch=FetchType.EAGER)
	private AgenteRisco agenteRisco;
	
	@NotNull(message="� necess�rio informar o Agente de Risco.")
	@ManyToOne(fetch=FetchType.EAGER)
	private PossivelDanoSaude possivelDanoSaude;
	
	@NotNull(message="� necess�rio informar a Categoria do Risco.")
	@ManyToOne(fetch=FetchType.EAGER)
	private CategoriaRisco categoriaRisco;	
	
	@NotNull(message="� necess�rio informar o Meio de Propaga��o.")
	@Size(max = 128, message="Tamanho m�ximo para Meio de Propaga��o: 128")
	private String meioPropagacao;	
	
	@NotNull(message="� necess�rio informar a Medida de Controle.")
	@Size(max = 128, message="Tamanho m�ximo para Medida de Controle: 128")
	private String medidaControle;	
	
	@NotNull(message="� necess�rio informar a Fonte Geradora.")
	@ManyToOne(fetch=FetchType.EAGER)
	private FonteGeradora fonteGeradora;	
	
	@NotNull(message="� necess�rio informar a Atividade.")
	@Size(max = 128, message="Tamanho m�ximo para Atividade: 128")
	private String atividade;	
	
	@NotNull(message="� necess�rio informar o Local.")
	@Size(max = 128, message="Tamanho m�ximo para Local: 128")
	private String local;

	@NotNull(message="� necess�rio informar a Frequ�ncia.")
	@Size(max = 32, message="Tamanho m�ximo para Frequ�ncia: 32")
	private String frequencia;	
	
	@NotNull(message="� necess�rio informar a Dura��o.")
	@Size(max = 8, message="Tamanho m�ximo para Dura��o: 8")
	private String duracao;
	
	@Version
	private long version;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	public PossivelDanoSaude getPossivelDanoSaude() {
		return possivelDanoSaude;
	}

	public void setPossivelDanoSaude(PossivelDanoSaude possivelDanoSaude) {
		this.possivelDanoSaude = possivelDanoSaude;
	}

	public CategoriaRisco getCategoriaRisco() {
		return categoriaRisco;
	}

	public void setCategoriaRisco(CategoriaRisco categoriaRisco) {
		this.categoriaRisco = categoriaRisco;
	}

	public String getMeioPropagacao() {
		return meioPropagacao;
	}

	public void setMeioPropagacao(String meioPropagacao) {
		this.meioPropagacao = meioPropagacao;
	}

	public String getMedidaControle() {
		return medidaControle;
	}

	public void setMedidaControle(String medidaControle) {
		this.medidaControle = medidaControle;
	}

	public FonteGeradora getFonteGeradora() {
		return fonteGeradora;
	}

	public void setFonteGeradora(FonteGeradora fonteGeradora) {
		this.fonteGeradora = fonteGeradora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aprho getAprho() {
		return aprho;
	}

	public void setAprho(Aprho aprho) {
		this.aprho = aprho;
	}

	public AgenteRisco getAgenteRisco() {
		return agenteRisco;
	}

	public void setAgenteRisco(AgenteRisco agenteRisco) {
		this.agenteRisco = agenteRisco;
	}
	
}
