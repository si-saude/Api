package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AtendimentoFilter extends GenericFilter {

	private FilaAtendimentoOcupacionalFilter filaAtendimentoOcupacional;
	private FilaEsperaOcupacionalFilter filaEsperaOcupacional;
	private TarefaFilter tarefa;
	private AsoFilter aso;
		
	public FilaAtendimentoOcupacionalFilter getFilaAtendimentoOcupacional() {
		return filaAtendimentoOcupacional;
	}
	public void setFilaAtendimentoOcupacional(FilaAtendimentoOcupacionalFilter filaAtendimentoOcupacional) {
		this.filaAtendimentoOcupacional = filaAtendimentoOcupacional;
	}
	public FilaEsperaOcupacionalFilter getFilaEsperaOcupacional() {
		return filaEsperaOcupacional;
	}
	public void setFilaEsperaOcupacional(FilaEsperaOcupacionalFilter filaEsperaOcupacional) {
		this.filaEsperaOcupacional = filaEsperaOcupacional;
	}
	public TarefaFilter getTarefa() {
		return tarefa;
	}
	public void setTarefa(TarefaFilter tarefa) {
		this.tarefa = tarefa;
	}
	public AsoFilter getAso() {
		return aso;
	}
	public void setAso(AsoFilter aso) {
		this.aso = aso;
	}
}
