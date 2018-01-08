package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Tarefa;

public class TarefaBuilder extends GenericEntityBuilder<Tarefa, TarefaFilter> {

	public static TarefaBuilder newInstance(Tarefa tarefa) {
		return new TarefaBuilder(tarefa);
	}
	
	public static TarefaBuilder newInstance(List<Tarefa> tarefas) {
		return new TarefaBuilder(tarefas);
	}
	
	private TarefaBuilder(Tarefa tarefa) {
		super(tarefa);
	}
	
	private TarefaBuilder(List<Tarefa> tarefas) {
		super(tarefas);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Tarefa clone(Tarefa tarefa) {
		
		Tarefa newTarefa = new Tarefa();
		newTarefa.setId(tarefa.getId());
		newTarefa.setHorario(tarefa.getHorario());
		newTarefa.setAtualizacao(tarefa.getAtualizacao());
		newTarefa.setStatus(tarefa.getStatus());
		newTarefa.setVersion(tarefa.getVersion());
		
		if(tarefa.getCliente() != null)
			newTarefa.setCliente(EmpregadoBuilder.newInstance(tarefa.getCliente())
					.getEntity());
		
		if(tarefa.getEquipe() != null)
			newTarefa.setEquipe(EquipeBuilder.newInstance(tarefa.getEquipe()).getEntity());
		
		if(tarefa.getResponsavel() != null)
			newTarefa.setResponsavel(ProfissionalBuilder.newInstance(tarefa.getResponsavel())
					.getEntity());
		
		if(tarefa.getServico() != null)
			newTarefa.setServico(ServicoBuilder.newInstance(tarefa.getServico()).getEntity());
		
		return newTarefa;
	}

	@Override
	public Tarefa cloneFromFilter(TarefaFilter filter) {
		return null;
	}
}
