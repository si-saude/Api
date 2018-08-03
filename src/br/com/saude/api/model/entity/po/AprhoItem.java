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
	
	@NotNull(message="� necess�rio informar o Possivel Dana � Sa�de.")
	@ManyToOne(fetch=FetchType.EAGER)
	private PossivelDanoSaude possivelDanoSaude;

	@ManyToOne(fetch=FetchType.EAGER)
	private CategoriaRisco categoriaRisco;	
	
	@Size(max = 128, message="Tamanho m�ximo para Meio de Propaga��o: 128")
	private String meioPropagacao;	
	
	@Size(max = 128, message="Tamanho m�ximo para Medida de Controle: 128")
	private String medidaControle;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private FonteGeradora fonteGeradora;	
	
	@NotNull(message="� necess�rio informar a Atividade.")
	@Size(max = 2048, message="Tamanho m�ximo para Atividade: 128")
	private String atividade;	
	
	@NotNull(message="� necess�rio informar o Local.")
	@Size(max = 128, message="Tamanho m�ximo para Local: 128")
	private String local;

	@NotNull(message="� necess�rio informar a Frequ�ncia.")
	@Size(max = 32, message="Tamanho m�ximo para Frequ�ncia: 32")
	private String frequencia;	
	
	@NotNull(message="� necess�rio informar a Dura��o.")
	@Size(max = 32, message="Tamanho m�ximo para Dura��o: 32")
	private String duracao;
	
	@NotNull(message="� necess�rio informar a Avalia��o de Efic�cia.")
	@Size(max = 32, message="Tamanho m�ximo para Avalia��o de Efic�cia: 32")
	private String avaliacaoEficacia;

	@Version
	private long version;
	
	public String getAvaliacaoEficacia() {
		return avaliacaoEficacia;
	}

	public void setAvaliacaoEficacia(String avaliacaoEficacia) {
		this.avaliacaoEficacia = avaliacaoEficacia;
	}
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
