package br.com.saude.api.model.entity.po;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.saude.api.generic.Helper;

@Entity
public class SolicitacaoCentralIntegra {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Descrição da Solicitação.")
	@Size(max = 2048, message="Tamanho máximo para Descrição da Solicitação: 2048")
	private String descricao;

	@Size(max = 2048, message="Tamanho máximo para Observação da Solicitação: 2048")
	private String observacao;
	
	@NotNull(message="É necessário informar o Tipo da Solicitação da Solicitação.")
	@ManyToOne(fetch=FetchType.EAGER)
	private TipoSolicitacao tipoSolicitacao;
	
	@NotNull(message="É necessário informar a Tarefa da Solicitação.")
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Tarefa tarefa;

	@NotNull(message="É necessário informar o Status da Solicitação.")
	@Size(max = 32, message="Tamanho máximo para Status da Solicitação: 32")
	private String status;
	
	private int tempoEstimado;
	
	private int tempoGasto;
	
	private Date prazo;
	
	@NotNull(message="É necessário informar a Abertura da Solicitação.")
	private Date abertura;
	
	private boolean concluido;
	
	@Transient
	private Map<Integer,Integer> anexo;
	
	@Transient
	private String anexoBase64;
	
	@Transient
	private boolean atrasado;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public int getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(int tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public Map<Integer, Integer> getAnexo() {
		return anexo;
	}

	public void setAnexo(Map<Integer, Integer> anexo) {
		this.anexo = anexo;
	}

	public String getAnexoBase64() {
		return anexoBase64;
	}

	public void setAnexoBase64(String anexoBase64) {
		this.anexoBase64 = anexoBase64;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public boolean isAtrasado( ) {
		if ( this.prazo != null ) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(this.prazo);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			
			if ( calendar.getTime().before( Helper.getToday() ) ) {
				atrasado = true;	
			} else atrasado = false;
		}
		
		return atrasado;
	}
	
	public void setAtrasado( boolean atrasado ) {
		this.atrasado = atrasado;
	}

}
