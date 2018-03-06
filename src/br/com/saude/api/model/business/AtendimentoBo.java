package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.AtendimentoBuilder;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.creation.builder.example.AtendimentoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.TarefaExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.AtendimentoFactory;
import br.com.saude.api.model.creation.factory.entity.FilaAtendimentoOcupacionalAtualizacaoFactory;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Atividade;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.AtendimentoDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusAso;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TipoConvocacao;

public class AtendimentoBo extends GenericBo<Atendimento, AtendimentoFilter, AtendimentoDao, 
	AtendimentoBuilder, AtendimentoExampleBuilder> {

	private static AtendimentoBo instance;
	
	private AtendimentoBo() {
		super();
	}
	
	public static AtendimentoBo getInstance() {
		if(instance == null)
			instance = new AtendimentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadTarefa().loadTriagens();
		};
	}
	
	@Override
	public Atendimento getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAll(AtendimentoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAllTarefaStatusNaoConcluidoCancelado(AtendimentoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).exampleTarefaStatusNaoConcluidoCancelado()), 
				this.functionLoadAll);
	}
	
	public List<Atendimento> getListFilaAtendimentoLocalizacaoDoMomento(Atendimento atendimento) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		return getDao().getListFilaAtendimentoLocalizacaoDoMomento(atendimento);
	}
	
	protected Atendimento addAtualizacao(Atendimento atendimento) {
		
		if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() == null)
			atendimento.getFilaAtendimentoOcupacional().setAtualizacoes(
					new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().add(
				FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
					.filaAtendimentoOcupacional(atendimento.getFilaAtendimentoOcupacional())
					.status(atendimento.getFilaAtendimentoOcupacional().getStatus())
					.tempo(FilaAtendimentoOcupacionalBo.getInstance()
							.calcularTempoAtualizacao(atendimento.getFilaAtendimentoOcupacional()))
					.get());
		
		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());
		
		if ( atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas() != null ) {
			atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r -> {
				r.setFicha(atendimento.getFilaEsperaOcupacional().getFichaColeta());
			});
		}
		
		return atendimento;
	}
	
	public Atendimento iniciar(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO);
		
		return save(addAtualizacao(atendimento), this.functionLoadAll);
	}
	
	@SuppressWarnings("deprecation")
	public Atendimento registrarAusencia(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		FilaAtendimentoOcupacional fila = FilaAtendimentoOcupacionalBo.getInstance()
				.getBuilder(atendimento.getFilaAtendimentoOcupacional())
				.getEntity();
		
		//SETAR EMPREGADO COMO AGUARDANDO
		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaEsperaOcupacional().setStatus(
				StatusFilaEsperaOcupacional.getInstance().AUSENTE);
		
		//SETAR PROFISSIONAL COMO INDISPONÍVEL
		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
		
		if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null) 
			atendimento.getFilaAtendimentoOcupacional().getAtualizacoes()
				.forEach(a->a.setFila(fila));
		
		//GRAVAR TAREFA COMO ABERTA, REMOVENDO PROFISSIONAL
		
		Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
		Date fim = Date.from(inicio.toInstant());
		inicio.setHours(8);
		fim.setHours(16);
		
		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atendimento.getTarefa().setResponsavel(null);
		atendimento.getTarefa().setInicio(inicio);
		atendimento.getTarefa().setFim(fim);
		
		getDao().devolverPraFila(atendimento);
		
		return FilaAtendimentoOcupacionalBo.getInstance().atualizar(AtendimentoFactory
				.newInstance().filaAtendimento(fila).get());
	}
	
	@SuppressWarnings("deprecation")
	public Atendimento devolverPraFila(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		FilaAtendimentoOcupacional fila = FilaAtendimentoOcupacionalBo.getInstance()
				.getBuilder(atendimento.getFilaAtendimentoOcupacional())
				.getEntity();
		
		//SETAR EMPREGADO COMO AGUARDANDO
		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaEsperaOcupacional().setStatus(
				StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		
		//SETAR PROFISSIONAL COMO INDISPONÍVEL
		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);
		
		if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null) 
			atendimento.getFilaAtendimentoOcupacional().getAtualizacoes()
				.forEach(a->a.setFila(fila));
		
		//GRAVAR TAREFA COMO ABERTA, REMOVENDO PROFISSIONAL
		
		Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
		Date fim = Date.from(inicio.toInstant());
		inicio.setHours(8);
		fim.setHours(16);
		
		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atendimento.getTarefa().setResponsavel(null);
		atendimento.getTarefa().setInicio(inicio);
		atendimento.getTarefa().setFim(fim);
		
		getDao().devolverPraFila(atendimento);
		
		return FilaAtendimentoOcupacionalBo.getInstance().atualizar(AtendimentoFactory
				.newInstance().filaAtendimento(fila).get());
	}
	
	public Atendimento liberar(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		if(!atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO))
			throw new Exception("Não é possível liberar o empregado. Status: "+
					atendimento.getFilaAtendimentoOcupacional().getStatus());
		
		if(finalizouAtendimento(atendimento))
			atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().FINALIZADO);
		else if(!atendimento.getFilaEsperaOcupacional().getStatus()
				.equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO))
			atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		
		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());
		
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);
		
		return save(addAtualizacao(atendimento));
	}
	
	public Atendimento finalizar(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		
		if(!(atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO)) &&
			!(atendimento.getFilaAtendimentoOcupacional().getStatus()
					.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES)))
			throw new Exception("Não é possível finalizar o atendimento. Status: "+
					atendimento.getFilaAtendimentoOcupacional().getStatus());
		
		boolean liberado = atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);

		Date now = Helper.getNow();
		
		if(finalizouAtendimento(atendimento)) {
			if(!liberado)
				atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().FINALIZADO);
			
		} else if(!liberado)
			if(!atendimento.getFilaEsperaOcupacional().getStatus()
					.equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO))
				atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		
		//VERIFICA SE FOI ATENDIMENTO MÉDICO PARA CRIAÇÃO DO ASO
		if(atendimento.getFilaAtendimentoOcupacional()
				.getProfissional().getEquipe().getAbreviacao().contains("MED")) {
			
			atendimento.setAso(new Aso());
			atendimento.getAso().setAtendimento(atendimento);
			atendimento.getAso().setData(now);
			atendimento.getAso().setEmpregado(atendimento.getFilaEsperaOcupacional()
					.getEmpregado());
			atendimento.getAso().setStatus(StatusAso.PENDENTE_AUDITORIA);
			atendimento.getAso().setValidade(getValidadeAso(atendimento));
		}
		
		atendimento.getFilaEsperaOcupacional().setAtualizacao(now);
		
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
		
		atendimento.getTarefa().setFim(now);
		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);
		
		atendimento = gerarRisco(atendimento);
		
		return save(addAtualizacao(atendimento));
	}
	
	private Atendimento gerarRisco(Atendimento atendimento) {
		
		if(atendimento.getTriagens() != null && atendimento.getTriagens().size() > 0) {			
			List<Triagem> triagens = atendimento.getTriagens().stream().filter(t->t.getIndice() >= 0)
					.collect(Collectors.toList());
			
			//CARREGAR OS INDICADORES
			triagens.forEach(t->{
				try {
					t.setIndicadorSast(
							IndicadorSastBo.getInstance().getById(t.getIndicadorSast().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			double r0 = 0.95 - triagens.stream()
					.flatMapToInt(t-> IntStream.of(t.getIndice())).average().getAsDouble();
			
			double r01 = Math.log10(atendimento.getFilaAtendimentoOcupacional()
				.getProfissional().getEquipe().getPrioridadeSast() + 1) /
					(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe()
								.getPrioridadeSast() + triagens.size());
			
			double r1 = r0 + r01;
			
			if (triagens.stream().filter(t->t.getIndice() <= 
					t.getIndicadorSast().getCritico()).count() > 0)
				r1 = 0.95;
			
			RiscoEmpregado risco = new RiscoEmpregado();
			risco.setRiscoPotencial(atendimento.getFilaEsperaOcupacional().getRiscoPotencial());
			risco.setEquipe(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe());
			risco.setTriagens(atendimento.getTriagens());
			risco.setValor(r1);
			
			atendimento.getTriagens().forEach(t->t.setRiscoEmpregado(risco));
		}
		
		return atendimento;
	}
	
	public Atendimento finalizarPausar(Atendimento atendimento) throws Exception {
		
		atendimento = getById(atendimento.getId());
		
		if(!(atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO)) &&
			!(atendimento.getFilaAtendimentoOcupacional().getStatus()
					.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES)))
			throw new Exception("Não é possível finalizar o atendimento. Status: "+
					atendimento.getFilaAtendimentoOcupacional().getStatus());
		
		Date now = Helper.getNow();
		
		boolean liberado = atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);
		
		if(finalizouAtendimento(atendimento)) {
			if(!liberado)
				atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().FINALIZADO);
			
		} else if(!liberado)
			if(!atendimento.getFilaEsperaOcupacional().getStatus()
					.equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO))
				atendimento.getFilaEsperaOcupacional().setStatus(
					StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		
		//VERIFICA SE FOI ATENDIMENTO MÉDICO PARA CRIAÇÃO DO ASO
		if(atendimento.getFilaAtendimentoOcupacional()
				.getProfissional().getEquipe().getAbreviacao().contains("MED")) {
			
			atendimento.setAso(new Aso());
			atendimento.getAso().setAtendimento(atendimento);
			atendimento.getAso().setData(now);
			atendimento.getAso().setEmpregado(atendimento.getFilaEsperaOcupacional()
					.getEmpregado());
			atendimento.getAso().setStatus(StatusAso.PENDENTE_AUDITORIA);
			atendimento.getAso().setValidade(getValidadeAso(atendimento));
		}
		
		atendimento.getFilaEsperaOcupacional().setAtualizacao(now);
		
		atendimento.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);
		
		atendimento.getTarefa().setFim(now);
		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);
		
		atendimento = gerarRisco(atendimento);
		
		return save(addAtualizacao(atendimento));
	}
	
	private Date getValidadeAso(Atendimento atendimento) {
		
		int meses = 0;
		String tipoTipoAtendimento = getTipoAtendimento(atendimento);
		
		switch(tipoTipoAtendimento) {
			case TipoConvocacao.PERIODICO:
				meses = 12;
				break;
		}
		
		return Date.from(LocalDateTime.ofInstant(Helper.getToday().toInstant(), ZoneId.systemDefault())
				.plusMonths(meses)
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}
	
	private boolean finalizouAtendimento(Atendimento atendimento) throws Exception {
		
		Date today = Helper.getToday();
		 
		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(2);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setId(atendimento.getFilaEsperaOcupacional()
				.getEmpregado().getId());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);
		
		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
		tarefaFilter.getInicio().setInicio(today);
		tarefaFilter.getInicio().setFim(calendar.getTime());
		
		PagedList<Tarefa> tarefas = TarefaBo.getInstance().getList(
					TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluido());
		
		//VERIFICAR PENDÊNCIA
		Tarefa tarefaPendencia = FilaEsperaOcupacionalBo.getInstance().checkPendecia(
				atendimento.getFilaEsperaOcupacional().getEmpregado());
		
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
		
		//VERIFICAR SE RETORNOU A PRÓPRIA TAREFA
		if(tarefas.getTotal() > 0) {
			if(tarefas.getList().stream().filter(t->t.getId() == atendimento.getTarefa().getId())
					.count() > 0)
				tarefas.setTotal(tarefas.getTotal() - 1);
		}
		
		return tarefas.getTotal() <= 0;
	}
	
	private String getTipoAtendimento(Atendimento atendimento) {
		switch(atendimento.getTarefa().getServico().getGrupo()) {
			case GrupoServico.ATENDIMENTO_OCUPACIONAL:
			
				switch(atendimento.getTarefa().getServico().getCodigo()) {
					//ADMISSIONAL
					case "0001":
						
						break;
						
					//DEMISSIONAL
					case "0002":
						
						break;
						
					//PERIÓDICO
					case "0003":
						return TipoConvocacao.PERIODICO;
					case "1003":
						return TipoConvocacao.PERIODICO;
						
					//RETORNO AO TRABALHO
					case "0004":
						
						break;
						
					//MUDANÇA DE FUNÇÃO
					case "0005":
						
						break;
						
					//ESPECIAL
					case "0006":
						
						break;
						
					//PERICIAL
					case "0007":
						
						break;
						
					//AVALIAÇÃO DE CAPACIDADE LABORAL
					case "0008":
						
						break;
						
				}
			break;
		}
		return "***";
	}
	
	public EmpregadoConvocacaoFilter configureEmpregadoConvocacaoFilter(Atendimento atendimento) {
		String tipoAtendimento = getTipoAtendimento(atendimento);
		
		// 1 - OBTER A CONVOCAÇÃO DO EMPREGADO, CUJA DATA INFORMADA ESTÁ NO PERÍODO
		// DO CRONOGRAMA, CUJO TIPO DA CONVOCAÇÃO CORRESPONDA AO SERVIÇO SELECIONADO
		EmpregadoConvocacaoFilter empConFilter = new EmpregadoConvocacaoFilter();
		empConFilter.setPageNumber(1);
		empConFilter.setPageSize(1);
		empConFilter.setEmpregado(new EmpregadoFilter());
		empConFilter.getEmpregado().setId(atendimento.getTarefa().getCliente().getId());
		empConFilter.setConvocacao(new ConvocacaoFilter());
		empConFilter.getConvocacao().setInicio(new DateFilter());
		empConFilter.getConvocacao().getInicio().setTypeFilter(TypeFilter.MENOR_IGUAL);
		empConFilter.getConvocacao().getInicio().setInicio(atendimento.getTarefa().getInicio());
		empConFilter.getConvocacao().setFim(new DateFilter());
		empConFilter.getConvocacao().getFim().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		empConFilter.getConvocacao().getFim().setInicio(atendimento.getTarefa().getInicio());
		empConFilter.getConvocacao().setTipo(tipoAtendimento);
		return empConFilter;
	}
	
	@SuppressWarnings("deprecation")
	public String registrarSolicitacaoAtendimento(Atendimento atendimento) throws Exception {
		
		// 1 - OBTER A CONVOCAÇÃO DO EMPREGADO, CUJA DATA INFORMADA ESTÁ NO PERÍODO
		// DO CRONOGRAMA, CUJO TIPO DA CONVOCAÇÃO CORRESPONDA AO SERVIÇO SELECIONADO
		EmpregadoConvocacaoFilter empConFilter = configureEmpregadoConvocacaoFilter(atendimento);
		PagedList<EmpregadoConvocacao> empConList = 
				EmpregadoConvocacaoBo.getInstance().getList(empConFilter);
		
		if(empConList.getTotal() == 0)
			throw new Exception("Não foi possível solicitar o serviço, pois não existe "
					+ "convocação para o empregado cujo cronograma atenda à data informada.");
		
		// 2 - VERIFICAR SE O RESULTADO DOS EXAMES JÁ FOI AUDITADO
		if(!empConList.getList().get(0).isResultadoAuditado())
			throw new Exception("Não foi possível solicitar o serviço, pois não foi realizada "
					+ "a auditoria do resultado dos exames.");
		
		// 3 - VERIFICAR SE A SOLICITAÇÃO JÁ FOI REALIZADA PREVIAMENTE
		Date f = Date.from(LocalDateTime.ofInstant(atendimento.getTarefa().getInicio().toInstant(), ZoneId.systemDefault())
				.plusDays(1)
				.atZone(ZoneId.systemDefault())
				.toInstant());
		
		TarefaFilter filter = new TarefaFilter();
		filter.setPageNumber(1);
		filter.setPageSize(1);
		filter.setCliente(new EmpregadoFilter());
		filter.getCliente().setId(atendimento.getTarefa().getCliente().getId());
		filter.setServico(new ServicoFilter());
		filter.getServico().setId(atendimento.getTarefa().getServico().getId());
		filter.setInicio(new DateFilter());
		filter.getInicio().setInicio(atendimento.getTarefa().getInicio());
		filter.getInicio().setFim(f);
		filter.getInicio().setTypeFilter(TypeFilter.ENTRE);
		
		PagedList<Tarefa> ts = TarefaBo.getInstance().getList(filter);
		
		if(ts.getTotal() > 0)
			throw new Exception("Não foi possível solicitar o serviço, pois já foi realizada "
					+ "uma solicitação para o mesmo dia.");
		
		// 4 - LOAD ALL DO SERVIÇO DA TAREFA DO ATENDIMENTO
		atendimento.getTarefa().setServico(
				ServicoBo.getInstance()
				.getById(atendimento.getTarefa().getServico().getId()));
		
		// 5 - INSTANCIAR UMA TAREFA PARA CADA ATIVIDADE DO SERVIÇO.
		// SERVIÇO PERIÓDICO RESERVA TODA A AGENDA DO DIA.
		Tarefa tarefa = null;
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		for(Atividade atividade : atendimento.getTarefa().getServico().getAtividades()) {
			
			Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
			Date fim = Date.from(inicio.toInstant());
			
			inicio.setHours(8);
			fim.setHours(16);
			
			tarefa = TarefaBuilder.newInstance(atendimento.getTarefa()).getEntity();
			tarefa.setAtualizacao(Helper.getNow());
			tarefa.setEquipe(atividade.getEquipe());
			tarefa.setInicio(inicio);
			tarefa.setFim(fim);
			tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
			tarefas.add(tarefa);
		}
		
		// 6 - SALVAR NO BANCO
		TarefaBo.getInstance().saveList(tarefas);
		
		return "Atendimento registrado com sucesso.";
	}
	
}
