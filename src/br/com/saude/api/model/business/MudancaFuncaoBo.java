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

	}

	@Override
	public PagedList<MudancaFuncao> getList(MudancaFuncaoFilter filter) throws Exception {
		return super.getList(getDao().getList(getExampleBuilder(filter).example()), this.functionLoad);
	}

	@Override
	public List<MudancaFuncao> getSelectList(MudancaFuncaoFilter filter) throws Exception {
		return super.getSelectList(
				this.getDao().getList(this.getExampleBuilder(filter).exampleSelectList()).getList(),
				this.functionLoad);
	}

	@Override
	public MudancaFuncao getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id), this.functionLoadAll);
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
			tarefa.setAtualizacao(Helper.getNow());
			tarefa.setEquipe(atividade.getEquipe());
			tarefa.setInicio(Helper.getNow());
			tarefa.setFim(Helper.getNow());
			tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
			tarefas.add(tarefa);
		}
		mudancaFuncao.setTarefas(null);
		mudancaFuncao.setTarefas(tarefas);
	

		return super.save(mudancaFuncao);
		
	}
}
