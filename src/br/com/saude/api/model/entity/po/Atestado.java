package br.com.saude.api.model.entity.po;

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


@Entity
public class Atestado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(max = 16, message="Tamanho máximo para CID do Atestado: 16")
	private String cid;
	
	private int numeroDias;
	
	@Transient
	private Map<Integer,Integer> anexo;
	
	@Transient
	private String anexoBase64;
	
	@NotNull(message="É necessário informar a Tarefa do Atestado.")
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Tarefa tarefa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cat cat;
	
	private boolean impossibilidadeLocomocao;
	
	@NotNull(message="É necessário informar o Status do Atestado.")
	private String status;
	
	private boolean lancadoSap;
	
	private boolean atestadoFisicoRecebido;
	
	private boolean controleLicenca;
	
	private Date dataAgendamento;
	
	@NotNull(message="É necessário informar a Data da Solicitação.")
	private Date dataSolicitacao;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String Cid) {
		this.cid = Cid;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
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

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public boolean isImpossibilidadeLocomocao() {
		return impossibilidadeLocomocao;
	}

	public void setImpossibilidadeLocomocao(boolean impossibilidadeLocomocao) {
		this.impossibilidadeLocomocao = impossibilidadeLocomocao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isLancadoSap() {
		return lancadoSap;
	}

	public void setLancadoSap(boolean lancadoSap) {
		this.lancadoSap = lancadoSap;
	}

	public boolean isAtestadoFisicoRecebido() {
		return atestadoFisicoRecebido;
	}

	public void setAtestadoFisicoRecebido(boolean atestadoFisicoRecebido) {
		this.atestadoFisicoRecebido = atestadoFisicoRecebido;
	}

	public boolean isControleLicenca() {
		return controleLicenca;
	}

	public void setControleLicenca(boolean controleLicenca) {
		this.controleLicenca = controleLicenca;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
