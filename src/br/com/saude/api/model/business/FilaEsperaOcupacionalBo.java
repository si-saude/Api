package br.com.saude.api.model.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.FilaEsperaOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.FilaEsperaOcupacionalExampleBuilder;
import br.com.saude.api.model.creation.builder.example.TarefaExampleBuilder;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.FilaAtendimentoOcupacionalFilter;
import br.com.saude.api.model.entity.filter.FilaEsperaOcupacionalFilter;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.RegraAtendimento;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipe;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.FilaEsperaOcupacionalDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;

public class FilaEsperaOcupacionalBo 
	extends GenericBo<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter, 
					FilaEsperaOcupacionalDao, FilaEsperaOcupacionalBuilder, 
					FilaEsperaOcupacionalExampleBuilder> {

	private static FilaEsperaOcupacionalBo instance;
	
	private FilaEsperaOcupacionalBo() {
		super();
	}
	
	public static FilaEsperaOcupacionalBo getInstance() {
		if(instance == null)
			instance = new FilaEsperaOcupacionalBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadLocalizacao();
		};
	}
	
	@Override
	public FilaEsperaOcupacional getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<FilaEsperaOcupacional> getListAll(FilaEsperaOcupacionalFilter filter) throws  Exception {
		return super.getList(filter,this.functionLoadAll);
	}
	
	public String checkIn(FilaEsperaOcupacional fila) throws Exception {
		// 1 - VERIFICAR SE A LOCALIZAÇÃO FOI INFORMADA
		if(fila.getLocalizacao() == null)
			throw new Exception("É necessário informar a Localização para realizar o Check-in.");
		
		// 2 - VERIFICAR SE O EMPREGADO EXISTE
		if(fila.getEmpregado() != null && fila.getEmpregado().getPessoa() != null) {
			EmpregadoFilter filter = new EmpregadoFilter();
			filter.setPageNumber(1);
			filter.setPageSize(1);
			filter.setChave(fila.getEmpregado().getChave());
			filter.setMatricula(fila.getEmpregado().getMatricula());
			
			filter.setPessoa(new PessoaFilter());
			filter.getPessoa().setCpf(fila.getEmpregado().getPessoa().getCpf());
			
			filter.getPessoa().setDataNascimento(new DateFilter());
			filter.getPessoa().getDataNascimento().setTypeFilter(TypeFilter.IGUAL);
			filter.getPessoa().getDataNascimento()
				.setInicio(fila.getEmpregado().getPessoa().getDataNascimento());
			
			PagedList<Empregado> empregados = EmpregadoBo.getInstance().getList(filter);
			
			if(empregados.getTotal() > 0)
				fila.setEmpregado(empregados.getList().get(0));
			else
				throw new Exception("Não foi possível encontrar o cadastro do Empregado.");
		}else
			throw new Exception("Não foi possível encontrar o cadastro do Empregado.");
		
		// 3 - VERIFICAR SE JÁ FOI FEITO CHECK-IN
		Date today = Helper.getToday();
		
		FilaEsperaOcupacionalFilter filaFilter = new FilaEsperaOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(1);
		filaFilter.setEmpregado(new EmpregadoFilter());
		filaFilter.getEmpregado().setMatricula(fila.getEmpregado().getMatricula());
		filaFilter.setHorarioCheckin(new DateFilter());
		filaFilter.getHorarioCheckin().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filaFilter.getHorarioCheckin().setInicio(today);
		
		PagedList<FilaEsperaOcupacional> filaEsperaOcupacionais = 
				getList(filaFilter, this.functionLoadAll);
		
		if(filaEsperaOcupacionais.getTotal() > 0)
			throw new Exception("Já foi feito o Check-in deste Empregado na Localização: "
					+filaEsperaOcupacionais.getList().get(0).getLocalizacao().getNome()+".");
		
		// 4 - VERIFICAR SE EXISTE AGENDAMENTO (TAREFA) PARA ESTE EMPREGADO (CLIENTE),
		// CUJO GRUPO DO SERVIÇO SEJA ATENDIMENTO OCUPACIONAL, E STATUS DIFERENTE DE
		// CONCLUÍDO E CANCELADO, E A DATA DA TAREFA ESTÁ ENTRE O DIA ATUAL E O SEGUINTE
		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(1);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setMatricula(fila.getEmpregado().getMatricula());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);
		
		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
		tarefaFilter.getInicio().setInicio(today);
		tarefaFilter.getInicio().setFim(calendar.getTime());
		
		PagedList<Tarefa> tarefas = TarefaBo.getInstance()
			.getList(TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluidoCancelado());
		
		if(tarefas.getTotal() == 0)
			throw new Exception("Não há agendamento para este Empregado.");
		
		// 5 - INSTANCIAR FILA
		fila.setHorarioCheckin(Helper.getNow());
		fila.setAtualizacao(fila.getHorarioCheckin());
		fila.setTempoEspera(0);
		fila.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		fila.setServico(tarefas.getList().get(0).getServico());
		
		// 6 - INSERIR NO BANCO
		getDao().save(fila);
		
		return "Empregado "+fila.getEmpregado().getPessoa().getNome()+" inserido na fila de espera. "+
				"Favor aguardar chamada.";
	}
	
	public List<Atendimento> refresh(Atendimento at) throws Exception {
		
		RegraAtendimento regra = at.getRegra();		
		
		// 1 - VERIFICAR SE A REGRA FOI INFORMADA E RECARREGAR COMPLETA
		// ORDENADA PELO ID
		if(regra == null || regra.getId() == 0)
			throw new Exception("É necessário informar a Regra de Atendimento.");
		
		regra = RegraAtendimentoBo.getInstance().getById(regra.getId());
		regra.getRegraAtendimentoEquipes().sort(new Comparator<RegraAtendimentoEquipe>() {
			public int compare(RegraAtendimentoEquipe o1, RegraAtendimentoEquipe o2) {
				return new Integer(o1.getId()).compareTo(o2.getId()) ;
			}
		});
		
		// 2 - VERIFICAR SE A LOCALIZAÇÃO FOI INFORMADA
		if(at.getFilaEsperaOcupacional() == null || 
				at.getFilaEsperaOcupacional().getLocalizacao() == null || 
				at.getFilaEsperaOcupacional().getLocalizacao().getId() == 0)
			throw new Exception("É necessário informar a Localização.");
		
		Localizacao localizacao = at.getFilaEsperaOcupacional().getLocalizacao();
		
		// 3 - OBTER A FILA DE ESPERA, ORDENADA PELA ATUALIZACAO
		// (AGUARDANDO, PENDENTE)
		Date today = Helper.getToday();
		
		FilaEsperaOcupacionalFilter filter = new FilaEsperaOcupacionalFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setHorarioCheckin(new DateFilter());
		filter.getHorarioCheckin().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filter.getHorarioCheckin().setInicio(today);
		filter.setLocalizacao(new LocalizacaoFilter());
		filter.getLocalizacao().setId(localizacao.getId());
		filter.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		
		PagedList<FilaEsperaOcupacional> fila = getListAll(filter);
		
		// 4 - OBTER A LISTA DE ATENDIMENTO
		AtendimentoFilter atendimentofilter = new AtendimentoFilter();
		atendimentofilter.setPageNumber(1);
		atendimentofilter.setPageSize(Integer.MAX_VALUE);
		atendimentofilter.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
		atendimentofilter.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
		atendimentofilter.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		atendimentofilter.getFilaAtendimentoOcupacional().getInicio().setInicio(today);
		atendimentofilter.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO);
		atendimentofilter.getFilaAtendimentoOcupacional().setLocalizacao(new LocalizacaoFilter());
		atendimentofilter.getFilaAtendimentoOcupacional().getLocalizacao().setId(localizacao.getId());
		
		PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(atendimentofilter);
		
		if(atendimentos.getTotal() == 0)
			atendimentos.setList(new ArrayList<Atendimento>());
		
		if(fila.getTotal() > 0) {
			fila.getList().sort(new Comparator<FilaEsperaOcupacional>() {
				public int compare(FilaEsperaOcupacional arg0, FilaEsperaOcupacional arg1) {
					return arg0.getAtualizacao().compareTo(arg1.getAtualizacao());
				}
			});
			
			// 5 - OBTER A FILA DE ATENDIMENTO (DISPONÍVEL)
			FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
			filaFilter.setPageNumber(1);
			filaFilter.setPageSize(Integer.MAX_VALUE);
			filaFilter.setInicio(new DateFilter());
			filaFilter.getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
			filaFilter.getInicio().setInicio(today);
			filaFilter.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
			filaFilter.setLocalizacao(new LocalizacaoFilter());
			filaFilter.getLocalizacao().setId(localizacao.getId());
			
			PagedList<FilaAtendimentoOcupacional> filaAtendimentos = 
					FilaAtendimentoOcupacionalBo.getInstance().getListAll(filaFilter);
			
			if(filaAtendimentos.getTotal() > 0)
				filaAtendimentos.getList().sort(new Comparator<FilaAtendimentoOcupacional>() {
					public int compare(FilaAtendimentoOcupacional o1, FilaAtendimentoOcupacional o2) {
						return o1.getAtualizacao().compareTo(o2.getAtualizacao());
					}
				});
			
			// 6 - PARA CADA ITEM DA FILA DE ESPERA, VERIFICAR SE O EMPREGADO PODE IR PARA O
			// PROFISSÍVEL DISPONÍVEL (CONSIDERANDO O STATUS PENDENTE). ADICIONAR OS NOVOS ITENS
			// NA LISTA DE ATENDIMENTO, COM AS RESPECTIVAS FILAS E TAREFAS ATUALIZADAS
			RegraAtendimento regraAux = regra;
			fila.getList().forEach(filaEspera->{
				
				AtendimentoFilter aF = new AtendimentoFilter();
				aF.setPageNumber(1);
				aF.setPageSize(Integer.MAX_VALUE);
				aF.setFilaEsperaOcupacional(new FilaEsperaOcupacionalFilter());
				aF.getFilaEsperaOcupacional().setId(filaEspera.getId());
				aF.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
				aF.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
				aF.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
				aF.getFilaAtendimentoOcupacional().getInicio().setInicio(today);
				aF.getFilaAtendimentoOcupacional().setLocalizacao(new LocalizacaoFilter());
				aF.getFilaAtendimentoOcupacional().getLocalizacao().setId(localizacao.getId());
				aF.getFilaAtendimentoOcupacional().setStatus(
						StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);
				PagedList<Atendimento> aList = new PagedList<Atendimento>(); 
				try {
					aList = AtendimentoBo.getInstance().getListLoadAll(aF);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				FilaAtendimentoOcupacional remove = null;
				for(FilaAtendimentoOcupacional filaAtendimento : filaAtendimentos.getList()) {	
					//SE EXISTIR, VERIFICAR SE PELA REGRA SELECIONADA, A EQUIPE DO PROFISSIONAL
					//DEPENDE DE ALGUMA DAS EQUIPES EXISTENTES NA LISTA DE EQUIPES PENDENTES
					//DA FILA DE ESPERA
					if(aList.getTotal() > 0) {
						List<RegraAtendimentoEquipe> regraAtendimentoEquipe = 
								regraAux.getRegraAtendimentoEquipes().stream()
								.filter(r->r.getEquipe().getId() == 
									filaAtendimento.getProfissional().getEquipe().getId())
								.collect(Collectors.toList());
						
						if(regraAtendimentoEquipe != null && regraAtendimentoEquipe.size() > 0) {
							PagedList<Atendimento> aListAux = aList;
							
							if(regraAtendimentoEquipe.get(0).getRegraAtendimentoEquipeRequisitos()
								.stream().filter(r -> {
									return aListAux.getList().stream().filter(a->{
											return a.getFilaAtendimentoOcupacional().getProfissional()
													.getEquipe().getId() == r.getEquipe().getId();
									}).count() > 0;
								}).count() > 0) {
								continue;
							}
						}
					}
					
					//OBTER A TAREFA
					PagedList<Tarefa> tarefas = new PagedList<Tarefa>(); 
					TarefaFilter tarefaFilter = new TarefaFilter();
					tarefaFilter.setPageNumber(1);
					tarefaFilter.setPageSize(1);
					tarefaFilter.setCliente(new EmpregadoFilter());
					tarefaFilter.getCliente().setMatricula(filaEspera.getEmpregado().getMatricula());
					tarefaFilter.setEquipe(new EquipeFilter());
					tarefaFilter.getEquipe().setAbreviacao(
							filaAtendimento.getProfissional().getEquipe().getAbreviacao());
					tarefaFilter.setServico(new ServicoFilter());
					tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(today);
					calendar.add(Calendar.DATE, 1);
					
					tarefaFilter.setInicio(new DateFilter());
					tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
					tarefaFilter.getInicio().setInicio(today);
					tarefaFilter.getInicio().setFim(calendar.getTime());
					try {
						tarefas = TarefaBo.getInstance().getList(
								TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluidoCancelado());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(tarefas.getTotal() == 0)
						continue;
					
					//ATUALIZAR REFERENCIAS
					Tarefa tarefa = tarefas.getList().get(0);
					tarefa.setInicio(Helper.getNow());
					tarefa.setAtualizacao(tarefa.getInicio());
					tarefa.setResponsavel(filaAtendimento.getProfissional());
					tarefa.setStatus(StatusTarefa.getInstance().EXECUCAO);
					
					filaAtendimento.setAtualizacao(tarefa.getAtualizacao());
					filaAtendimento.setStatus(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO);
					
					filaEspera.setAtualizacao(filaAtendimento.getAtualizacao());
					filaEspera.setStatus(StatusFilaEsperaOcupacional.getInstance().EM_ATENDIMENTO);
					filaEspera.setTempoEspera(filaEspera.getTempoEspera() + calcularTempoAtualizacao(filaEspera));
					
					//INSTANCIAR
					Atendimento atendimento = new Atendimento();
					atendimento.setFilaAtendimentoOcupacional(filaAtendimento);
					atendimento.setFilaEsperaOcupacional(filaEspera);
					atendimento.setTarefa(tarefa);
					
					//ADD NA LISTA
					atendimentos.getList().add(atendimento);
					
					remove = filaAtendimento;
					break;
				}
				
				if(remove != null)
					filaAtendimentos.getList().remove(remove);
				remove = null;
			});
			
			// 7 - SALVAR A LISTA DE ATENDIMENTO NO BANCO
			AtendimentoBo.getInstance().saveList(atendimentos.getList());
		}
				
		// 8 - RETORNAR A LISTA DE ATENDIMENTO
		return atendimentos.getList();
	}
	
	private long calcularTempoAtualizacao(FilaEsperaOcupacional fila) {
		return (Helper.getNow().getTime() - fila.getAtualizacao().getTime()) / (60 * 1000) % 60;
	}
}
