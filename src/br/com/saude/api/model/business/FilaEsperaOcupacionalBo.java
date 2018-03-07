package br.com.saude.api.model.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import br.com.saude.api.generic.BooleanFilter;
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
import br.com.saude.api.model.entity.filter.IndicadorSastFilter;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.filter.PerguntaFichaColetaFilter;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;
import br.com.saude.api.model.entity.po.RegraAtendimento;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipe;
import br.com.saude.api.model.entity.po.RegraAtendimentoLocalizacao;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.FilaEsperaOcupacionalDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;

public class FilaEsperaOcupacionalBo 
	extends GenericBo<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter, 
					FilaEsperaOcupacionalDao, FilaEsperaOcupacionalBuilder, 
					FilaEsperaOcupacionalExampleBuilder> {
	
	private Function<FilaEsperaOcupacionalBuilder, FilaEsperaOcupacionalBuilder> functionLoadRefresh;

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
			return builder.loadLocalizacao().loadFichaColeta().loadRiscoPotencial();
		};
		
		this.functionLoadRefresh = builder -> {
			return builder.loadLocalizacao();
		};
		
	}
	
	@Override
	public FilaEsperaOcupacional getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<FilaEsperaOcupacional> getListAll(FilaEsperaOcupacionalFilter filter) throws  Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}
	
	public PagedList<FilaEsperaOcupacional> getListRefresh(FilaEsperaOcupacionalFilter filter) throws  Exception {
		return super.getList(getDao().getListRefresh(getExampleBuilder(filter).example()), 
				this.functionLoadRefresh);
	}
	
	private PagedList<FilaEsperaOcupacional> check(FilaEsperaOcupacional fila) throws Exception {
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
			
			PagedList<Empregado> empregados = EmpregadoBo.getInstance().getListEq(filter);
			
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
		
		return getList(getDao().getListRefresh(getExampleBuilder(filaFilter).example()), this.functionLoadRefresh);
	}
	
	public String checkOut(FilaEsperaOcupacional fila) throws Exception {
		
		PagedList<FilaEsperaOcupacional> filaEsperaOcupacionais = check(fila);
		
		if(filaEsperaOcupacionais.getTotal() > 0) {
			fila = filaEsperaOcupacionais.getList().get(0);
			fila.setStatus(StatusFilaEsperaOcupacional.getInstance().ALMOCO);
			getDao().save(fila);
		}else
			throw new Exception("Não foi encontrado o Check-in do Empregado.");
		
		return "Registro de Almoço realizado para o Empregado "+fila.getEmpregado().getPessoa().getNome()+".";
	}
	
	public String checkIn(FilaEsperaOcupacional fila) throws Exception {
		
		Date today = Helper.getToday();
		PagedList<FilaEsperaOcupacional> filaEsperaOcupacionais = check(fila);
		
		if(filaEsperaOcupacionais.getTotal() > 0) {
			
			fila = filaEsperaOcupacionais.getList().get(0);
			
			//SE ESTIVER EM ALMOÇO, VOLTAR PARA A FILA
			if(fila.getStatus().equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO) ||
					fila.getStatus().equals(StatusFilaEsperaOcupacional.getInstance().AUSENTE)) {
				fila.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
				
				getDao().save(fila);
				
				return "Empregado "+fila.getEmpregado().getPessoa().getNome()+" inserido na fila de espera. "+
						"Favor aguardar chamada.";
			}else
				throw new Exception("Já foi feito o Check-in deste Empregado na Localização: "
					+filaEsperaOcupacionais.getList().get(0).getLocalizacao().getNome()+".");
		}
		
		// 4 - VERIFICAR SE EXISTE AGENDAMENTO (TAREFA) PARA ESTE EMPREGADO (CLIENTE),
		// CUJO GRUPO DO SERVIÇO SEJA ATENDIMENTO OCUPACIONAL, E STATUS DIFERENTE DE
		// CONCLUÍDO E CANCELADO, E A DATA DA TAREFA ESTÁ ENTRE O DIA ATUAL E O SEGUINTE
		Tarefa tarefa = checkPendecia(fila.getEmpregado()); 
		if(tarefa == null) {
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
			
			tarefa = tarefas.getList().get(0);
		}
		
		// 5 - INSTANCIAR FILA
		fila.setHorarioCheckin(Helper.getNow());
		fila.setAtualizacao(fila.getHorarioCheckin());
		fila.setTempoEspera(0);
		fila.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		fila.setServico(tarefa.getServico());
		
		// 6 - CRIAR A FICHA DE COLETA, COM AS RESPOSTAS PARA AS PERGUNTAS ATIVAS
		PerguntaFichaColetaFilter perguntaFilter = new PerguntaFichaColetaFilter();
		perguntaFilter.setPageNumber(1);
		perguntaFilter.setPageSize(Integer.MAX_VALUE);
		perguntaFilter.setInativo(new BooleanFilter());
		perguntaFilter.getInativo().setValue(2);
		
		PagedList<PerguntaFichaColeta> perguntas = 
				PerguntaFichaColetaBo.getInstance().getListLoadAll(perguntaFilter);
		
		if(perguntas.getTotal() > 0) {
			fila.setFichaColeta(new FichaColeta());
			fila.getFichaColeta().setRespostaFichaColetas(new ArrayList<RespostaFichaColeta>());
			
			//PARA CADA PERGUNTA, CRIAR UMA RESPOSTA
			for(PerguntaFichaColeta pergunta : perguntas.getList()) {
				RespostaFichaColeta resposta = new RespostaFichaColeta();
				resposta.setPergunta(pergunta);
				resposta.setFicha(fila.getFichaColeta());
				fila.getFichaColeta().getRespostaFichaColetas().add(resposta);
			}
		}
		
		// 7 - GERAR O RISCO POTENCIAL
		RiscoPotencial risco = new RiscoPotencial();
		risco.setData(Helper.getToday());
		risco.setEmpregado(fila.getEmpregado());
		risco.setAtual(true);
		fila.setRiscoPotencial(risco);
		
		// 8 - OBTER A LISTA DOS RISCOS DO EMPREGADO PARA SETAR COMO NÃO ATUAL
		RiscoPotencialFilter riscoFilter = new RiscoPotencialFilter();
		riscoFilter.setPageNumber(1);
		riscoFilter.setPageSize(Integer.MAX_VALUE);
		riscoFilter.setEmpregado(new EmpregadoFilter());
		riscoFilter.getEmpregado().setId(fila.getEmpregado().getId());
		riscoFilter.setAtual(new BooleanFilter());
		riscoFilter.getAtual().setValue(1);
		
		PagedList<RiscoPotencial> riscos = RiscoPotencialBo.getInstance().getListLoadAll(riscoFilter);
		
		if(riscos.getTotal() > 0) {
			riscos.getList().forEach(r->r.setAtual(false));
			RiscoPotencialBo.getInstance().saveList(riscos.getList());
		}
		
		// 9 - INSERIR NO BANCO
		getDao().save(fila);
		
		return "Empregado "+fila.getEmpregado().getPessoa().getNome()+" inserido na fila de espera. "+
				"Favor aguardar chamada.";
	}
	
	@SuppressWarnings("deprecation")
	protected Tarefa checkPendecia(Empregado empregado) throws Exception {
		
		//VERIFICAR SE EXISTE TAREFA ABERTA ANTERIOR AO DIA ATUAL
		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(Integer.MAX_VALUE);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setId(empregado.getId());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setInicio(Helper.getToday());
		tarefaFilter.getInicio().setTypeFilter(TypeFilter.MENOR);
		tarefaFilter.setStatus(StatusTarefa.getInstance().ABERTA);
		
		PagedList<Tarefa> tarefas = TarefaBo.getInstance().getList(tarefaFilter);
		
		if(tarefas.getTotal() > 0) {
			//ORDENAR POR DATA INICIO E PEGAR A MAIOR DATA
			tarefas.getList().sort(new Comparator<Tarefa>() {
				public int compare(Tarefa arg0, Tarefa arg1) {
					return arg1.getInicio().compareTo(arg0.getInicio());
				}
			});
			
			Date data = Date.from(tarefas.getList().get(0).getInicio().toInstant());
			Date dataInicio = Date.from(data.toInstant());
			dataInicio.setHours(0);
			dataInicio.setMinutes(0);
			
			Date dataFim = Date.from(tarefas.getList().get(0).getInicio().toInstant());
			dataFim.setHours(23);
			dataFim.setMinutes(59);
			
			//VERIFICAR SE EXISTE TAREFA CONCLUÍDA PARA A MESMA DATA. SE EXISTIR, RETORNAR
			tarefaFilter = new TarefaFilter();
			tarefaFilter.setPageNumber(1);
			tarefaFilter.setPageSize(1);
			tarefaFilter.setCliente(new EmpregadoFilter());
			tarefaFilter.getCliente().setId(empregado.getId());
			tarefaFilter.setServico(new ServicoFilter());
			tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
			tarefaFilter.setStatus(StatusTarefa.getInstance().CONCLUIDA);
			tarefaFilter.setInicio(new DateFilter());
			tarefaFilter.getInicio().setInicio(dataInicio);
			tarefaFilter.getInicio().setFim(dataFim);
			tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
			
			tarefas = TarefaBo.getInstance().getList(tarefaFilter);
			
			if(tarefas.getTotal() > 0) {
				Tarefa tarefa = tarefas.getList().get(0);
				tarefa.setInicio(dataInicio);
				tarefa.setFim(dataFim);
				return tarefa;
			}
		}
		
		return null;
	}
	
	public List<Atendimento> refresh(Atendimento at) throws Exception {
		
		// 2 - VERIFICAR SE A LOCALIZAÇÃO FOI INFORMADA
		if(at.getFilaEsperaOcupacional() == null || 
				at.getFilaEsperaOcupacional().getLocalizacao() == null || 
				at.getFilaEsperaOcupacional().getLocalizacao().getId() == 0)
			throw new Exception("É necessário informar a Localização.");
		
		Localizacao localizacao = LocalizacaoBo.getInstance().getById( 
				at.getFilaEsperaOcupacional().getLocalizacao().getId());
		
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
		AtendimentoFilter atendimentoFilter = new AtendimentoFilter();
		atendimentoFilter.setPageNumber(1);
		atendimentoFilter.setPageSize(Integer.MAX_VALUE);
		atendimentoFilter.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
		atendimentoFilter.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
		atendimentoFilter.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		atendimentoFilter.getFilaAtendimentoOcupacional().getInicio().setInicio(today);
		atendimentoFilter.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO);
		atendimentoFilter.getFilaAtendimentoOcupacional().setLocalizacao(new LocalizacaoFilter());
		atendimentoFilter.getFilaAtendimentoOcupacional().getLocalizacao().setId(localizacao.getId());
		atendimentoFilter.setTarefa(new TarefaFilter());
		
		PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAllTarefaStatusNaoConcluidoCancelado(atendimentoFilter);
		
		if(atendimentos.getTotal() == 0)
			atendimentos.setList(new ArrayList<Atendimento>());
		
		if(fila.getTotal() > 0) {
			fila.getList().sort(new Comparator<FilaEsperaOcupacional>() {
				public int compare(FilaEsperaOcupacional arg0, FilaEsperaOcupacional arg1) {
					return arg0.getHorarioCheckin().compareTo(arg1.getHorarioCheckin());
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
			
			if(filaAtendimentos.getTotal() > 0) {
				filaAtendimentos.getList().sort(new Comparator<FilaAtendimentoOcupacional>() {
					public int compare(FilaAtendimentoOcupacional o1, FilaAtendimentoOcupacional o2) {
						return o1.getAtualizacao().compareTo(o2.getAtualizacao());
					}
				});
				
				// 6 - PARA CADA ITEM DA FILA DE ESPERA, VERIFICAR SE O EMPREGADO PODE IR PARA O
				// PROFISSÍVEL DISPONÍVEL (CONSIDERANDO O STATUS PENDENTE). ADICIONAR OS NOVOS ITENS
				// NA LISTA DE ATENDIMENTO, COM AS RESPECTIVAS FILAS E TAREFAS ATUALIZADAS
				fila.getList().forEach(filaEspera->{
					
					Tarefa tarefaPendencia = null;
					
					AtendimentoFilter aF = new AtendimentoFilter();
					aF.setPageNumber(1);
					aF.setPageSize(Integer.MAX_VALUE);
					aF.setFilaEsperaOcupacional(new FilaEsperaOcupacionalFilter());
					aF.getFilaEsperaOcupacional().setEmpregado(new EmpregadoFilter());
					aF.getFilaEsperaOcupacional().getEmpregado().setId(filaEspera.getEmpregado().getId());
					aF.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
					aF.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
					aF.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
					aF.getFilaAtendimentoOcupacional().getInicio().setInicio(today);
					aF.getFilaAtendimentoOcupacional().setLocalizacao(new LocalizacaoFilter());
					aF.getFilaAtendimentoOcupacional().getLocalizacao().setId(localizacao.getId());
					aF.setTarefa(new TarefaFilter());
					aF.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);
					aF.getTarefa().setServico(new ServicoFilter());
					aF.getTarefa().getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
					
					PagedList<Atendimento> aList = new PagedList<Atendimento>(); 
					try {
						aList = AtendimentoBo.getInstance().getListLoadAll(aF);						
						
						//VERIFICAR SE EXISTE PENDÊNCIA PARA O EMPREGADO. CASO EXISTA, OBTER A DATA DOS 
						//ATENDIMENTOS JÁ REALIZADOS E ADICIONÁ-LOS EM aList
						tarefaPendencia = checkPendecia(filaEspera.getEmpregado());
						if(tarefaPendencia != null) {							
							aF.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
							aF.getFilaAtendimentoOcupacional().getInicio().setInicio(tarefaPendencia.getInicio());
							aF.getFilaAtendimentoOcupacional().getInicio().setFim(tarefaPendencia.getFim());
							aF.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.ENTRE);
							
							PagedList<Atendimento> a = AtendimentoBo.getInstance().getListLoadAll(aF);
							
							if(a.getTotal() > 0) {
								if(aList.getList() == null)
									aList.setList(new ArrayList<Atendimento>());
								
								aList.getList().addAll(a.getList());
								aList.setTotal(aList.getTotal()+a.getList().size());
							}								
						}
						
						//OBTER OS ATENDIMENTOS EM LANÇAMENTO DE INFORMAÇÕES
						aF.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
						aF.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
						aF.getFilaAtendimentoOcupacional().getInicio().setInicio(today);
						aF.getFilaAtendimentoOcupacional().setStatus(StatusFilaAtendimentoOcupacional
								.getInstance().LANCAMENTO_DE_INFORMACOES);
						aF.getTarefa().setStatus(StatusTarefa.getInstance().EXECUCAO);
						
						PagedList<Atendimento> a = AtendimentoBo.getInstance().getListLoadAll(aF);
						
						if(a.getTotal() > 0) {
							if(aList.getList() == null)
								aList.setList(new ArrayList<Atendimento>());
							
							aList.getList().addAll(a.getList());
							aList.setTotal(aList.getTotal()+a.getList().size());
						}						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//OBTER A REGRA
					PagedList<Tarefa> tarefasAux = new PagedList<Tarefa>(); 
					TarefaFilter tarefaFilterAux = new TarefaFilter();
					tarefaFilterAux.setPageNumber(1);
					tarefaFilterAux.setPageSize(1);
					tarefaFilterAux.setCliente(new EmpregadoFilter());
					tarefaFilterAux.getCliente().setId(filaEspera.getEmpregado().getId());
					tarefaFilterAux.setServico(new ServicoFilter());
					tarefaFilterAux.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
					
					Calendar calendarAux = Calendar.getInstance();
					calendarAux.setTime(today);
					calendarAux.add(Calendar.DATE, 1);
					
					tarefaFilterAux.setInicio(new DateFilter());
					tarefaFilterAux.getInicio().setTypeFilter(TypeFilter.ENTRE);
					tarefaFilterAux.getInicio().setInicio(today);
					tarefaFilterAux.getInicio().setFim(calendarAux.getTime());
					try {
						tarefasAux = TarefaBo.getInstance().getList(
								TarefaExampleBuilder.newInstance(tarefaFilterAux).exampleStatusNaoConcluidoCancelado());
						
						//SE TIVER PENDÊNCIA, OBTER AS TAREFAS DO DIA DA PENDÊNCIA E ADICIONAR EM tarefas
						if(tarefaPendencia != null) {
							tarefaFilterAux.setInicio(new DateFilter());
							tarefaFilterAux.getInicio().setTypeFilter(TypeFilter.ENTRE);
							tarefaFilterAux.getInicio().setInicio(tarefaPendencia.getInicio());
							tarefaFilterAux.getInicio().setFim(tarefaPendencia.getFim());
							
							PagedList<Tarefa> t = TarefaBo.getInstance().getList(
									TarefaExampleBuilder.newInstance(tarefaFilterAux).exampleStatusNaoConcluidoCancelado());
							
							if(t.getTotal() > 0) {
								if(tarefasAux.getList() == null)
									tarefasAux.setList(new ArrayList<Tarefa>());
								
								tarefasAux.getList().addAll(t.getList());
								tarefasAux.setTotal(tarefasAux.getTotal()+t.getList().size());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					Tarefa tarefaAux = tarefasAux.getList().get(0);
					
					RegraAtendimento regraAux = null;
					
					Optional<RegraAtendimentoLocalizacao> opt = localizacao
							.getRegraAtendimentoLocalizacoes().stream()
							.filter(r->r.getServicos().stream().filter(s->
											s.getId() == tarefaAux.getServico().getId())
										.count() > 0)
							.findFirst();
					
					if(opt != null) {
						
						try {
							if(opt.get() != null)
								regraAux = opt.get().getRegraAtendimento();
						}catch(NoSuchElementException ex) {

						}
					}
					
					try {
						regraAux = RegraAtendimentoBo.getInstance().getById(regraAux.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(regraAux != null)
					for(RegraAtendimentoEquipe rE : regraAux.getRegraAtendimentoEquipes()) {
						
						Optional<FilaAtendimentoOcupacional> o = filaAtendimentos.getList().stream()
								.filter(x->x.getProfissional().getEquipe().getId() == rE.getEquipe().getId() && 
											x.getProfissional().getServicos().stream().filter(s->
												s.getId() == tarefaAux.getServico().getId()).count() > 0)
								.findFirst();
						
						if(o != null) {
							FilaAtendimentoOcupacional fA = null;
							
							try {
								fA = o.get();
							}catch(NoSuchElementException ex) {
								
							}
							
							if(fA != null) {
								
								//SE DEPENDER DE ALGUMA EQUIPE...
								if(rE.getRegraAtendimentoEquipeRequisitos().size() > 0) {
									
									if(aList.getTotal() == 0)
										continue;
									
									if(rE.getRegraAtendimentoEquipeRequisitos().size() > aList.getTotal())
										continue;
									
									List<Atendimento> listAtendimento = aList.getList();
									if(rE.getRegraAtendimentoEquipeRequisitos().stream().filter(r-> {
										return listAtendimento.stream().filter(a->{
											return a.getFilaAtendimentoOcupacional().getProfissional()
													.getEquipe().getId() == r.getEquipe().getId();
										}).count() > 0;
									}).count() != rE.getRegraAtendimentoEquipeRequisitos().size())
										continue;
									
									//SE FOR ACOLHIMENTO, NÃO LEVAR EM CONSIDERAÇÃO OS ATENDIMENTOS
									//CUJA TAREFA ESTEJA EM EXECUÇÃO
									if(rE.getEquipe().getAbreviacao().equals("ACO") && listAtendimento.stream()
											.filter(a->a.getTarefa().getStatus().equals(
													StatusTarefa.getInstance().EXECUCAO))
											.count() > 0 ) {
										continue;
									}
								}
								
								//OBTER A TAREFA
								PagedList<Tarefa> tarefas = new PagedList<Tarefa>(); 
								TarefaFilter tarefaFilter = new TarefaFilter();
								tarefaFilter.setPageNumber(1);
								tarefaFilter.setPageSize(1);
								tarefaFilter.setCliente(new EmpregadoFilter());
								tarefaFilter.getCliente().setId(filaEspera.getEmpregado().getId());
								tarefaFilter.setEquipe(new EquipeFilter());
								tarefaFilter.getEquipe().setAbreviacao(
										fA.getProfissional().getEquipe().getAbreviacao());
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
									
									//SE TIVER PENDÊNCIA, OBTER AS TAREFAS DO DIA DA PENDÊNCIA E ADICIONAR EM tarefas
									if(tarefaPendencia != null) {
										tarefaFilter.setInicio(new DateFilter());
										tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
										tarefaFilter.getInicio().setInicio(tarefaPendencia.getInicio());
										tarefaFilter.getInicio().setFim(tarefaPendencia.getFim());
										
										PagedList<Tarefa> t = TarefaBo.getInstance().getList(
												TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluidoCancelado());
										
										if(t.getTotal() > 0) {
											if(tarefas.getList() == null)
												tarefas.setList(new ArrayList<Tarefa>());
											
											tarefas.getList().addAll(t.getList());
											tarefas.setTotal(tarefas.getTotal()+t.getList().size());
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								if(tarefas.getTotal() == 0)
									continue;
								
								//ATUALIZAR REFERENCIAS
								Tarefa tarefa = tarefas.getList().get(0);
								
								if(tarefa.getStatus().equals(StatusTarefa.getInstance().EXECUCAO))
									continue;
								
								if(fA.getProfissional().getServicos().stream().filter(s->
									s.getId() == tarefa.getServico().getId()).count() == 0)
								continue;
								
								tarefa.setInicio(Helper.getNow());
								tarefa.setAtualizacao(tarefa.getInicio());
								tarefa.setResponsavel(fA.getProfissional());
								tarefa.setStatus(StatusTarefa.getInstance().EXECUCAO);
								
								fA.setStatus(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO);
								
								filaEspera.setStatus(StatusFilaEsperaOcupacional.getInstance().EM_ATENDIMENTO);
								filaEspera.setTempoEspera(filaEspera.getTempoEspera() + calcularTempoAtualizacao(filaEspera));
								filaEspera.setAtualizacao(tarefa.getAtualizacao());
								//SETAR REFERENCIA DE FICHA COLETA EM RESPOSTAS FICHAS COLETAS
								filaEspera.getFichaColeta().getRespostaFichaColetas().forEach(rF -> 
									rF.setFicha(filaEspera.getFichaColeta()));
								
								//INSTANCIAR
								Atendimento atendimento = new Atendimento();
								atendimento.setFilaAtendimentoOcupacional(fA);
								atendimento.setFilaEsperaOcupacional(filaEspera);
								atendimento.setTarefa(tarefa);
								atendimento = AtendimentoBo.getInstance().addAtualizacao(atendimento);
								fA.setAtualizacao(tarefa.getAtualizacao());
								
								//CRIAR A FICHA DE TRIAGEM
								try {
									atendimento = setTriagens(atendimento);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								//ADD NA LISTA
								atendimentos.getList().add(atendimento);
								filaAtendimentos.getList().remove(fA);
								break;
							}
						}
					}
				});
			}	
			
			// 7 - SALVAR A LISTA DE ATENDIMENTO NO BANCO
			AtendimentoBo.getInstance().saveList(atendimentos.getList());
		}
				
		// 8 - RETORNAR A LISTA DE ATENDIMENTO
		return  AtendimentoBo.getInstance().getBuilder(atendimentos.getList()).loadTarefa().getEntityList();
	}
	
	private Atendimento setTriagens(Atendimento atendimento) throws Exception {
		
		// 1 - OBTER OS INDICADORES ATIVOS DA EQUIPE DO PROFISSIONAL
		IndicadorSastFilter indicadorFilter = new IndicadorSastFilter();
		indicadorFilter.setPageNumber(1);
		indicadorFilter.setPageSize(Integer.MAX_VALUE);
		indicadorFilter.setInativo(new BooleanFilter());
		indicadorFilter.getInativo().setValue(2);
		indicadorFilter.setEquipe(new EquipeFilter());
		indicadorFilter.getEquipe().setId(atendimento.getFilaAtendimentoOcupacional()
				.getProfissional().getEquipe().getId());
		
		PagedList<IndicadorSast> indicadores = IndicadorSastBo.getInstance().getList(indicadorFilter);
		
		if(indicadores.getTotal() > 0) {
			
			if(atendimento.getTriagens() == null)
				atendimento.setTriagens(new ArrayList<Triagem>());
			
			// 2 - PARA CADA INDICADOR RETORNADO, CRIAR UMA TRIAGEM COM O INDICADOR ASSOCIADO
			for(IndicadorSast indicador : indicadores.getList()) {
				Triagem triagem = new Triagem();
				triagem.setAtendimento(atendimento);
				triagem.setIndicadorSast(indicador);
				atendimento.getTriagens().add(triagem);
			}
		}
		
		return atendimento;
	}
	
	private long calcularTempoAtualizacao(FilaEsperaOcupacional fila) {
		return (Helper.getNow().getTime() - fila.getAtualizacao().getTime()) / (60 * 1000) % 60;
	}
}
