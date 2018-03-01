package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.Tarefa;

public class AtendimentoFactory {

	private Atendimento atendimento;
	
	public static AtendimentoFactory newInstance(){
		return new AtendimentoFactory();
	}
	
	private AtendimentoFactory() {
		this.atendimento = new Atendimento();
	}
	
	public AtendimentoFactory filaEspera(FilaEsperaOcupacional fila) {
		this.atendimento.setFilaEsperaOcupacional(fila);
		return this;
	}
	
	public AtendimentoFactory filaAtendimento(FilaAtendimentoOcupacional fila) {
		this.atendimento.setFilaAtendimentoOcupacional(fila);
		return this;
	}
	
	public AtendimentoFactory tarefa(Tarefa tarefa) {
		this.atendimento.setTarefa(tarefa);
		return this;
	}
	
	public Atendimento get() {
		return this.atendimento;
	}
}
