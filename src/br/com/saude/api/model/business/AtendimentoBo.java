package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.OrderFilter;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.AtendimentoBuilder;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.creation.builder.example.AtendimentoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.TarefaExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.AtendimentoFactory;
import br.com.saude.api.model.creation.factory.entity.EmailFactory;
import br.com.saude.api.model.creation.factory.entity.FilaAtendimentoOcupacionalAtualizacaoFactory;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Aptidao;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.AsoAvaliacao;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Atividade;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;
import br.com.saude.api.model.entity.po.AvaliacaoFisicaAtividadeFisica;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.AtendimentoDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.Sexo;
import br.com.saude.api.util.constant.StatusAso;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TipoAtividadeFisica;
import br.com.saude.api.util.constant.TipoConvocacao;
import br.com.saude.api.util.constant.GrupoPerguntaFichaColeta;

@SuppressWarnings("deprecation")
public class AtendimentoBo extends
		GenericBo<Atendimento, AtendimentoFilter, AtendimentoDao, AtendimentoBuilder, AtendimentoExampleBuilder> {

	protected Function<AtendimentoBuilder, AtendimentoBuilder> functionLoadAso;
	protected Function<AtendimentoBuilder, AtendimentoBuilder> functionLoadAllAtualizacoes;
	private static AtendimentoBo instance;

	private AtendimentoBo() {
		super();
	}

	public static AtendimentoBo getInstance() {
		if (instance == null)
			instance = new AtendimentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadTarefa().loadTriagens().loadAso().loadQuestionario().loadAvaliacaoFisica().loadAvaliacaoHigieneOcupacional();
		};

		this.functionLoadAllAtualizacoes = builder -> {
			return this.functionLoadAll.apply(builder).loadFilaAtendimentoOcupacional();
		};
	}

	@Override
	public Atendimento getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	public Atendimento getByIdLoadAll(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAllAtualizacoes);
	}

	public PagedList<Atendimento> getListLoadAll(AtendimentoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), this.functionLoadAll);
	}

	public PagedList<Atendimento> getListLoadAllTarefaStatusNaoConcluidoCancelado(AtendimentoFilter filter)
			throws Exception {
		return super.getList(
				getDao().getListLoadAll(getExampleBuilder(filter).exampleTarefaStatusNaoConcluidoCancelado()),
				this.functionLoadAllAtualizacoes);
	}

	public List<Atendimento> getListFilaAtendimentoLocalizacaoDoMomento(Atendimento atendimento)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		return getDao().getListFilaAtendimentoLocalizacaoDoMomento(atendimento);
	}

	protected Atendimento addAtualizacao(Atendimento atendimento) {

		if (atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() == null)
			atendimento.getFilaAtendimentoOcupacional()
					.setAtualizacoes(new ArrayList<FilaAtendimentoOcupacionalAtualizacao>());

		atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().add(FilaAtendimentoOcupacionalAtualizacaoFactory
				.newInstance().filaAtendimentoOcupacional(atendimento.getFilaAtendimentoOcupacional())
				.status(atendimento.getFilaAtendimentoOcupacional().getStatus()).tempo(FilaAtendimentoOcupacionalBo
						.getInstance().calcularTempoAtualizacao(atendimento.getFilaAtendimentoOcupacional()))
				.get());

		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());

		if (atendimento.getFilaEsperaOcupacional().getFichaColeta() != null
				&& atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas() != null) {
			atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r -> {
				r.setFicha(atendimento.getFilaEsperaOcupacional().getFichaColeta());
			});
		}

		return atendimento;
	}

	public Atendimento merge(Atendimento a) throws Exception {

		Atendimento atendimentoAux = a;

		if (a.getId() > 0)
			a = getByIdLoadAll(a.getId());

		if ( atendimentoAux.getAvaliacaoFisica() != null ) {
			atendimentoAux.getAvaliacaoFisica().getAvaliacaoFisicaAtividadeFisicas().forEach(afaf -> 
				afaf.setAvaliacaoFisica(atendimentoAux.getAvaliacaoFisica()));
			atendimentoAux.getAvaliacaoFisica().setAtendimento(atendimentoAux);
			a.setAvaliacaoFisica(atendimentoAux.getAvaliacaoFisica());
		}
		
		if (atendimentoAux.getAso() != null && atendimentoAux.getAso().getId() != 0)
			a.setAso(atendimentoAux.getAso());
		
		FichaColeta fichaColeta = a.getFilaEsperaOcupacional().getFichaColeta();

		if (fichaColeta != null) {
			a.getFilaEsperaOcupacional().setFichaColeta(atendimentoAux.getFilaEsperaOcupacional().getFichaColeta());
			a.getFilaEsperaOcupacional()
					.setRiscoPotencial(atendimentoAux.getFilaEsperaOcupacional().getRiscoPotencial());
			a.setTriagens(atendimentoAux.getTriagens());

			Atendimento atendimento = a;

			a.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r -> {
				if (r.getItens() != null)
					r.getItens().forEach(i -> i.setResposta(r));

				// ATUALIZAR A VERSÃO
				Optional<RespostaFichaColeta> resposta = fichaColeta.getRespostaFichaColetas().stream()
						.filter(rF -> rF.getId() == r.getId()).findFirst();

				if (resposta != null && resposta.isPresent())
					r.setVersion(resposta.get().getVersion());
			});

			List<RiscoEmpregado> riscos = a.getFilaEsperaOcupacional().getRiscoPotencial().getRiscoEmpregados();

			if (riscos != null)
				for (int i = 0; i < riscos.size(); i++) {
					riscos.set(i, RiscoEmpregadoBo.getInstance().getByIdAll(riscos.get(i).getId()));
					riscos.get(i).setRiscoPotencial(a.getFilaEsperaOcupacional().getRiscoPotencial());

					if (riscos.get(i).getTriagens() != null)
						for (Triagem t : riscos.get(i).getTriagens())
							t.setRiscoEmpregado(riscos.get(i));
				}

			if (a.getTriagens() != null)
				a.getTriagens().forEach(t -> {
					if (t.getDiagnostico() != null && t.getDiagnostico().getId() == 0)
						t.setDiagnostico(null);

					if (t.getEquipeAbordagem() != null && t.getEquipeAbordagem().getId() == 0)
						t.setEquipeAbordagem(null);

					if (t.getIntervencao() != null && t.getIntervencao().getId() == 0)
						t.setIntervencao(null);

					if (t.getRiscoEmpregado() != null && t.getRiscoEmpregado().getId() == 0)
						t.setRiscoEmpregado(null);

					t.setAtendimento(atendimento);
				});

			return a;
		} else {
			if (atendimentoAux.getFilaEsperaOcupacional().getRiscoPotencial() != null
					&& atendimentoAux.getFilaEsperaOcupacional().getRiscoPotencial().getId() == 0)
				atendimentoAux.getFilaEsperaOcupacional().setRiscoPotencial(null);

			if (atendimentoAux.getAso() != null && atendimentoAux.getAso().getId() == 0)
				atendimentoAux.setAso(null);
		}

		return atendimentoAux;
	}

	public Atendimento iniciar(Atendimento atendimento) throws Exception {

		atendimento = merge(atendimento);

		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO);
		atendimento = gerarAso(atendimento);
		generateAvaliacaoFisica(atendimento);
		atendimento = save(addAtualizacao(atendimento), this.functionLoadAll);
		atendimento = getById(atendimento.getId());
		atendimento.getTriagens().sort(new Comparator<Triagem>() {
			@Override
			public int compare(Triagem arg0, Triagem arg1) {
				return arg0.getIndicadorSast().getCodigo().compareTo(arg1.getIndicadorSast().getCodigo());
			}
		});
		return atendimento;
	}

	public Atendimento gerarAso(Atendimento atendimento) throws Exception {

		String tipoAtendimento = getTipoAtendimento(atendimento);

		if (atendimento.getTarefa().getEquipe().getAbreviacao().equals("MED")
				&& (tipoAtendimento == TipoConvocacao.ADMISSIONAL || tipoAtendimento == TipoConvocacao.DEMISSIONAL
						|| tipoAtendimento == TipoConvocacao.PERIODICO
						|| tipoAtendimento == TipoConvocacao.RETORNO_AO_TRABALHO
						|| tipoAtendimento == TipoConvocacao.MUDANCA_DE_FUNCAO
						|| tipoAtendimento == "VALIDAÇÃO DE ASO")) {

			Aso aso = atendimento.getAso() != null && atendimento.getAso().getId() > 0 ? atendimento.getAso()
					: new Aso();
			aso.setEmpregado(EmpregadoBo.getInstance()
					.getByIdLoadTipoGrupoMonitoramento(atendimento.getFilaEsperaOcupacional().getEmpregado().getId()));
			aso.setAtendimento(new Atendimento());
			aso.getAtendimento().setId(atendimento.getId());
			aso.setData(Helper.getNow());
			aso.setStatus(StatusAso.PENDENTE_AUDITORIA);
			aso.setValidade(getValidadeAso(atendimento, Helper.getNow()));
			aso.setAptidoes(new ArrayList<Aptidao>());
			aso.setAsoAvaliacoes(new ArrayList<AsoAvaliacao>());

			aso.getEmpregado().getGrupoMonitoramentos().stream().forEach(x -> {

				if (x.getAvaliacoes() != null) {
					x.getAvaliacoes().forEach(a -> {
						if (aso.getAsoAvaliacoes().stream().filter(y -> y.getDescricao().equals(a.getNome()))
								.count() == 0 && a.isAuditoriaMedico()) {
							AsoAvaliacao asoAvaliacao = new AsoAvaliacao();
							asoAvaliacao.setDescricao(a.getNome());
							asoAvaliacao.setAso(aso);
							aso.getAsoAvaliacoes().add(asoAvaliacao);
						}
					});
				}

				if (x.getTipoGrupoMonitoramento().getNome().equals("ATIVIDADES CRÍTICAS")) {
					Aptidao aptidao = new Aptidao();
					aptidao.setAso(aso);
					aptidao.setGrupoMonitoramento(x);
					aso.getAptidoes().add(aptidao);
				}
			});
			atendimento.setAso(aso);
		}
		return atendimento;
	}

	public Atendimento registrarAusencia(Atendimento atendimento) throws Exception {

		atendimento = merge(atendimento);

		FilaAtendimentoOcupacional fila = FilaAtendimentoOcupacionalBo.getInstance()
				.getBuilder(atendimento.getFilaAtendimentoOcupacional()).getEntity();

		// SETAR EMPREGADO COMO AGUARDANDO
		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().AUSENTE);

		// SETAR PROFISSIONAL COMO INDISPONÍVEL
		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);

		if (atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null)
			atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a -> a.setFila(fila));

		// GRAVAR TAREFA COMO ABERTA, REMOVENDO PROFISSIONAL

		Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
		Date fim = Date.from(inicio.toInstant());
		inicio.setHours(8);
		fim.setHours(16);

		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atendimento.getTarefa().setResponsavel(null);
		atendimento.getTarefa().setInicio(inicio);
		atendimento.getTarefa().setFim(fim);

		getDao().devolverPraFila(atendimento);

		return FilaAtendimentoOcupacionalBo.getInstance()
				.atualizar(AtendimentoFactory.newInstance().filaAtendimento(fila).get());
	}

	public Atendimento devolverPraFila(Atendimento atendimento) throws Exception {

		atendimento = merge(atendimento);

		FilaAtendimentoOcupacional fila = FilaAtendimentoOcupacionalBo.getInstance()
				.getBuilder(atendimento.getFilaAtendimentoOcupacional()).getEntity();

		// SETAR EMPREGADO COMO AGUARDANDO
		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);

		// SETAR PROFISSIONAL COMO INDISPONÍVEL
		atendimento.getFilaAtendimentoOcupacional().setAtualizacao(Helper.getNow());
		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);

		if (atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null)
			atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a -> a.setFila(fila));

		// GRAVAR TAREFA COMO ABERTA, REMOVENDO PROFISSIONAL

		Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
		Date fim = Date.from(inicio.toInstant());
		inicio.setHours(8);
		fim.setHours(16);

		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atendimento.getTarefa().setResponsavel(null);
		atendimento.getTarefa().setInicio(inicio);
		atendimento.getTarefa().setFim(fim);

		getDao().devolverPraFila(atendimento);

		return FilaAtendimentoOcupacionalBo.getInstance()
				.atualizar(AtendimentoFactory.newInstance().filaAtendimento(fila).get());
	}

	public Atendimento liberar(Atendimento atendimento) throws Exception {

		atendimento = merge(atendimento);

		if (!atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO))
			throw new Exception("Não é possível liberar o empregado. Status: "
					+ atendimento.getFilaAtendimentoOcupacional().getStatus());

		if (finalizouAtendimento(atendimento, Helper.getToday()))
			atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().FINALIZADO);
		else if (!atendimento.getFilaEsperaOcupacional().getStatus()
				.equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO))
			atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);

		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.getNow());

		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);

		atendimento = save(addAtualizacao(atendimento), this.functionLoadAll);
		atendimento = getById(atendimento.getId());

		atendimento.getTriagens().sort(new Comparator<Triagem>() {
			@Override
			public int compare(Triagem arg0, Triagem arg1) {
				return arg0.getIndicadorSast().getCodigo().compareTo(arg1.getIndicadorSast().getCodigo());
			}
		});

		return atendimento;
	}

	private Atendimento tratarAtendimento(Atendimento atendimento) throws Exception {

		if (atendimento.getFilaEsperaOcupacional().getFichaColeta() != null)
			atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r -> {
				r.setFicha(atendimento.getFilaEsperaOcupacional().getFichaColeta());

				if (r.getItens() != null)
					r.getItens().forEach(i -> {
						i.setResposta(r);
					});
			});

		if (atendimento.getFilaEsperaOcupacional().getRiscoPotencial() != null) {
			List<RiscoEmpregado> riscos = atendimento.getFilaEsperaOcupacional().getRiscoPotencial()
					.getRiscoEmpregados();

			if (riscos != null) {
				for (int i = 0; i < riscos.size(); i++) {
					riscos.set(i, RiscoEmpregadoBo.getInstance().getByIdAll(riscos.get(i).getId()));
					riscos.get(i).setRiscoPotencial(atendimento.getFilaEsperaOcupacional().getRiscoPotencial());

					int ii = i;
					riscos.get(i).getTriagens().forEach(t -> {
						t.setRiscoEmpregado(riscos.get(ii));
					});
				}
			}
		}

		return atendimento;
	}

	public Atendimento finalizarRetroativo(Atendimento atendimento) throws Exception {

		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO);

		atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().EM_ATENDIMENTO);

		atendimento = addAtualizacao(finalizar(atendimento, new Date(atendimento.getTarefa().getInicio().getTime())));

		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().ENCERRADO_AUTOMATICAMENTE);

		atendimento = tratarAtendimento(atendimento);
		atendimento = criarAso(atendimento);
		Atendimento aux = atendimento;
		if (aux.getFilaEsperaOcupacional().getRiscoPotencial() != null
				&& aux.getFilaEsperaOcupacional().getRiscoPotencial().getRiscoEmpregados() != null) {
			Optional<RiscoEmpregado> riscoEmpregado = aux.getFilaEsperaOcupacional().getRiscoPotencial()
					.getRiscoEmpregados().stream()
					.filter(r -> r.getEquipe().getId() == aux.getTarefa().getEquipe().getId() && r.isAtivo())
					.findFirst();

			if (riscoEmpregado != null && riscoEmpregado.isPresent()) {
				riscoEmpregado.get().setAtivo(false);
				riscoEmpregado.get().setTriagens(null);
			}
		}

		return save(atendimento);
	}

	public Servico servicoReavaliacaoPeriodico() throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("1001");
		servicoFilter.setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getListLoadAll(servicoFilter).getList().get(0);
		return servico;
	}

	public Servico servicoReavaliacaoFisicaBrigada() throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("1002");
		servicoFilter.setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getListLoadAll(servicoFilter).getList().get(0);
		return servico;
	}

	public Atendimento criarAso(Atendimento atendimento) throws Exception {
		if (atendimento.getAso() != null && atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe().getAbreviacao().contains("MED")) {
			
			atendimento.getAso().setAtendimento(atendimento);
			atendimento.getAso().setEmpregado(atendimento.getFilaEsperaOcupacional().getEmpregado());

			atendimento.setAso(AsoBo.getInstance().criarItensAuditoriaAso(atendimento.getAso()));

			if ((!atendimento.getAso().isPendente() && (!atendimento.getAso().isConvocado())) && atendimento.getAso()
					.getAptidoes().stream().filter(x -> x.getAptidaoAso().equals("INAPTO")).count() > 0) {

				atendimento.getAso().setConvocado(true);
				String tipoConvocacao = TipoConvocacao.REAVALIACAO_PERIODICO;

				if (!atendimento.getAso().isAusenciaExames()) {
					criarConvocacao(atendimento, tipoConvocacao);
				} else {
					Servico servico = null;

					if (atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().stream()
							.filter(x -> x.getPergunta().getCodigo().equals("0019")
									&& (x.getConteudo().equals("INSATISFATÓRIO") || x.getConteudo().equals("PENDENTE")))
							.count() > 0) {

						servico = servicoReavaliacaoFisicaBrigada();
					} else
						servico = servicoReavaliacaoPeriodico();

					Date inicio = Date.from(atendimento.getAso().getDataRestricao().toInstant());
					Atendimento atendimentoAux = new Atendimento();
					Tarefa tarefa = new Tarefa();
					tarefa.setCliente(atendimento.getTarefa().getCliente());
					tarefa.setInicio(inicio);
					tarefa.setServico(servico);
					atendimentoAux.setTarefa(tarefa);

					registrarSolicitacaoReavaliacaoPeriodico(atendimentoAux);
				}
			}

		}
		return atendimento;
	}
	

	public void registrarSolicitacaoReavaliacaoPeriodico(Atendimento atendimento) throws Exception {

		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		Tarefa tarefaAux = null;

		for (Atividade atividade : atendimento.getTarefa().getServico().getAtividades()) {

			tarefaAux = new Tarefa();
			Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
			Date fim = Date.from(inicio.toInstant());
			inicio.setHours(8);
			fim.setHours(16);
			tarefaAux.setInicio(inicio);
			tarefaAux.setFim(fim);
			tarefaAux.setCliente(atendimento.getTarefa().getCliente());
			tarefaAux.setServico(atendimento.getTarefa().getServico());
			tarefaAux.setAtualizacao(Helper.getNow());
			tarefaAux.setEquipe(atividade.getEquipe());
			tarefaAux.setStatus(StatusTarefa.getInstance().ABERTA);
			tarefas.add(tarefaAux);
		}

		TarefaBo.getInstance().saveList(tarefas);
		notifyEmployee(atendimento);

	}

	private void criarConvocacao(Atendimento atendimento, String tipoConvocacao) throws Exception {

		Convocacao convocacao = new Convocacao();

		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(atendimento.getAso().getDataRestricao());

		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		convocacaoFilter.setTitulo(
				tipoConvocacao + " " + (dtCalendar.get(Calendar.MONTH) + 1) + "." + dtCalendar.get(Calendar.YEAR));
		convocacaoFilter.setTipo(tipoConvocacao);
		convocacaoFilter.setPageNumber(1);
		convocacaoFilter.setPageSize(1);

		PagedList<Convocacao> convocacoes = ConvocacaoBo.getInstance().getList(convocacaoFilter);
		if (convocacoes.getTotal() > 0) {
			convocacao = convocacoes.getList().get(0);
			convocacao = ConvocacaoBo.getInstance().getById(convocacao.getId());
		} else {

			dtCalendar.set(Calendar.DATE, 1);
			convocacao.setTipo(tipoConvocacao);
			convocacao.setTitulo(convocacaoFilter.getTitulo());
			convocacao.setInicio(dtCalendar.getTime());
			dtCalendar.setTime(Helper.cloneDate(dtCalendar.getTime()));
			dtCalendar.set(Calendar.DATE, 31);
			convocacao.setFim(dtCalendar.getTime());
		}

		if (convocacao.getEmpregadoConvocacoes() == null)
			convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());

		EmpregadoConvocacao empregadoConvocacao = new EmpregadoConvocacao();
		empregadoConvocacao.setEmpregado(atendimento.getTarefa().getCliente());
		empregadoConvocacao.setConvocacao(convocacao);
		empregadoConvocacao.setDataConvocacao(Helper.getNow());
		empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());

		if (atendimento.getAso() != null && atendimento.getAso().getExamesConvocacao().size() > 0) {
			for (Exame exame : atendimento.getAso().getExamesConvocacao()) {
				EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
				empregadoConvocacaoExame.setExame(exame);
				empregadoConvocacaoExame.setEmpregadoConvocacao(empregadoConvocacao);
				empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
			}
		}

		convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);

		ProfissiogramaFilter profissiogramaFilter = new ProfissiogramaFilter();
		profissiogramaFilter.setNome(convocacaoFilter.getTipo());
		profissiogramaFilter.setPageSize(1);
		profissiogramaFilter.setPageNumber(1);

		PagedList<Profissiograma> profissiogramas = ProfissiogramaBo.getInstance().getList(profissiogramaFilter);

		if (profissiogramas.getTotal() == 0)
			throw new Exception("Não foi possível encontrar o profissiograma " + convocacaoFilter.getTitulo());

		convocacao.setProfissiograma(profissiogramas.getList().get(0));
		convocacao.setGerenciaConvocacoes(new ArrayList<GerenciaConvocacao>());
		convocacao = ConvocacaoBo.getInstance().save(convocacao);
		convocacao = ConvocacaoBo.getInstance().getById(convocacao.getId());

		convocacao.getEmpregadoConvocacoes().sort(new Comparator<EmpregadoConvocacao>() {
			@Override
			public int compare(EmpregadoConvocacao arg0, EmpregadoConvocacao arg1) {
				return new Integer(arg1.getId()).compareTo(arg0.getId());
			}
		});

		convocacao.getEmpregadoConvocacoes().get(0).setSelecionado(true);

		String pathAnexos = ConvocacaoBo.getInstance().processarConvocacaoPath(convocacao);

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(atendimento.getTarefa().getCliente());

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataRestricao = formatter.format(atendimento.getAso().getDataRestricao().getTime());

		EmailFactory emailFactory = EmailFactory.newInstance().assunto(tipoConvocacao)
				.conteudo("Prezado(a),</br></br> informamos a necessidade da realização do " + tipoConvocacao
						+ " no serviço de saúde no dia " + dataRestricao + ".")
				.dataEnvio(Helper.getNow()).destinatarios(empregados).anexos(pathAnexos);
		EmailBo.getInstance().save(emailFactory.get());

	}

	public Atendimento finalizar(Atendimento atendimento) throws Exception {
		atendimento = merge(atendimento);
		atendimento = addAtualizacao(finalizar(atendimento, Helper.getNow()));
		atendimento.getTarefa().setFim(Helper.getNow());
		atendimento = criarAso(atendimento);
		return save(atendimento);
	}

	public Atendimento finalizarPausar(Atendimento atendimento) throws Exception {

		atendimento = merge(atendimento);
		atendimento = addAtualizacao(finalizar(atendimento, Helper.getNow()));
		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().INDISPONIVEL);
		atendimento.getTarefa().setFim(Helper.getNow());
		atendimento = criarAso(atendimento);

		return save(atendimento);
	}

	private Atendimento finalizar(Atendimento atendimento, Date data) throws Exception {

		if (!(atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO))
				&& !(atendimento.getFilaAtendimentoOcupacional().getStatus()
						.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES)))
			throw new Exception("Não é possível finalizar o atendimento. Status: "
					+ atendimento.getFilaAtendimentoOcupacional().getStatus());

		boolean liberado = atendimento.getFilaAtendimentoOcupacional().getStatus()
				.equals(StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES);

		if (finalizouAtendimento(atendimento, Helper.cloneDate(data))) {
			if (!liberado)
				atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().FINALIZADO);

		} else if (!liberado)
			if (!atendimento.getFilaEsperaOcupacional().getStatus()
					.equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO)
					&& !atendimento.getFilaEsperaOcupacional().getStatus()
							.equals(StatusFilaEsperaOcupacional.getInstance().AUSENTE))
				atendimento.getFilaEsperaOcupacional().setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);

		// VERIFICA SE FOI ATENDIMENTO MÉDICO PARA CRIAÇÃO DO ASO
		// atendimento = verificarCriacaoAso(atendimento, Helper.cloneDate(data));

		atendimento.getFilaEsperaOcupacional().setAtualizacao(Helper.cloneDate(data));

		atendimento.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().DISPONIVEL);

		atendimento.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);

		atendimento = gerarRisco(atendimento);
		return atendimento;
	}

	protected Atendimento verificarCriacaoAso(Atendimento atendimento, Date date) {

		if (atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe().getAbreviacao().contains("MED")) {

			atendimento.setAso(new Aso());
			atendimento.getAso().setAtendimento(atendimento);
			atendimento.getAso().setData(date);
			atendimento.getAso().setEmpregado(atendimento.getFilaEsperaOcupacional().getEmpregado());
			atendimento.getAso().setStatus(StatusAso.PENDENTE_AUDITORIA);
			atendimento.getAso().setValidade(getValidadeAso(atendimento, date));
		} else
			atendimento.setAso(null);

		return atendimento;
	}

	protected Atendimento gerarRisco(Atendimento atendimento) {

		if (atendimento.getTriagens() != null && atendimento.getTriagens().size() > 0) {
			List<Triagem> triagens = atendimento.getTriagens().stream().filter(t -> t.getIndice() >= 0)
					.collect(Collectors.toList());

			// CARREGAR OS INDICADORES
			triagens.forEach(t -> {
				try {
					t.setIndicadorSast(IndicadorSastBo.getInstance().getById(t.getIndicadorSast().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			double r0 = 0.95
					- (triagens.stream().flatMapToInt(t -> IntStream.of(t.getIndice())).average().getAsDouble() / 4.3);

			double r01 = (Math.log10(
					atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe().getPrioridadeSast() + 1)
					/ (atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe().getPrioridadeSast()
							+ 5))
					/ (atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe().getPrioridadeSast()
							+ triagens.size());

			double r1 = r0 + r01;

			if (triagens.stream().filter(t -> t.getIndice() <= t.getIndicadorSast().getCritico()).count() > 0)
				r1 = 0.95 + r01;

			RiscoEmpregado risco = new RiscoEmpregado();
			risco.setRiscoPotencial(atendimento.getFilaEsperaOcupacional().getRiscoPotencial());
			risco.setProfissional(atendimento.getFilaAtendimentoOcupacional().getProfissional());
			risco.setEquipe(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe());
			risco.setTriagens(atendimento.getTriagens());
			risco.setAtivo(true);
			risco.setValor(r1);
			risco.setData(Helper.getToday());
			risco.setStatus(StatusRiscoEmpregado.getInstance().REALIZADO);

			atendimento.getTriagens().forEach(t -> {
				t.setRiscoEmpregado(risco);
				t.setAtendimento(atendimento);
			});
		}

		return atendimento;
	}

	private Date getValidadeAso(Atendimento atendimento, Date data) {

		int meses = 0;
		String tipoTipoAtendimento = getTipoAtendimento(atendimento);

		switch (tipoTipoAtendimento) {
		case "VALIDAÇÃO DE ASO":
		case TipoConvocacao.PERIODICO:
		case TipoConvocacao.RETORNO_AO_TRABALHO:
		case TipoConvocacao.ADMISSIONAL:
		case TipoConvocacao.DEMISSIONAL:
		case TipoConvocacao.MUDANCA_DE_FUNCAO:
			meses = 12;
			break;
		}

		return Date.from(LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).plusMonths(meses)
				.minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
	}

	private boolean finalizouAtendimento(Atendimento atendimento, Date today) throws Exception {

		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(2);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setId(atendimento.getFilaEsperaOcupacional().getEmpregado().getId());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		tarefaFilter.getServico().setCodigo(atendimento.getTarefa().getServico().getCodigo());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);

		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
		tarefaFilter.getInicio().setInicio(Helper.cloneDate(today));
		tarefaFilter.getInicio().setFim(calendar.getTime());

		PagedList<Tarefa> tarefas = TarefaBo.getInstance()
				.getList(TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluido());

		// VERIFICAR PENDÊNCIA
		Tarefa tarefaPendencia = FilaEsperaOcupacionalBo.getInstance()
				.checkPendecia(atendimento.getFilaEsperaOcupacional().getEmpregado(), Helper.cloneDate(today));

		if (tarefaPendencia != null) {
			tarefaFilter.setInicio(new DateFilter());
			tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
			tarefaFilter.getInicio().setInicio(Helper.cloneDate(tarefaPendencia.getInicio()));
			tarefaFilter.getInicio().setFim(Helper.cloneDate(tarefaPendencia.getFim()));

			PagedList<Tarefa> t = TarefaBo.getInstance()
					.getList(TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluidoCancelado());

			if (t.getTotal() > 0) {
				if (tarefas.getList() == null)
					tarefas.setList(new ArrayList<Tarefa>());

				tarefas.getList().addAll(t.getList());
				tarefas.setTotal(tarefas.getTotal() + t.getList().size());
			}
		}

		// VERIFICAR SE RETORNOU A PRÓPRIA TAREFA
		if (tarefas.getTotal() > 0) {
			if (tarefas.getList().stream().filter(t -> t.getId() == atendimento.getTarefa().getId()).count() > 0)
				tarefas.setTotal(tarefas.getTotal() - 1);
		}

		return tarefas.getTotal() <= 0;
	}

	public String getTipoAtendimento(Atendimento atendimento) {
		switch (atendimento.getTarefa().getServico().getGrupo()) {
		case GrupoServico.ATENDIMENTO_OCUPACIONAL:

			switch (atendimento.getTarefa().getServico().getCodigo()) {
			// ADMISSIONAL
			case "0001":
				return TipoConvocacao.ADMISSIONAL;

			// DEMISSIONAL
			case "0002":
				return TipoConvocacao.DEMISSIONAL;

			// PERIÓDICO
			case "0003":
				return TipoConvocacao.PERIODICO;
			case "1003":
				return "VALIDAÇÃO DE ASO";

			// RETORNO AO TRABALHO
			case "0004":
				return TipoConvocacao.RETORNO_AO_TRABALHO;

			// MUDANÇA DE FUNÇÃO
			case "0005":
				return TipoConvocacao.MUDANCA_DE_FUNCAO;

			// ESPECIAL
			case "0006":
				return TipoConvocacao.ESPECIAL;

			// PERICIAL
			case "0007":
				return TipoConvocacao.PERICIAL;

			// AVALIAÇÃO DE CAPACIDADE LABORAL
			case "0008":

				break;
			case "1001":
			case "1002":
				return TipoConvocacao.REAVALIACAO_PERIODICO;

			}
			break;
		}
		return "***";
	}
	
	public EmpregadoConvocacaoFilter configureEmpregadoConvocacaoFilter(Atendimento atendimento) {
		String tipoAtendimento = getTipoAtendimento(atendimento);

		Date primeiroDiaAno = Helper.getToday();
		primeiroDiaAno.setDate(1);
		primeiroDiaAno.setMonth(0);
		primeiroDiaAno.setTime((primeiroDiaAno.getTime() / 1000) * 1000);

		// 1 - OBTER A CONVOCAÇÃO DO EMPREGADO
		EmpregadoConvocacaoFilter empConFilter = new EmpregadoConvocacaoFilter();
		empConFilter.setPageNumber(1);
		empConFilter.setPageSize(1);
		empConFilter.setEmpregado(new EmpregadoFilter());
		empConFilter.getEmpregado().setId(atendimento.getTarefa().getCliente().getId());
		empConFilter.setConvocacao(new ConvocacaoFilter());
		empConFilter.getConvocacao().setInicio(new DateFilter());
		empConFilter.getConvocacao().getInicio().setTypeFilter(TypeFilter.MAIOR_IGUAL);
		empConFilter.getConvocacao().getInicio().setInicio(primeiroDiaAno);

		if (tipoAtendimento.equals("VALIDAÇÃO DE ASO")) {
			empConFilter.getConvocacao().setTipo(TipoConvocacao.PERIODICO);
			empConFilter.getConvocacao().setTitulo("ADIANTADO");
		} else
			empConFilter.getConvocacao().setTipo(tipoAtendimento);
		return empConFilter;
	}
	
	
	public String registrarSolicitacaoAtendimento(Atendimento atendimento) throws Exception {

		// 1 - OBTER A CONVOCAÇÃO DO EMPREGADO, CUJA DATA INFORMADA ESTÁ NO PERÍODO
		// DO CRONOGRAMA, CUJO TIPO DA CONVOCAÇÃO CORRESPONDA AO SERVIÇO SELECIONADO
		EmpregadoConvocacaoFilter empConFilter = configureEmpregadoConvocacaoFilter(atendimento);
		PagedList<EmpregadoConvocacao> empConList = EmpregadoConvocacaoBo.getInstance().getList(empConFilter);

		if (empConList.getTotal() == 0)
			throw new Exception("Não foi possível solicitar o serviço, pois não existe "
					+ "convocação para o empregado cujo cronograma atenda à data informada.");

		// 2 - VERIFICAR SE O RESULTADO DOS EXAMES JÁ FOI AUDITADO
		if (!empConList.getList().get(0).isResultadoAuditado())
			throw new Exception("Não foi possível solicitar o serviço, pois não foi realizada "
					+ "a auditoria do resultado dos exames.");

		// 3 - VERIFICAR SE A SOLICITAÇÃO JÁ FOI REALIZADA PREVIAMENTE
		Date f = Date
				.from(LocalDateTime.ofInstant(atendimento.getTarefa().getInicio().toInstant(), ZoneId.systemDefault())
						.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

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

		if (ts.getTotal() > 0)
			throw new Exception("Não foi possível solicitar o serviço, pois já foi realizada "
					+ "uma solicitação para o mesmo dia.");

		// 4 - LOAD ALL DO SERVIÇO DA TAREFA DO ATENDIMENTO
		atendimento.getTarefa()
				.setServico(ServicoBo.getInstance().getById(atendimento.getTarefa().getServico().getId()));

		// 5 - INSTANCIAR UMA TAREFA PARA CADA ATIVIDADE DO SERVIÇO.
		// SERVIÇO PERIÓDICO RESERVA TODA A AGENDA DO DIA.
		Tarefa tarefa = null;
		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		for (Atividade atividade : atendimento.getTarefa().getServico().getAtividades()) {

			// VERIFICAR SE DEVE GERAR TAREGA PARA HO
			if (atividade.getEquipe().getAbreviacao().equals("HIG")
					&& empConList.getList().get(0).getEmpregado().getGerencia().isAusentePeriodico())
				continue;

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

		// 7 - NOTIFICAR EMPREGADO
		notifyEmployee(atendimento);

		return "Atendimento registrado com sucesso.";
	}
	
	public String registrarSolicitacaoExamePericial(Atendimento atendimento) throws Exception {

		if (atendimento.getTarefa().getEquipe() == null)
			throw new Exception("Equipe da tarefa do Exame Pericial deve existir.");

		// 3 - VERIFICAR SE A SOLICITAÇÃO JÁ FOI REALIZADA PREVIAMENTE
		Date f = Date
				.from(LocalDateTime.ofInstant(atendimento.getTarefa().getInicio().toInstant(), ZoneId.systemDefault())
						.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

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

		if (ts.getTotal() > 0)
			throw new Exception("Não foi possível solicitar o serviço, pois já foi realizada "
					+ "uma solicitação para o mesmo dia.");

		// 5 - INSTANCIAR UMA TAREFA PARA CADA ATIVIDADE DO SERVIÇO.
		// SERVIÇO PERIÓDICO RESERVA TODA A AGENDA DO DIA.
		Tarefa tarefa = null;

		Date inicio = Date.from(atendimento.getTarefa().getInicio().toInstant());
		Date fim = Date.from(inicio.toInstant());

		inicio.setHours(8);
		fim.setHours(16);

		tarefa = TarefaBuilder.newInstance(atendimento.getTarefa()).getEntity();
		tarefa.setAtualizacao(Helper.getNow());
		tarefa.setInicio(inicio);
		tarefa.setFim(fim);
		tarefa.setStatus(StatusTarefa.getInstance().ABERTA);

		// 6 - SALVAR NO BANCO
		TarefaBo.getInstance().save(tarefa);

		// 7 - NOTIFICAR EMPREGADO
		notifyEmployee(atendimento);

		return "Atendimento registrado com sucesso.";
	}

	private void notifyEmployee(Atendimento atendimento)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {

		EmailFactory email = EmailFactory.newInstance();
		List<Empregado> destinatarios = new ArrayList<Empregado>();
		destinatarios.add(atendimento.getTarefa().getCliente());

		new Helper();
		email.conteudo("Prezado(a),<br><br>informamos que foi agendado o " + "serviço "
				+ atendimento.getTarefa().getServico().getNome() + " para o dia "
				+ atendimento.getTarefa().getInicio().toLocaleString().split(" ")[0] + ".")
				.assunto(atendimento.getTarefa().getServico().getNome()).dataEnvio(Helper.getToday())
				.destinatarios(destinatarios);

		EmailBo.getInstance().save(email.get());
	}

	public Atendimento getComplementoAtendimentoAvulso(Atendimento atendimento) throws Exception {

		Tarefa tarefa = atendimento.getTarefa();

		atendimento.setFilaAtendimentoOcupacional(FilaAtendimentoOcupacionalBo.getInstance()
				.getById(atendimento.getFilaAtendimentoOcupacional().getId()));
		atendimento.setTarefa(TarefaBo.getInstance().getById(atendimento.getTarefa().getId()));
		atendimento.getTarefa().setInicio(new Date(tarefa.getInicio().getTime()));
		atendimento.getTarefa().setFim(new Date(tarefa.getFim().getTime()));
		atendimento.getTarefa().setAtualizacao(Helper.getNow());
		atendimento.getTarefa().setResponsavel(ProfissionalBo.getInstance().getById(tarefa.getResponsavel().getId()));

		Atendimento atendimentoAux = new Atendimento();

		// 1 - BUSCAR ATENDIMENTO PELO ID TAREFA
		AtendimentoFilter atendimentoFilter = new AtendimentoFilter();
		atendimentoFilter.setTarefa(new TarefaFilter());
		atendimentoFilter.getTarefa().setId(atendimento.getTarefa().getId());
		atendimentoFilter.setPageSize(1);
		atendimentoFilter.setPageNumber(1);

		PagedList<Atendimento> atendimentosAux = this.getListLoadAll(atendimentoFilter);

		// 2 - ATENDIMENTO EXISTE?
		if (atendimentosAux.getTotal() > 0) {
			// 2.1.0 - ATENDIMENTO EXISTE
			atendimentoAux = atendimentosAux.getList().get(0);
			atendimentoAux.setTarefa(atendimento.getTarefa());
			atendimentoAux.setFilaAtendimentoOcupacional(atendimento.getFilaAtendimentoOcupacional());
		} else {
			// 2.2.0 - ATENDIMENTO NÃO EXISTE
			atendimentoAux = atendimento;
			atendimentoAux.setFilaEsperaOcupacional(
					FilaEsperaOcupacionalBo.getInstance().getById(atendimentoAux.getFilaEsperaOcupacional().getId()));
		}
		if ((atendimentoAux.getAso() == null || atendimentoAux.getAso().getId() == 0)
				|| (atendimentoAux.getAso() != null && atendimentoAux.getAso().getId() > 0
						&& (!atendimentoAux.getAso().isConvocado()))) {
			atendimentoAux = gerarAso(atendimentoAux);
			if (atendimentoAux.getAso() != null) {
				atendimentoAux.getAso().getAptidoes().forEach(x -> x.setAso(null));
				atendimentoAux.getAso().getAsoAvaliacoes().forEach(x -> x.setAso(null));
			}
		}
		
		if ((atendimentoAux.getAvaliacaoFisica() == null || atendimentoAux.getAvaliacaoFisica().getId() == 0))			 
			atendimentoAux = generateAvaliacaoFisica(atendimentoAux);
		
		
		atendimentoAux.getFilaAtendimentoOcupacional()
				.setStatus(StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO);

		if (atendimentoAux.getTriagens() == null)
			atendimentoAux.setTriagens(new ArrayList<Triagem>());

		// 3 - PRECISA CRIAR FICHA DE TRIAGEM?
		Servico servico = atendimentoAux.getTarefa().getServico();
		if (servico.getCodigo().equals("0003") && servico.getGrupo().equals(GrupoServico.ATENDIMENTO_OCUPACIONAL)) {
			// 4 - TEM TRIAGENS?
			if (atendimentoAux.getTriagens().size() == 0) {
				// 5 - CRIAR FICHA DE TRIAGEM
				atendimentoAux = FilaEsperaOcupacionalBo.getInstance().setTriagens(atendimentoAux);
			}

			if (atendimentoAux.getTriagens() != null && atendimentoAux.getTriagens().size() > 0) {

				atendimentoAux.getTriagens().forEach(t -> {
					if (t.getAtendimento() != null) {
						long atendimentoId = t.getAtendimento().getId();
						t.setAtendimento(new Atendimento());
						t.getAtendimento().setId(atendimentoId);
					}
				});

				// 6 - TEM RISCO POTENCIAL?
				if (atendimentoAux.getFilaEsperaOcupacional().getRiscoPotencial() == null
						|| atendimentoAux.getFilaEsperaOcupacional().getRiscoPotencial().getId() == 0) {

					RiscoPotencial risco = null;
					Tarefa tarefaPendencia = FilaEsperaOcupacionalBo.getInstance().checkPendecia(
							atendimentoAux.getFilaEsperaOcupacional().getEmpregado(),
							atendimentoAux.getTarefa().getInicio());

					if (tarefaPendencia != null) {
						atendimentoFilter.getTarefa().setId(tarefaPendencia.getId());

						atendimentosAux = this.getListLoadAll(atendimentoFilter);

						if (atendimentosAux.getTotal() > 0) {
							risco = atendimentosAux.getList().get(0).getFilaEsperaOcupacional().getRiscoPotencial();

							if (risco != null && risco.getId() > 0) {
								atendimentoAux.getFilaEsperaOcupacional().setRiscoPotencial(risco);
							}
						}
					}

					if (risco == null) {
						// CRIAR RISCO POTENCIAL

						// SE NÃO TIVER ACOLHIMENTO
						// OBTER A TAREFA DE ACOLHIMENTO
						PagedList<Tarefa> tarefas = FilaEsperaOcupacionalBo.getInstance().obterTarefasAtendimentoAvulso(
								atendimentoAux.getFilaEsperaOcupacional().getEmpregado(), tarefa.getInicio());

						Tarefa tarefaAcolhimento = FilaEsperaOcupacionalBo.getInstance()
								.getTarefaEquipeAcolhimento(tarefas);

						if (tarefaAcolhimento == null || !tarefaAcolhimento.getEquipe().getAbreviacao().equals("ACO")
								|| !tarefaAcolhimento.getEquipe().getAbreviacao().equals("ENF")) {
							// OBTER O RISCO ATUAL, DO EMPREGADO
							PagedList<RiscoPotencial> riscos = null;

							RiscoPotencialFilter riscoPotencialFilter = new RiscoPotencialFilter();
							riscoPotencialFilter.setEmpregado(new EmpregadoFilter());
							riscoPotencialFilter.getEmpregado()
									.setId(atendimentoAux.getFilaEsperaOcupacional().getEmpregado().getId());
							riscoPotencialFilter.setAtual(new BooleanFilter());
							riscoPotencialFilter.getAtual().setValue(1);
							riscoPotencialFilter.setPageSize(1);
							riscoPotencialFilter.setPageNumber(1);

							riscos = RiscoPotencialBo.getInstance().getList(riscoPotencialFilter);

							// SE NÃO TIVER, OBTER RISCO DO EMPREGADO, POSTERIOR A DATA
							if (riscos.getTotal() == 0) {
								riscoPotencialFilter.setData(new DateFilter());
								riscoPotencialFilter.getData().setInicio(tarefa.getInicio());
								riscoPotencialFilter.getData().setTypeFilter(TypeFilter.MAIOR_IGUAL);
								riscoPotencialFilter.setAtual(null);
								riscos = RiscoPotencialBo.getInstance().getList(riscoPotencialFilter);

								if (riscos.getTotal() > 0)
									risco = riscos.getList().get(0);
							} else
								risco = riscos.getList().get(0);
						}

						if (risco == null) {
							risco = new RiscoPotencial();
							risco.setData(tarefa.getInicio());
							risco.setEmpregado(atendimentoAux.getFilaEsperaOcupacional().getEmpregado());
							risco.setAtual(true);
							risco.setStatus(StatusRiscoPotencial.getInstance().ABERTO);
							risco.setAbreviacaoEquipeAcolhimento(tarefaAcolhimento.getEquipe().getAbreviacao());

							// ATUALIZAR RISCOS ANTIGOS
							FilaEsperaOcupacionalBo.getInstance()
									.atualizarRiscosAntigos(atendimentoAux.getFilaEsperaOcupacional().getEmpregado());
						}
						atendimentoAux.getFilaEsperaOcupacional().setRiscoPotencial(risco);
					}
				}

				// 7 - TEM FICHA COLETA?
				if (atendimentoAux.getFilaEsperaOcupacional().getFichaColeta() == null
						|| atendimentoAux.getFilaEsperaOcupacional().getFichaColeta().getId() == 0) {

					atendimentoAux.setFilaEsperaOcupacional(FilaEsperaOcupacionalBo.getInstance()
							.criarFichaColeta(atendimentoAux.getFilaEsperaOcupacional()));

					atendimentoAux.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r -> {
						r.setFicha(null);
					});
				}
			}
		}

		atendimentoAux.getFilaEsperaOcupacional().setEmpregado(
				EmpregadoBo.getInstance().getById(atendimentoAux.getFilaEsperaOcupacional().getEmpregado().getId()));

		atendimentoAux.getTriagens().sort(new Comparator<Triagem>() {
			@Override
			public int compare(Triagem arg0, Triagem arg1) {
				return arg0.getIndicadorSast().getCodigo().compareTo(arg1.getIndicadorSast().getCodigo());
			}
		});
		
		return atendimentoAux;
	}

	public Atendimento calcularComposicaoCorporal(Atendimento atendimento) {
		double sum7Dobras = 0;
		double dc = 0;

		List<RespostaFichaColeta> respostas = (List<RespostaFichaColeta>) atendimento.getFilaEsperaOcupacional()
				.getFichaColeta().getRespostaFichaColetas().stream()
				.filter(r -> r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO))
				.collect(Collectors.toList());
		// peso dividido pela altura ao quadrado (altura em metros)
		
		atendimento.getAvaliacaoFisica().setImc(Helper.roundDouble((Double
				.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
						.collect(Collectors.toList()).get(0).getConteudo())
				/ Math.pow(
						(Double.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0002"))
								.collect(Collectors.toList()).get(0).getConteudo()) / 100),
						2.0)),2));

		atendimento.getAvaliacaoFisica()
				.setRazaoCinturaEstatura(Helper.roundDouble(Double
						.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0010"))
								.collect(Collectors.toList()).get(0).getConteudo())
						/ Double.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0002"))
								.collect(Collectors.toList()).get(0).getConteudo()),2));

		if(!verifyDobrasZero(atendimento)) {
			sum7Dobras = getSumDobras(atendimento);
			
			if (atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getSexo()
					.equals(Sexo.getInstance().MASCULINO)) {
				dc = 1.1120 - (0.00043499 * sum7Dobras) + (0.00000055 * Math.pow(sum7Dobras, 2))
						- (0.00028826 * atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade());
				atendimento.getAvaliacaoFisica().setPercentualGordura(Helper.roundDouble(((4.95 / dc) - 4.50) * 100,2));
			} else {
				dc = 1.0970 - (0.00046971 * sum7Dobras) + (0.00000056 * Math.pow(sum7Dobras, 2))
						- (0.00012828 * atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade());
				atendimento.getAvaliacaoFisica().setPercentualGordura(Helper.roundDouble(((4.96 / dc) - 4.51) * 100,2));
			}
			
			atendimento.getAvaliacaoFisica()
					.setPercentualMassaMagra(Helper.roundDouble(100 - atendimento.getAvaliacaoFisica().getPercentualGordura(),2));
	
			atendimento.getAvaliacaoFisica()
					.setGorduraAbsoluta(Helper.roundDouble((atendimento.getAvaliacaoFisica().getPercentualGordura() / 100)
							* Double.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
									.collect(Collectors.toList()).get(0).getConteudo()),2));
	
			atendimento.getAvaliacaoFisica()
					.setMassaMagra(Helper.roundDouble(Double
							.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
									.collect(Collectors.toList()).get(0).getConteudo())
							- atendimento.getAvaliacaoFisica().getGorduraAbsoluta(),2));
	
			atendimento.getAvaliacaoFisica()
					.setPercentualGorduraIdeal(this.getPercentualGorduraIdeal(
							atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade(),
							atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getSexo()));
	
			atendimento.getAvaliacaoFisica()
					.setPercentualMassaMagraIdeal(Helper.roundDouble(100 - atendimento.getAvaliacaoFisica().getPercentualGorduraIdeal(),2));
	
	
			double carenciaMuscularAux =  Helper.roundDouble((atendimento.getAvaliacaoFisica().getPercentualMassaMagraIdeal()
					- atendimento.getAvaliacaoFisica().getPercentualMassaMagra())
					* (Double.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
							.collect(Collectors.toList()).get(0).getConteudo()) / 100),2);
	
			atendimento.getAvaliacaoFisica().setCarenciaMuscular(carenciaMuscularAux < 0 ? 0 : carenciaMuscularAux);
	
			atendimento.getAvaliacaoFisica()
					.setPesoIdeal(Helper.roundDouble((atendimento.getAvaliacaoFisica().getMassaMagra()
							/ (1 - (atendimento.getAvaliacaoFisica().getPercentualGorduraIdeal() / 100))
							+ atendimento.getAvaliacaoFisica().getCarenciaMuscular()),2));
	
			double pesoExcesso = Helper.roundDouble(Double
					.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
							.collect(Collectors.toList()).get(0).getConteudo())
					- atendimento.getAvaliacaoFisica().getPesoIdeal(),2);
	
			atendimento.getAvaliacaoFisica().setPesoExcesso(pesoExcesso < 0 ? 0: pesoExcesso);
	
			atendimento.getAvaliacaoFisica().setPercentualMassaMagraNegociada(
					Helper.roundDouble(100 - atendimento.getAvaliacaoFisica().getPercentualGorduraNegociada(),2));
	
			atendimento.getAvaliacaoFisica()
					.setPesoNegociado(Helper.roundDouble(atendimento.getAvaliacaoFisica().getMassaMagra()
							/ (1 - (atendimento.getAvaliacaoFisica().getPercentualGorduraNegociada() / 100))
							+ atendimento.getAvaliacaoFisica().getCarenciaMuscular(),2));
	
			double pesoExecessoNegociadoAux = Helper.roundDouble(Double
					.parseDouble(respostas.stream().filter(r -> r.getPergunta().getCodigo().equals("0001"))
							.collect(Collectors.toList()).get(0).getConteudo())
					- atendimento.getAvaliacaoFisica().getPesoNegociado(),2);		
	
			atendimento.getAvaliacaoFisica()
					.setPesoExcessoNegociado(pesoExecessoNegociadoAux < 0 ? 0 : pesoExecessoNegociadoAux);
		}else {
			
			atendimento.getAvaliacaoFisica().setPercentualGordura(0);
			atendimento.getAvaliacaoFisica().setPercentualMassaMagra(0);
			atendimento.getAvaliacaoFisica().setGorduraAbsoluta(0);
			atendimento.getAvaliacaoFisica().setMassaMagra(0);
			atendimento.getAvaliacaoFisica().setPercentualGorduraIdeal(0);
			atendimento.getAvaliacaoFisica().setPercentualMassaMagraIdeal(0);			
			atendimento.getAvaliacaoFisica().setCarenciaMuscular(0);
			atendimento.getAvaliacaoFisica().setPesoIdeal(0);
			atendimento.getAvaliacaoFisica().setPesoExcesso(0);
			atendimento.getAvaliacaoFisica().setPercentualMassaMagraNegociada(0);
			atendimento.getAvaliacaoFisica().setPesoNegociado(0);
			atendimento.getAvaliacaoFisica().setPesoExcessoNegociado(0);
		}
			
		return atendimento;
	}

	private double getSumDobras(Atendimento atendimento) {
		return atendimento.getAvaliacaoFisica().getDobraAbdominal()
				+ atendimento.getAvaliacaoFisica().getDobraAuxiliarMedia()
				+ atendimento.getAvaliacaoFisica().getDobraCoxaMedial()
				+ atendimento.getAvaliacaoFisica().getDobraSubscapular()
				+ atendimento.getAvaliacaoFisica().getDobraToracica()
				+ atendimento.getAvaliacaoFisica().getDobraTricipital()
				+ atendimento.getAvaliacaoFisica().getDobraSupraIliaca();
	}

	private boolean verifyDobrasZero(Atendimento atendimento) {
		return (atendimento.getAvaliacaoFisica().getDobraAbdominal() == 0||
				atendimento.getAvaliacaoFisica().getDobraAuxiliarMedia() == 0||
				atendimento.getAvaliacaoFisica().getDobraCoxaMedial() == 0||
				atendimento.getAvaliacaoFisica().getDobraSubscapular() == 0||
			    atendimento.getAvaliacaoFisica().getDobraToracica() == 0||
				atendimento.getAvaliacaoFisica().getDobraTricipital() == 0||
				atendimento.getAvaliacaoFisica().getDobraSupraIliaca() == 0);
	}
	private int getPercentualGorduraIdeal(int idade, String sexo) {
		if (sexo == Sexo.getInstance().MASCULINO) {
			if (idade >= 18 && idade <= 29) {
				return 14;
			} else if (idade >= 30 && idade <= 39) {
				return 16;
			} else if (idade >= 40 && idade <= 49) {
				return 17;
			} else if (idade >= 50 && idade <= 59) {
				return 18;
			} else if (idade >= 60) {
				return 21;
			}
		} else {
			if (idade >= 18 && idade <= 29) {
				return 19;
			} else if (idade >= 30 && idade <= 39) {
				return 21;
			} else if (idade >= 40 && idade <= 49) {
				return 22;
			} else if (idade >= 50 && idade <= 59) {
				return 23;
			} else if (idade >= 60) {
				return 26;
			}
		}
		return 0;
	}

	@SuppressWarnings({ "resource" })
	public String getRelatorioProaf(Atendimento atendimento) throws Exception {
		// OBTER O HTML DO RELATÓRIO
		StringBuilder html = new StringBuilder();
		String line;

		URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "") + "REPORT/RelatorioProaf.html");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));

		while ((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}

		bufferedReader.close();

		// CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri;
		File pdf;
		URI uri = new URI(Helper.getProjectPath() + "relatorioProaf/");
		File file = new File(uri.getPath());
		file.mkdirs();

		StringReplacer stringReplacer = new StringReplacer(html.toString());
		URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "") + "IMAGE/petrobras.png");
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());

		stringReplacer = stringReplacer
				.replace("[data]", Objects.toString(Helper.convertToHumanPtDate(Helper.getToday())))
				.replace("[nome]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getNome()))
				.replace("[gerencia]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getGerencia()
								.getCodigoCompleto()))
				.replace("[base]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getBase().getNome()))
				.replace("[cargo]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getCargo().getNome()))
				.replace("[idade]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade()))
				.replace("[fcRepouso]", Objects.toString(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getFrequenciaCardiaca(), 2)))
				.replace("[matricula]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getMatricula()))
				.replace("[chave]", Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getChave()))
				.replace("[tipo]", Objects.toString(atendimento.getAvaliacaoFisica().getTipo()))
				.replace("[atendimento]",
						Objects.toString(Helper.convertToHumanPtDate(atendimento.getTarefa().getInicio())))
				.replace("[genero]",
						Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getSexo()))
				.replace("[pressaoArterial]",
						Objects.toString(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getPressaoArterialSistolica(),2) + " X "
								+ roundAndCommaDouble(atendimento.getAvaliacaoFisica().getPressaoArterialDiastolica(),2)));

		AtendimentoFilter filter = new AtendimentoFilter();
		filter.setTarefa(new TarefaFilter());
		filter.getTarefa().setEquipe(new EquipeFilter());
		filter.getTarefa().getEquipe().setId(atendimento.getTarefa().getEquipe().getId());
		filter.getTarefa().setCliente(new EmpregadoFilter());
		filter.getTarefa().getCliente().setId(atendimento.getFilaEsperaOcupacional().getEmpregado().getId());
		filter.setOrder(new OrderFilter());
		filter.getOrder().setDesc(true);
		filter.getOrder().setProperty("id");
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);

		List<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(filter).getList();
		atendimentos.set(0, atendimento);
		int removeCount = atendimentos.size();

		for (int i = removeCount; i >= 1; i--) {
			atendimento = atendimentos.get(i - 1);
			if ( atendimento.getAvaliacaoFisica() == null ) {
				removeCount--;
				continue;
			}
			
			for (int ii = 0; ii < 3; ii++)
				stringReplacer.replace("[data" + i + "]",
						Objects.toString(Helper.convertToHumanPtDate(atendimento.getTarefa().getInicio())));

			stringReplacer.replace("[ccPeso" + i + "]",
					Objects.toString(getRespostaFichaColeta(atendimento.getFilaEsperaOcupacional().getFichaColeta(),
							GrupoPerguntaFichaColeta.EXAME_FISICO, "0001")))
					.replace("[ccEstatura" + i + "]",
							Objects.toString(
									getRespostaFichaColeta(atendimento.getFilaEsperaOcupacional().getFichaColeta(),
											GrupoPerguntaFichaColeta.EXAME_FISICO, "0002")))
					.replace("[ccCintura" + i + "]",
							Objects.toString(
									getRespostaFichaColeta(atendimento.getFilaEsperaOcupacional().getFichaColeta(),
											GrupoPerguntaFichaColeta.EXAME_FISICO, "0010")))
					.replace("[ccAbdome" + i + "]",
							Objects.toString(
									getRespostaFichaColeta(atendimento.getFilaEsperaOcupacional().getFichaColeta(),
											GrupoPerguntaFichaColeta.EXAME_FISICO, "0011")))
					.replace("[ccQuadril" + i + "]",
							Objects.toString(
									getRespostaFichaColeta(atendimento.getFilaEsperaOcupacional().getFichaColeta(),
											GrupoPerguntaFichaColeta.EXAME_FISICO, "0012")))
					.replace("[ccImc" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getImc(),2))))
					.replace("[ccRce" + i + "]",
							Objects.toString(
									String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getRazaoCinturaEstatura(),2))))
					.replace("[cc%Gordura" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getPercentualGordura(),2))))
					.replace("[ccPesoGordo" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getGorduraAbsoluta(),2))))
					.replace("[ccPesoMagro" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getMassaMagra(),2))))
					.replace("[ccPesoIdeal" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getPesoIdeal(),2))))
					.replace("[ccPin" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getPesoNegociado(),2))))
					.replace("[dcDobraTricipital" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraTricipital(),2))))
					.replace("[dcDobraSubscapular" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraSubscapular(),2))))
					.replace("[dcDobraAuxMedia" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraAuxiliarMedia(),2))))
					.replace("[dcDobraToracica" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraToracica(),2))))
					.replace("[dcDobraAbdominal" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraAbdominal(),2))))
					.replace("[dcDobraSupraIliaca" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraSupraIliaca(),2))))
					.replace("[dcDobraCoxaMedial" + i + "]",
							Objects.toString(String.valueOf(roundAndCommaDouble(atendimento.getAvaliacaoFisica().getDobraCoxaMedial(),2))))
					.replace("[afVo2Maximo" + i + "]", Objects.toString(
							String.valueOf(atendimento.getAvaliacaoFisica().getAptidaoCardiorrespiratoriaValor() + " - "
									+ (atendimento.getAvaliacaoFisica().getAptidaoCardiorrespiratoriaClassificacao() != null ?
									   atendimento.getAvaliacaoFisica().getAptidaoCardiorrespiratoriaClassificacao() : ""))))
					.replace("[afFlexibilidade" + i + "]",
							Objects.toString(String.valueOf(atendimento.getAvaliacaoFisica().getFlexibilidadeValor()
									+ " - " + (atendimento.getAvaliacaoFisica().getFlexibilidadeClassificacao() != null ?
											   atendimento.getAvaliacaoFisica().getFlexibilidadeClassificacao() : ""))))
					.replace("[afRepeticoesAbdominais" + i + "]",
							Objects.toString(String.valueOf(atendimento.getAvaliacaoFisica().getForcaAbdominalValor()
									+ " - " + (atendimento.getAvaliacaoFisica().getForcaAbdominalClassificacao() != null ?
											   atendimento.getAvaliacaoFisica().getForcaAbdominalClassificacao() : ""))))
					.replace("[afForcaPreensaoManual" + i + "]",
							Objects.toString(String
									.valueOf(atendimento.getAvaliacaoFisica().getForcaPreensaoManualValor() + " - "
											+ (atendimento.getAvaliacaoFisica().getForcaPreensaoManualClassificacao()!= null ?
											   atendimento.getAvaliacaoFisica().getForcaPreensaoManualClassificacao() : ""))));
		}

		StringBuilder atividades  = new StringBuilder(""); 
		atendimento.getAvaliacaoFisica().getAvaliacaoFisicaAtividadeFisicas().forEach(afaf -> {
			if (afaf.getTipo().equals(TipoAtividadeFisica.getInstance().ORIENTADA)) {
				atividades.append("<tr><td width='200'><font size='1'>" + afaf.getAtividadeFisica().getDescricao() + "</font></td>" );
				atividades.append( "<td width='100'><font size='1'>" + getDaysAvaliacaoFisicaAtividadeFisica(afaf) + "</font></td>");
				atividades.append("<td width='100'><font size='1'>" + String.valueOf(afaf.getMinuto()) + "</font></td>");
				atividades.append("<td width='100'><font size='1'>" + (afaf.getObservacao() != null ? afaf.getObservacao() : "") + "</font></td>"); 
				atividades.append("</tr>");
			}
		});
		
		stringReplacer.replace("[atividadesFisicasRecomendadas]", atividades.toString());
		
		stringReplacer.replace("[parecerConclusivoOrientacoes]",
				Objects.toString(atendimento.getAvaliacaoFisica().getObservacoes() != null ? atendimento.getAvaliacaoFisica().getObservacoes() : ""));

		stringReplacer.replace("[nomeEducadorFisico]",
				atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getPessoa().getNome());

		stringReplacer.replace("[gestor]", "");
		stringReplacer.replace("[nivelProtecao]", "NÍVEL DE PROTEÇÃO - 3");
		stringReplacer.replace("[versao]", Helper.convertToHumanPtDate(Helper.getToday()));

		while (removeCount < 3) {
			removeCount++;
			for ( int i = 0; i < 3; i++ ) 
				stringReplacer.replace("[data" + removeCount + "]","");
			
			stringReplacer.replace("[ccPeso" + removeCount + "]", "").replace("[ccEstatura" + removeCount + "]", "")
					.replace("[ccCintura" + removeCount + "]", "").replace("[ccAbdome" + removeCount + "]", "").replace("[ccQuadril" + removeCount + "]", "")
					.replace("[ccImc" + removeCount + "]", "").replace("[ccRce" + removeCount + "]", "")
					.replace("[cc%Gordura" + removeCount + "]", "").replace("[ccPesoGordo" + removeCount + "]", "")
					.replace("[ccPesoMagro" + removeCount + "]", "").replace("[ccPesoIdeal" + removeCount + "]", "")
					.replace("[ccPin" + removeCount + "]", "").replace("[ccPin" + removeCount + "]", "")
					.replace("[dcDobraTricipital" + removeCount + "]", "")
					.replace("[dcDobraSubscapular" + removeCount + "]", "")
					.replace("[dcDobraAuxMedia" + removeCount + "]", "")
					.replace("[dcDobraToracica" + removeCount + "]", "")
					.replace("[dcDobraAbdominal" + removeCount + "]", "")
					.replace("[dcDobraSupraIliaca" + removeCount + "]", "")
					.replace("[dcDobraCoxaMedial" + removeCount + "]", "")
					.replace("[afVo2Maximo" + removeCount + "]", "").replace("[afFlexibilidade" + removeCount + "]", "")
					.replace("[afRepeticoesAbdominais" + removeCount + "]", "")
					.replace("[afForcaPreensaoManual" + removeCount + "]", "");
		}

		pdfUri = new URI(uri.getPath() + "/"
				+ Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getMatricula().trim())
				+ ".pdf");

		pdf = new File(pdfUri.getPath());
		OutputStream stream = new FileOutputStream(pdf);
		Document doc = new Document();
		PdfWriter.getInstance(doc, stream);
		doc.open();

		HTMLWorker htmlWorker = new HTMLWorker(doc);
		htmlWorker.parse(new StringReader(stringReplacer.result()));

		doc.close();
		stream.close();

		byte[] pdfArray = new byte[(int) pdf.length()];
		new FileInputStream(pdf).read(pdfArray);
		return Base64.getEncoder().encodeToString(pdfArray);
	}

	private String getRespostaFichaColeta(FichaColeta fichaColeta, String grupo, String codigo) {
		Optional<RespostaFichaColeta> oResposta = fichaColeta.getRespostaFichaColetas().stream()
				.filter(r -> r.getPergunta().getGrupo().equals(grupo) && r.getPergunta().getCodigo().equals(codigo))
				.findFirst();

		if (oResposta.isPresent())
			return oResposta.get().getConteudo();

		return null;
	}

	private int getDaysAvaliacaoFisicaAtividadeFisica(AvaliacaoFisicaAtividadeFisica afaf) {
		int days = 0;
		if (afaf.isDomingo())
			days++;
		if (afaf.isSegunda())
			days++;
		if (afaf.isTerca())
			days++;
		if (afaf.isQuarta())
			days++;
		if (afaf.isQuinta())
			days++;
		if (afaf.isSexta())
			days++;
		if (afaf.isSabado())
			days++;
		return days;
	}
	
	private String roundAndCommaDouble(Double value, int precision) {
		String[] splitted = value.toString().split("\\.");
		if ( splitted.length == 1 ) return splitted[0]; 
		else if ( precision < 1 ) return "";
		
		int sz = splitted.length;
		String join = "";
		if ( splitted[sz-1].length() > 1 )
			splitted[sz-1] = (String) splitted[sz-1].subSequence(0, precision);
		else 
			splitted[sz-1] = (String) splitted[sz-1].subSequence(0, 1);
		join = splitted[0];
		for ( int i=1; i < sz-2; i++ )
			join += "."+splitted[i];
		join += ","+splitted[sz-1];
		return join;
	}

	public Atendimento generateAvaliacaoFisica(Atendimento atendimento) {
		if ( atendimento.getFilaAtendimentoOcupacional().getProfissional()
				.getEquipe().getAbreviacao().equals("EDF") ) {
			atendimento.setAvaliacaoFisica(new AvaliacaoFisica());
		} else {
			atendimento.setAvaliacaoFisica(null);
		}
		return atendimento;
	}
	
}