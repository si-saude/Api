package br.com.saude.api.model.business;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.FilaAtendimentoOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.FilaAtendimentoOcupacionalExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.FilaAtendimentoOcupacionalAtualizacaoFactory;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.FilaAtendimentoOcupacionalFilter;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.persistence.FilaAtendimentoOcupacionalDao;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;

public class FilaAtendimentoOcupacionalBo 
	extends GenericBo<FilaAtendimentoOcupacional, FilaAtendimentoOcupacionalFilter, 
						FilaAtendimentoOcupacionalDao, FilaAtendimentoOcupacionalBuilder, 
						FilaAtendimentoOcupacionalExampleBuilder> {

	private Function<FilaAtendimentoOcupacionalBuilder,FilaAtendimentoOcupacionalBuilder> functionLoadLocalizacao;
	
	private static FilaAtendimentoOcupacionalBo instance;
	
	private FilaAtendimentoOcupacionalBo() {
		super();
	}
	
	public static FilaAtendimentoOcupacionalBo getInstance() {
		if(instance == null)
			instance = new FilaAtendimentoOcupacionalBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadLocalizacao = builder -> {
			return builder.loadLocalizacao();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoadLocalizacao.apply(builder).loadAtualizacoes();
		};
	}
	
	@Override
	public FilaAtendimentoOcupacional getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	private void verificacaoInicial(FilaAtendimentoOcupacional fila) throws Exception{
		// 1 - VERIFICAR SE A LOCALIZAÇÃO FOI INFORMADA
				if(fila.getLocalizacao() == null)
					throw new Exception("É necessário informar a Localização para entrar na Fila de Atendimento.");
				
		// 2 - VERIFICAR SE O PROFISSIONAL FOI INFORMADO
				if(fila.getProfissional() == null || fila.getProfissional().getId() == 0)
					throw new Exception("É necessário informar o Profissional para entrar na Fila de Atendimento.");
	}
	
	private void encerrarAutomaticamente(FilaAtendimentoOcupacional fila) throws Exception {
		FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(Integer.MAX_VALUE);
		filaFilter.setProfissional(new ProfissionalFilter());
		filaFilter.getProfissional().setId(fila.getProfissional().getId());
		filaFilter.setInicio(new DateFilter());
		filaFilter.getInicio().setTypeFilter(TypeFilter.MENOR);
		filaFilter.getInicio().setInicio(Helper.getToday());
		
		PagedList<FilaAtendimentoOcupacional> filas = getListAll(
				getDao().getListLoadAll(getExampleBuilder(filaFilter).exampleStatusDiferenteEncerrado()));
		
		if(filas.getTotal() > 0) {
			Date now = Helper.getNow();
			
			filas.getList().forEach(f -> {
				long time = f.getInicio().getTime();
				
				f.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ENCERRADO_AUTOMATICAMENTE);
				
				if(f.getAtualizacoes() != null)
					for(FilaAtendimentoOcupacionalAtualizacao a : f.getAtualizacoes())
						time += a.getTempo() * (60 * 1000);
				else
					f.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
				
				f.setFim(new Date());
				f.getFim().setTime(time);
				f.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
						.filaAtendimentoOcupacional(f)
						.status(f.getStatus())
						.tempo(calcularTempoAtualizacao(f))
						.get());
				f.setAtualizacao(now);
			});
			
			saveList(filas.getList());
		}
	}
	
	public List<Atendimento> entrar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1
		verificacaoInicial(fila);
		
		// 2 - CANCELAR AS TAREFAS DO GRUPO DE SERVIÇO ATEND. OCUPACIONAL,
		// DA EQUIPE DO PROFISSIONAL, ONDE NÃO EXISTA CHECK-IN REALIZADO PELO CLIENTE
		TarefaBo.getInstance().gerarCancelamentoAtendimento();
		
		// 3 - ENCERRAR FILA DO DIA ANTERIOR
		encerrarAutomaticamente(fila);
		
		// 4 - VERIFICAR SE JÁ ESTÁ NA FILA COM STATUS DIFERENTE DE ENCERRADO
		Date today = Helper.getToday();
		
		FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(1);
		filaFilter.setProfissional(new ProfissionalFilter());
		filaFilter.getProfissional().setId(fila.getProfissional().getId());
		filaFilter.setInicio(new DateFilter());
		filaFilter.getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filaFilter.getInicio().setInicio(today);
		
		PagedList<FilaAtendimentoOcupacional> filaAtendimentoOcupacionais = 
				getList(getExampleBuilder(filaFilter).exampleStatusDiferenteEncerrado(),
						this.functionLoadLocalizacao);
		
		if(filaAtendimentoOcupacionais.getTotal() > 0)
			throw new Exception("O Profissional já está na Fila de Atendimento da Localização: "+
					filaAtendimentoOcupacionais.getList().get(0).getLocalizacao().getNome()+".");
		
		// 5 - INSTANCIAR FILA
		fila.setInicio(Helper.getNow());
		fila.setAtualizacao(fila.getInicio());
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
		
		// 6 - INSERIR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR FILA DO DIA, DO LOCAL, ATUALIZADA		
		return obterListaAtual(fila);
	}
	
	private List<Atendimento> obterListaAtual(FilaAtendimentoOcupacional fila) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception{
		
		AtendimentoFilter filter = new AtendimentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
		filter.getFilaAtendimentoOcupacional().setLocalizacao(new LocalizacaoFilter());
		filter.getFilaAtendimentoOcupacional().getLocalizacao().setId(fila.getLocalizacao().getId());
		filter.getFilaAtendimentoOcupacional().setInicio(new DateFilter());
		filter.getFilaAtendimentoOcupacional().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filter.getFilaAtendimentoOcupacional().getInicio().setInicio(Helper.getToday());
		filter.setTarefa(new TarefaFilter());
		filter.getTarefa().setStatus(StatusTarefa.getInstance().EXECUCAO);
		
		PagedList<Atendimento> p = new PagedList<Atendimento>(); 
		
		try {			
			p = AtendimentoBo.getInstance().getListLoadAll(filter);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p.getList();
	}
	
	private FilaAtendimentoOcupacional obterFilaDoProfissionalNaLocalizacao(FilaAtendimentoOcupacional fila) throws Exception {
		
		FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(1);
		filaFilter.setProfissional(new ProfissionalFilter());
		filaFilter.getProfissional().setId(fila.getProfissional().getId());
		filaFilter.setLocalizacao(new LocalizacaoFilter());
		filaFilter.getLocalizacao().setId(fila.getLocalizacao().getId());
		filaFilter.setInicio(new DateFilter());
		filaFilter.getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filaFilter.getInicio().setInicio(Helper.getToday());
		
		PagedList<FilaAtendimentoOcupacional> filaAtendimentoOcupacionais = 
				getList(filaFilter, this.functionLoadAll);
		
		if(filaAtendimentoOcupacionais.getTotal() == 0)
			throw new Exception("O Profissional não está na Fila de Atendimento.");
		
		return filaAtendimentoOcupacionais.getList().get(0);
	}
	
	private FilaAtendimentoOcupacional obterFilaDoProfissional(FilaAtendimentoOcupacional fila) throws Exception {
		
		FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(1);
		filaFilter.setProfissional(new ProfissionalFilter());
		filaFilter.getProfissional().setId(fila.getProfissional().getId());
		filaFilter.setInicio(new DateFilter());
		filaFilter.getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filaFilter.getInicio().setInicio(Helper.getToday());
		
		PagedList<FilaAtendimentoOcupacional> filaAtendimentoOcupacionais = 
				getList(filaFilter, this.functionLoadAll);
		
		if(filaAtendimentoOcupacionais.getTotal() > 0)
			filaAtendimentoOcupacionais.getList().get(0);
		
		return fila;
	}
	
	public List<Atendimento> voltar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissionalNaLocalizacao(fila);
		
		// 4 - CHECAR STATUS
		if(!fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL) &&
				!fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().ALMOCO) &&
				!fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().ENCERRADO))
			throw new Exception("Não é possível voltar para a fila de atendimento. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		fila.setAtualizacao(Helper.getNow());
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterListaAtual(fila);
	}
	
	public List<Atendimento> pausar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissionalNaLocalizacao(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		fila.setAtualizacao(Helper.getNow());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterListaAtual(fila);
	}
	
	public List<Atendimento> registrarAlmoco(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissionalNaLocalizacao(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ALMOCO);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		fila.setAtualizacao(Helper.getNow());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterListaAtual(fila);
	}
	
	public List<Atendimento> encerrar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissionalNaLocalizacao(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ENCERRADO);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		fila.setAtualizacao(Helper.getNow());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterListaAtual(fila);
	}
	
	public List<Atendimento> atualizarLista(FilaAtendimentoOcupacional fila) throws Exception{
		if(fila.getLocalizacao() != null && fila.getLocalizacao().getId() > 0) {
			Atendimento atendimento = new Atendimento();
			atendimento.setFilaAtendimentoOcupacional(fila);
			return AtendimentoBo.getInstance().getListFilaAtendimentoLocalizacaoDoMomento(atendimento);
		}
		return new ArrayList<Atendimento>();
	}
	
	public Atendimento atualizar(Atendimento atendimento) throws Exception {
		
		FilaAtendimentoOcupacional fila = getBuilder(atendimento.getFilaAtendimentoOcupacional()).loadLocalizacao()
				.getEntity();
		
		//VERIFICAR SE INFORMOU O PROFISSIONAL
		if(fila.getProfissional() == null)
			throw new Exception("É necessário informar o Profissional para atualizar a Fila de Atendimento.");
		
		//VERIFICAR SE EXISTE FILA APENAS PELO PROFISSIONAL.
		fila = obterFilaDoProfissional(fila);
		
		if(fila.getId() == 0) {
			if(fila.getLocalizacao() == null)
				throw new Exception("É necessário informar a Localização para entrar na Fila de Atendimento.");
			
			fila = obterFilaDoProfissionalNaLocalizacao(fila);
		}
		
		if(fila.getId() > 0) {
			//VERIFICAR SE HÁ ALGUM ATENDIMENTO PARA ESTE PROFISSIONAL,
			//CUJA TAREFA DIFERENTE NÃO CONCLUÍDA NEM CANCELADA
			AtendimentoFilter atendimentoFilter = new AtendimentoFilter();
			atendimentoFilter.setPageNumber(1);
			atendimentoFilter.setPageSize(1);
			atendimentoFilter.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
			atendimentoFilter.getFilaAtendimentoOcupacional().setId(fila.getId());
			atendimentoFilter.setTarefa(new TarefaFilter());
			
			PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance()
					.getListLoadAllTarefaStatusNaoConcluidoCancelado(atendimentoFilter);
			
			if(atendimentos.getTotal() > 0) {
				Atendimento atendimentoAux = atendimentos.getList().get(0);
				atendimentoAux.getFilaEsperaOcupacional().setEmpregado(EmpregadoBo
						.getInstance().getById(atendimentoAux.getFilaEsperaOcupacional().getEmpregado().getId()));
				
				if(atendimento.getFilaEsperaOcupacional().getFichaColeta() != null
						&& atendimento.getFilaEsperaOcupacional().getFichaColeta().getId() > 0) {
					atendimentoAux.getFilaEsperaOcupacional().setFichaColeta(
							atendimento.getFilaEsperaOcupacional().getFichaColeta());					
				}
				
				return atendimentoAux;
			}else
				atendimento = new Atendimento();
			
			atendimento.setFilaAtendimentoOcupacional(fila);
		}else
			atendimento = new Atendimento();
		
		return atendimento;
	}
	
	protected long calcularTempoAtualizacao(FilaAtendimentoOcupacional fila) {
		return (Helper.getNow().getTime() - fila.getAtualizacao().getTime()) / (60 * 1000) % 60;
	}
	
	public PagedList<FilaAtendimentoOcupacional> getListAll(FilaAtendimentoOcupacionalFilter filter) throws Exception {
		return super.getList(filter, this.functionLoadAll);
	}
	
	public PagedList<FilaAtendimentoOcupacional> 
		getListAll(PagedList<FilaAtendimentoOcupacional> pagedList) throws Exception {
		return super.getList(pagedList, this.functionLoadAll);
	}
}
