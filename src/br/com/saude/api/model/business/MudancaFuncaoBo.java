package br.com.saude.api.model.business;
import java.util.ArrayList;
import java.util.List;


import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.MudancaFuncaoBuilder;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.creation.builder.example.MudancaFuncaoExampleBuilder;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.Atividade;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.model.persistence.MudancaFuncaoDao;
import br.com.saude.api.util.constant.StatusTarefa;
public class MudancaFuncaoBo
		extends GenericBo<MudancaFuncao, MudancaFuncaoFilter, MudancaFuncaoDao, MudancaFuncaoBuilder, MudancaFuncaoExampleBuilder> 
		
{

	private static MudancaFuncaoBo instance;

	private MudancaFuncaoBo() {
		super();
	}

	public static MudancaFuncaoBo getInstance() {
		if (instance == null)
			instance = new MudancaFuncaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadTarefas();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadTarefas();
		};

	}

	@Override
	public MudancaFuncao save(MudancaFuncao mudancaFuncao) throws Exception {
					
		@SuppressWarnings("unused")
		int qtdAlteracaoData = 0;
		long qtdNaoConcluida = mudancaFuncao.getTarefas().stream()
				.filter(t->!t.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA)).count();
		
		for(Tarefa tarefa : mudancaFuncao.getTarefas()) {
			if(tarefa.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA) && tarefa.getFim() == null) {
				if(tarefa.getEquipe().getAbreviacao().equals("MED") && qtdNaoConcluida > 0)
					throw new Exception("Para concluir a tarefa de Medicina, é necessário que as outras tarefas estejam concluídas.");
					
				tarefa.setFim(Helper.getNow());
				qtdAlteracaoData++;
			}
			
			if(!(tarefa.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA)))
				tarefa.setFim(null);
		}		
		
		return super.save(mudancaFuncao);
	}
	
	/**
	 * CRIAR MÉTODO aplicarAltecacoes
	 * 
	 * 		
		//getById NO EMPREGADO
		
		//SETAR APENAS GHE, GHEE, BASE (QUANDO DIFERENTE DE NULL)
		
		//SALVAR O EMPREGADO
		  
		//SETAR A DATA ATUAL NA APLICAÇÃO DA MUDANÇA DE FUNÇÃO, E SALVAR A MUDANÇA DE FUNÇÃO
	 * 
	 * */
	
	
	@Override
	public PagedList<MudancaFuncao> getList(MudancaFuncaoFilter filter) throws Exception {
		
		PagedList<MudancaFuncao> mudancaFuncoes = super.getList(filter,this.functionLoad);
		
		mudancaFuncoes.getList().forEach(x->{		
			String statusAux  =""; 					
			for(Tarefa tarefa : x.getTarefas()) 
				statusAux += tarefa.getEquipe().getAbreviacao() +"-" +tarefa.getStatus()+"/n";					
				x.setStatus(statusAux);
				x.setAbertura(x.getTarefas().get(0).getInicio());	
				x.setCliente(x.getTarefas().get(0).getCliente());
		});	
		return mudancaFuncoes;
	}

	@Override
	public List<MudancaFuncao> getSelectList(MudancaFuncaoFilter filter) throws Exception {
		return super.getSelectList(
				this.getDao().getList(this.getExampleBuilder(filter).exampleSelectList()).getList(),
				this.functionLoad);
	}

	@Override
	public MudancaFuncao getById(Object id) throws Exception {
		MudancaFuncao mudancaFuncao = getByEntity(getDao().getById(id), this.functionLoadAll);
		mudancaFuncao.setCliente(mudancaFuncao.getTarefas().get(0).getCliente());
		return mudancaFuncao ;
	}	
	
	public MudancaFuncao registrarMudancaFuncao(MudancaFuncao mudancaFuncao) throws Exception {
		
		if(mudancaFuncao.getCargo() == null && mudancaFuncao.getFuncao() == null &&
		   mudancaFuncao.getGhe() == null && mudancaFuncao.getGhee() == null &&
		   mudancaFuncao.getRegime() == null && mudancaFuncao.getGerencia() == null &&
		   mudancaFuncao.getBase() == null)				
			throw new Exception("Preencha pelo menos 1 dos campos");
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		mudancaFuncao.getTarefas().get(0).setServico(
				ServicoBo.getInstance().getById(mudancaFuncao.getTarefas().get(0).getServico().getId()));		
		
		for(Atividade atividade : mudancaFuncao.getTarefas().get(0).getServico().getAtividades()) {
			
		    Tarefa tarefa = TarefaBuilder.newInstance(mudancaFuncao.getTarefas().get(0)).getEntity();
			tarefa.setEquipe(atividade.getEquipe());
			tarefa.setInicio(Helper.getNow());
			tarefa.setAtualizacao(tarefa.getInicio());
			tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
			tarefas.add(tarefa);
		}
		mudancaFuncao.setTarefas(null);
		mudancaFuncao.setTarefas(tarefas);
	

		return super.save(mudancaFuncao);
		
	}
}
