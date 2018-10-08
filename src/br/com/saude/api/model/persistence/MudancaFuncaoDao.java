package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Instalacao;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.model.entity.po.Tarefa;

public class MudancaFuncaoDao extends GenericDao<MudancaFuncao>  {
	
	private static MudancaFuncaoDao instance;
	
	private MudancaFuncaoDao(){
		super();
	}
	
	public static MudancaFuncaoDao getInstance() {
		if(instance == null)
			instance = new MudancaFuncaoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = mudancaFuncao -> {
			mudancaFuncao = loadTarefas(mudancaFuncao);		
			mudancaFuncao = loadInstalacoes(mudancaFuncao);
			return mudancaFuncao;
		};
	
	}	

	@Override
	public MudancaFuncao getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}

	@Override
	public PagedList<MudancaFuncao> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	private MudancaFuncao loadTarefas(MudancaFuncao mudancaFuncao)
	{
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(mudancaFuncao.getTarefas()!=null) {
			mudancaFuncao.getTarefas().forEach(a->{				
				tarefas.add((Tarefa) Hibernate.unproxy(a));				
			});
			mudancaFuncao.setTarefas(tarefas);
		}
		return mudancaFuncao;
	}
	
	private MudancaFuncao loadInstalacoes(MudancaFuncao mudancaFuncao)
	{
		List<Instalacao> instalacoes = new ArrayList<Instalacao>();
		if(mudancaFuncao.getInstalacoes()!=null) {
			mudancaFuncao.getInstalacoes().forEach(a->{				
				instalacoes.add((Instalacao) Hibernate.unproxy(a));				
			});
			mudancaFuncao.setInstalacoes(instalacoes);
		}
		return mudancaFuncao;
	}
	
}
