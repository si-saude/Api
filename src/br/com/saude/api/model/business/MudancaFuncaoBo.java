package br.com.saude.api.model.business;
import java.util.ArrayList;
import java.util.List;


import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.MudancaFuncaoBuilder;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.creation.builder.example.MudancaFuncaoExampleBuilder;
import br.com.saude.api.model.entity.dto.MudancaFuncaoDto;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.Atividade;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.model.persistence.MudancaFuncaoDao;
import br.com.saude.api.model.persistence.report.MudancaFuncaoReport;
import br.com.saude.api.util.constant.StatusTarefa;

public class MudancaFuncaoBo
		extends GenericBo<MudancaFuncao, MudancaFuncaoFilter, MudancaFuncaoDao, MudancaFuncaoBuilder, MudancaFuncaoExampleBuilder>
		implements GenericReportBo<MudancaFuncaoDto>
		
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
			return builder.loadTarefas().loadInstalacoes().loadLoadMonitoramentos().loadLoadGhe();
		};

	}

	@Override
	public MudancaFuncao save(MudancaFuncao mudancaFuncao) throws Exception {
					
		@SuppressWarnings("unused")
		int qtdAlteracaoData = 0;
		long qtdNaoConcluida = mudancaFuncao.getTarefas().stream()
				.filter(t->!t.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA)).count();
		
		
		mudancaFuncao.setCliente(EmpregadoBo.getInstance().getById(mudancaFuncao.getTarefas().get(0).getCliente().getId()));
		boolean temMudanca = existeAlteracaoMudancaFuncao(mudancaFuncao);
		
		
		for(Tarefa tarefa : mudancaFuncao.getTarefas()) {
			if(tarefa.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA) && tarefa.getFim() == null) {
				
				if(tarefa.getEquipe().getAbreviacao().equals("MED")) {
					if(qtdNaoConcluida > 0)
						throw new Exception("Para concluir a tarefa de Medicina, é necessário que as outras tarefas estejam concluídas.");
					else if(temMudanca)
						throw new Exception("A tarefa de Medicina deve ser concluída por meio de um Atendimento Ocupacional de Mudança de Função.");
				}
					
				tarefa.setFim(Helper.getNow());
			}
			
			if(!(tarefa.getStatus().equals(StatusTarefa.getInstance().CONCLUIDA)))
				tarefa.setFim(null);
		}		
		
		return super.save(mudancaFuncao);
	}
	
	public String solicitarConvocacao(MudancaFuncao mudancaFuncao) throws Exception {
		if(!existeAlteracaoMudancaFuncao(mudancaFuncao)) {
			throw new Exception("As mudanças já foram aplicadas");		
		}
		return "";
	}
	
	private boolean existeAlteracaoMudancaFuncao(MudancaFuncao mudancaFuncao) throws Exception {
	
		Empregado empregado = EmpregadoBo.getInstance().getById(mudancaFuncao.getTarefas().get(0).getCliente().getId());
		
		if( mudancaFuncao.getEnfase() != null && 
				(empregado.getEnfase() != null && mudancaFuncao.getEnfase().getId() != empregado.getEnfase().getId()
				|| (empregado.getEnfase() == null )))			
			return true;
		if( mudancaFuncao.getFuncao() != null && 
				(empregado.getFuncao() != null &&  mudancaFuncao.getFuncao().getId() != empregado.getFuncao().getId())
				|| (empregado.getFuncao() == null))			
			return true;
		if( mudancaFuncao.getGhe() != null && 
				(empregado.getGhe() != null &&  mudancaFuncao.getGhe().getId() != empregado.getGhe().getId())
				|| (empregado.getGhe() == null))			
			return true;
		if( mudancaFuncao.getGhee() != null &&
				(empregado.getGhee() != null &&  mudancaFuncao.getGhee().getId() != empregado.getGhee().getId())
				||(empregado.getGhee() == null))			
			return true;
		if( mudancaFuncao.getRegime() != null &&
				(empregado.getRegime() != null &&  mudancaFuncao.getRegime().getId() != empregado.getRegime().getId())
				|| (empregado.getRegime() == null))			
			return true;
		if( mudancaFuncao.getGerencia() != null &&
				(empregado.getGerencia() != null &&  mudancaFuncao.getGerencia().getId() != empregado.getGerencia().getId())
				||(empregado.getGerencia() == null))			
			return true;
		if( mudancaFuncao.getBase() != null &&
				(empregado.getBase() != null &&  mudancaFuncao.getBase().getId() != empregado.getBase().getId())
				||(empregado.getBase() == null))			
			return true;
		if(modificacaoIntalacoes(mudancaFuncao, empregado)) 
			return true;
		if(modificacaoGruposMonitoramento(mudancaFuncao, empregado))
			return true;
		
		return false;
	}
	
	private boolean modificacaoIntalacoes(MudancaFuncao mudancaFuncao, Empregado empregado) {
		
		if(mudancaFuncao.getInstalacoes() != null) {
			for(int x = 0; x < mudancaFuncao.getInstalacoes().size(); x++) {
				int y = x;
				if(empregado.getInstalacoes() == null ||empregado.getInstalacoes().stream().filter(i-> i.getId() == mudancaFuncao.getInstalacoes().get(y).getId()).count() == 0)
					return true;
			}
		}
		if(empregado.getInstalacoes() != null) {
			for(int x = 0; x < empregado.getInstalacoes().size(); x++) {
				int y = x;
				if(mudancaFuncao.getInstalacoes() == null ||mudancaFuncao.getInstalacoes().stream().filter(i-> i.getId() == empregado.getInstalacoes().get(y).getId()).count() == 0)
					return true;
			}
		}
		return false;
	}
	
	private boolean modificacaoGruposMonitoramento(MudancaFuncao mudancaFuncao, Empregado empregado) {
		
		if(mudancaFuncao.getGrupoMonitoramentos() != null) {
			for(int x = 0; x < mudancaFuncao.getGrupoMonitoramentos().size(); x++) {
				int y = x;
				if(empregado.getGrupoMonitoramentos() == null ||empregado.getGrupoMonitoramentos().stream().filter(i-> i.getId() == mudancaFuncao.getGrupoMonitoramentos().get(y).getId()).count() == 0)
					return true;
			}
		}
		if(empregado.getGrupoMonitoramentos() != null) {
			for(int x = 0; x < empregado.getGrupoMonitoramentos().size(); x++) {
				int y = x;
				if(mudancaFuncao.getGrupoMonitoramentos() == null ||mudancaFuncao.getGrupoMonitoramentos().stream().filter(i-> i.getId() == empregado.getGrupoMonitoramentos().get(y).getId()).count() == 0)
					return true;
			}
		}
		return false;
	}
	
	public Empregado aplicarAlteracoes(MudancaFuncao mudancaFuncao) throws Exception {
		
		if(existeAlteracaoMudancaFuncao(mudancaFuncao)) {
			   mudancaFuncao.setCliente(EmpregadoBo.getInstance().getById(mudancaFuncao.getTarefas().get(0).getCliente().getId()));
			   
			if(mudancaFuncao.getEnfase() != null)
			   mudancaFuncao.getCliente().setEnfase(mudancaFuncao.getEnfase());
			
			if(mudancaFuncao.getFuncao() != null)
			   mudancaFuncao.getCliente().setFuncao(mudancaFuncao.getFuncao());
			
			if(mudancaFuncao.getRegime() != null)
			   mudancaFuncao.getCliente().setRegime(mudancaFuncao.getRegime());
			
			if(mudancaFuncao.getGerencia() != null)
			   mudancaFuncao.getCliente().setGerencia(mudancaFuncao.getGerencia());
			
			if(mudancaFuncao.getBase() != null)
			   mudancaFuncao.getCliente().setBase(mudancaFuncao.getBase());		
			
			if( mudancaFuncao.getGhee() != null) 
				mudancaFuncao.getCliente().setGhee(mudancaFuncao.getGhee());	
			
			if( mudancaFuncao.getGhe() != null) 
				mudancaFuncao.getCliente().setGhe(mudancaFuncao.getGhe());	
			
			mudancaFuncao.getCliente().setInstalacoes(mudancaFuncao.getInstalacoes());
			
			mudancaFuncao.getCliente().setGrupoMonitoramentos(mudancaFuncao.getGrupoMonitoramentos());
			
		}else
			throw new Exception("As mudanças já foram aplicadas");		
		 
		 return EmpregadoBo.getInstance().save(mudancaFuncao.getCliente());
	}	
	
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
		
		if(mudancaFuncao.getEnfase() == null && mudancaFuncao.getFuncao() == null &&
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
	
	public List<MudancaFuncaoDto> getMudancaFuncoes() throws Exception{
		return MudancaFuncaoReport.getInstance().getMudancaFuncoes();
	}
}
