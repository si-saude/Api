package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class TarefaFilter extends GenericFilter {

	private DateFilter inicio;
	private DateFilter fim;
	private DateFilter atualizacao;
	private ServicoFilter servico;
	private EmpregadoFilter cliente;
	private ProfissionalFilter responsavel;
	private EquipeFilter equipe;
	private String status;
	
	public DateFilter getInicio() {
		return inicio;
	}
	public void setInicio(DateFilter inicio) {
		this.inicio = inicio;
	}
	public DateFilter getFim() {
		return fim;
	}
	public void setFim(DateFilter fim) {
		this.fim = fim;
	}
	public DateFilter getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(DateFilter atualizacao) {
		this.atualizacao = atualizacao;
	}
	public ServicoFilter getServico() {
		return servico;
	}
	public void setServico(ServicoFilter servico) {
		this.servico = servico;
	}
	public EmpregadoFilter getCliente() {
		return cliente;
	}
	public void setCliente(EmpregadoFilter cliente) {
		this.cliente = cliente;
	}
	public ProfissionalFilter getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(ProfissionalFilter responsavel) {
		this.responsavel = responsavel;
	}
	public EquipeFilter getEquipe() {
		return equipe;
	}
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
