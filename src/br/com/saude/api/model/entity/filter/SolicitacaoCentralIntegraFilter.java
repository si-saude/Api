package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class SolicitacaoCentralIntegraFilter extends GenericFilter { 
	private TipoSolicitacaoFilter tipoSolicitacao;
	private String status;
	private DateFilter abertura;
	private DateFilter prazo;
	private TarefaFilter tarefa;
	
	public TipoSolicitacaoFilter getTipoSolicitacao() {
		return tipoSolicitacao;
	}
	public void setTipoSolicitacao(TipoSolicitacaoFilter tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DateFilter getAbertura() {
		return abertura;
	}
	public void setAbertura(DateFilter abertura) {
		this.abertura = abertura;
	}
	public DateFilter getPrazo() {
		return prazo;
	}
	public void setPrazo(DateFilter prazo) {
		this.prazo = prazo;
	}
	public TarefaFilter getTarefa() {
		return tarefa;
	}
	public void setTarefa(TarefaFilter tarefa) {
		this.tarefa = tarefa;
	}
	
}
