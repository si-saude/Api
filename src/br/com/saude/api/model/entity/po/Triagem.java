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
public class Triagem {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Indicador Sast da Triagem.")
	private IndicadorSast indicadorSast;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar o Atendimento da Triagem.")
	private Atendimento atendimento;
	
	private int indice = -1;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private RiscoEmpregado riscoEmpregado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Diagnostico diagnostico;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Intervencao intervencao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipeAbordagem;
	
	@Size(max = 16, message="Tamanho máximo para Sugestão de Prazo para Início: 16")
	private String prazo;
	
	@Size(max = 2048, message="Tamanho máximo para Justificativa: 2048")
	private String justificativa;
	
	@OneToMany(mappedBy="triagem", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Acao> acoes;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IndicadorSast getIndicadorSast() {
		return indicadorSast;
	}

	public void setIndicadorSast(IndicadorSast indicadorSast) {
		this.indicadorSast = indicadorSast;
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public RiscoEmpregado getRiscoEmpregado() {
		return riscoEmpregado;
	}

	public void setRiscoEmpregado(RiscoEmpregado riscoEmpregado) {
		this.riscoEmpregado = riscoEmpregado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Intervencao getIntervencao() {
		return intervencao;
	}

	public void setIntervencao(Intervencao intervencao) {
		this.intervencao = intervencao;
	}

	public Equipe getEquipeAbordagem() {
		return equipeAbordagem;
	}

	public void setEquipeAbordagem(Equipe equipeAbordagem) {
		this.equipeAbordagem = equipeAbordagem;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}	
}
