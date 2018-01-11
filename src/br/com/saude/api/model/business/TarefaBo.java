package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.creation.builder.example.TarefaExampleBuilder;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.TarefaDao;

public class TarefaBo 
extends GenericBo<Tarefa, TarefaFilter, TarefaDao, TarefaBuilder, TarefaExampleBuilder> {

	private static TarefaBo instance;
	
	private TarefaBo() {
		super();
	}
	
	public static TarefaBo getInstance() {
		if(instance == null)
			instance = new TarefaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
