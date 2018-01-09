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
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.persistence.FilaAtendimentoOcupacionalDao;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;

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
						if(fila.getProfissional() == null)
							throw new Exception("É necessário informar o Profissional para entrar na Fila de Atendimento.");
	}
	
	public List<FilaAtendimentoOcupacional> entrar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2
		verificacaoInicial(fila);
		
		// 3 - VERIFICAR SE JÁ ESTÁ NA FILA
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
				getList(filaFilter,this.functionLoadLocalizacao);
		
		if(filaAtendimentoOcupacionais.getTotal() > 0)
			throw new Exception("O Profissional já está na Fila de Atendimento da Localização: "+
					filaAtendimentoOcupacionais.getList().get(0).getLocalizacao().getNome()+".");
		
		// 4 - INSTANCIAR FILA
		fila.setInicio(Helper.getNow());
		fila.setAtualizacao(fila.getInicio());
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);
		
		// 5 - INSERIR NO BANCO
		getDao().save(fila);
		
		// 6 - RETORNAR FILA DO DIA, DO LOCAL, ATUALIZADA		
		return obterFilaAtual(fila);
	}
	
	private List<FilaAtendimentoOcupacional> obterFilaAtual(FilaAtendimentoOcupacional fila) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception{
		FilaAtendimentoOcupacionalFilter filaFilter = new FilaAtendimentoOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(Integer.MAX_VALUE);
		filaFilter.setLocalizacao(new LocalizacaoFilter());
		filaFilter.getLocalizacao().setId(fila.getLocalizacao().getId());
		filaFilter.setInicio(new DateFilter());
		filaFilter.getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		filaFilter.getInicio().setInicio(Helper.getToday());
		return getList(filaFilter).getList();
	}
	
	private FilaAtendimentoOcupacional obterFilaDoProfissional(FilaAtendimentoOcupacional fila) throws Exception {
		
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
	
	public List<FilaAtendimentoOcupacional> pausar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissional(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setAtualizacao(Helper.getNow());
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterFilaAtual(fila);
	}
	
	public List<FilaAtendimentoOcupacional> registrarAlmoco(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissional(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setAtualizacao(Helper.getNow());
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ALMOCO);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterFilaAtual(fila);
	}
	
	public List<FilaAtendimentoOcupacional> encerrar(FilaAtendimentoOcupacional fila) throws Exception{
		
		// 1, 2 
		verificacaoInicial(fila);
		
		// 3 - OBTER A FILA
		fila = obterFilaDoProfissional(fila);
		
		// 4 - CHECAR STATUS
		if(fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO) ||
				fila.getStatus().equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))
			throw new Exception("Não é possível alterar a fila de atendimento do Profissional. "
					+ "Status: "+fila.getStatus());
		
		// 5 - ATUALIZAR
		if(fila.getAtualizacoes() == null)
			fila.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());
		
		fila.setAtualizacao(Helper.getNow());
		fila.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ENCERRADO);
		fila.getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory.newInstance()
				.filaAtendimentoOcupacional(fila)
				.status(fila.getStatus())
				.tempo(calcularTempoAtualizacao(fila))
				.get());
		
		
		// 6 - SALVAR NO BANCO
		getDao().save(fila);
		
		// 7 - RETORNAR LISTA ATUALIZADA
		return obterFilaAtual(fila);
	}
	
	public Atendimento atualizar(FilaAtendimentoOcupacional fila) throws Exception {
		
		verificacaoInicial(fila);
		
		if(fila.getId() == 0)
			fila = obterFilaDoProfissional(fila);
		
		//VERIFICAR SE HÁ ALGUM ATENDIMENTO PARA ESTE PROFISSIONAL,
		//CUJO STATUS SEJA AGUARDANDO EMPREGADO
		AtendimentoFilter atendimentofilter = new AtendimentoFilter();
		atendimentofilter.setPageNumber(1);
		atendimentofilter.setPageSize(1);
		atendimentofilter.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacionalFilter());
		atendimentofilter.getFilaAtendimentoOcupacional().setId(fila.getId());
		atendimentofilter.getFilaAtendimentoOcupacional().setStatus(
				StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO);
		
		PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance()
				.getListLoadAll(atendimentofilter);
		
		if(atendimentos.getTotal() > 0)
			return atendimentos.getList().get(0);
		
		return null;
	}
	
	protected long calcularTempoAtualizacao(FilaAtendimentoOcupacional fila) {
		return (Helper.getNow().getTime() - fila.getAtualizacao().getTime()) / (60 * 1000) % 60;
	}
}
