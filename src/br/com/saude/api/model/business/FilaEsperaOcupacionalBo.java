package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
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
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.entity.po.ItemRespostaFichaColeta;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.RegraAtendimento;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipe;
import br.com.saude.api.model.entity.po.RegraAtendimentoLocalizacao;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.FilaEsperaOcupacionalDao;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusRiscoPotencial;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.UF;

@SuppressWarnings("deprecation")
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
	
	public List<Atendimento> getQuadroAtendimento(Atendimento atendimento) throws Exception{
		
		if(atendimento == null || atendimento.getTarefa() == null || atendimento.getTarefa().getInicio() == null)
			throw new Exception("É necessário informar a Data.");
		
		if(atendimento.getFilaEsperaOcupacional() == null || atendimento.getFilaEsperaOcupacional()
				.getLocalizacao() == null || atendimento.getFilaEsperaOcupacional().getLocalizacao().getId() == 0)
			throw new Exception("É necessário informar a Localização.");
		
		Date inicio = atendimento.getTarefa().getInicio();
		inicio.setHours(0);
		inicio.setMinutes(0);
		inicio.setSeconds(0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inicio);
		calendar.add(Calendar.DATE, 1);
		
		AtendimentoFilter filter = new AtendimentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setTarefa(new TarefaFilter());
		filter.getTarefa().setInicio(new DateFilter());
		filter.getTarefa().getInicio().setInicio(inicio);
		filter.getTarefa().getInicio().setFim(calendar.getTime());
		filter.getTarefa().getInicio().setTypeFilter(TypeFilter.ENTRE);
		filter.setFilaEsperaOcupacional(new FilaEsperaOcupacionalFilter());
		filter.getFilaEsperaOcupacional().setLocalizacao(new LocalizacaoFilter());
		filter.getFilaEsperaOcupacional().getLocalizacao().setId(
				atendimento.getFilaEsperaOcupacional().getLocalizacao().getId() );
		
		PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(filter);
		
		atendimentos.getList().sort(new Comparator<Atendimento>() {
			public int compare(Atendimento arg0, Atendimento arg1) {
				return (arg0.getFilaEsperaOcupacional().getHorarioCheckin() + " - " +
				arg0.getTarefa().getInicio().toString()).compareTo(
						arg1.getFilaEsperaOcupacional().getHorarioCheckin() + " - " +
						arg1.getTarefa().getInicio().toString());
			}
		});
		
		return atendimentos.getList();
	}
	
	@SuppressWarnings({ "resource" })
	public String getDeclaracaoComparecimento(Atendimento atendimento) throws Exception{
		
		if(atendimento == null || atendimento.getTarefa() == null || atendimento.getTarefa().getInicio() == null)
			throw new Exception("É necessário informar a Data.");
		
		if(atendimento.getFilaEsperaOcupacional() == null || atendimento.getFilaEsperaOcupacional().getEmpregado() == null 
				|| atendimento.getFilaEsperaOcupacional().getEmpregado().getId() == 0)
			throw new Exception("É necessário informar o Empregado.");
		
		Profissional profissional = this.getProfissional("YSPR");
		
		if(profissional == null)
			throw new Exception("Profissional inexistente.");
		
		Empregado empregado = EmpregadoBo.getInstance().getById(profissional.getEmpregado().getId());
		profissional.setEmpregado(empregado);
		
		Date inicio = atendimento.getTarefa().getInicio();
		inicio.setHours(0);
		inicio.setMinutes(0);
		inicio.setSeconds(0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inicio);
		calendar.add(Calendar.DATE, 1);
		
		AtendimentoFilter filter = new AtendimentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setTarefa(new TarefaFilter());
		filter.getTarefa().setInicio(new DateFilter());
		filter.getTarefa().getInicio().setInicio(inicio);
		filter.getTarefa().getInicio().setFim(calendar.getTime());
		filter.getTarefa().getInicio().setTypeFilter(TypeFilter.ENTRE);
		filter.setFilaEsperaOcupacional(new FilaEsperaOcupacionalFilter());
		filter.getFilaEsperaOcupacional().setEmpregado(new EmpregadoFilter());
		filter.getFilaEsperaOcupacional().getEmpregado().setId(atendimento.
				getFilaEsperaOcupacional().getEmpregado().getId());
		
		List<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(filter).getList();
		
		if (atendimentos == null || atendimentos.size() == 0 ) 
			throw new Exception("Não existe atendimentos para o empregado nesta data.");
		
		atendimentos.sort(new Comparator<Atendimento>() {
			public int compare(Atendimento arg0, Atendimento arg1) {
				return (arg0.getTarefa().getInicio().toString()).
						compareTo(arg1.getTarefa().getInicio().toString());
			}
		});
		
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"REPORT/DeclaracaoComparecimento.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		//CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri;
		File pdf;
		URI uri = new URI(Helper.getProjectPath()+"declaracaoComparecimento/");
		File file = new File(uri.getPath());
		file.mkdirs();
		
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		stringReplacer = stringReplacer
			.replace("nomeEmpregado", Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getPessoa().getNome()))
			.replace("matriculaEmpregado", Objects.toString(atendimento.getFilaEsperaOcupacional().getEmpregado().getMatricula()))
			.replace("nomeProfissional", Objects.toString(profissional.getEmpregado().getPessoa().getNome()))
			.replace("cargoProfissional", Objects.toString(profissional.getEmpregado().getCargo().getNome()))
			.replace("ramalProfissional", Objects.toString(profissional.getEmpregado().getRamal()))
			.replace("tipoServico", Objects.toString(atendimentos.get(0).getTarefa().getServico().getNome()));
		
		if(profissional.getEmpregado().getFuncao() != null )
			stringReplacer.replace("funcaoProfissional", Objects.toString(profissional.getEmpregado().getFuncao().getNome()));
		else
			stringReplacer.replace("funcaoProfissional", "---");
		
		if ( profissional.getEmpregado().getPessoa().getTelefones() != null && profissional.getEmpregado().getPessoa().getTelefones().size() > 0 )
			stringReplacer.replace("telefoneProfissional", Objects.toString(profissional.getEmpregado().getPessoa().getTelefones().get(0)));
		else stringReplacer.replace("telefoneProfissional", "---");
		
		if ( profissional.getEmpregado().getPessoa().getEmail() != null )
			stringReplacer.replace("emailProfissional", Objects.toString(profissional.getEmpregado().getPessoa().getEmail()));
		else stringReplacer.replace("emailProfissional", "---");
		
		if(empregado.getBase().getUf().trim().equals("ES"))
			stringReplacer.replace("localizacao", "da Base 61");
		else
			stringReplacer.replace("localizacao", "de Taquipe");
		
		URI assinatura = null;
		
		try {
			 assinatura = new URI(Helper.getProjectPath() + "empregado/assinatura/" + profissional.getEmpregado().getId() + ".png");
		} catch (Exception e) {
			assinatura = null;
		}
				
		URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
		URI np2 = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/np-2.png");
		
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath())
				.replace("np2", np2.getPath());
		if ( assinatura != null )
			stringReplacer = stringReplacer.replace("assinatura", assinatura.getPath());
		
		SimpleDateFormat sdfData = new SimpleDateFormat("d MMMMM yyyy");
		 
		String dataAtual = sdfData.format(new Date());
		stringReplacer = stringReplacer.replace("data", dataAtual)
				.replace("horarioCheckin", atendimentos.get(atendimentos.size()-1).getFilaEsperaOcupacional().getHorarioCheckin().toLocaleString())
				.replace("fim", atendimentos.get(atendimentos.size()-1).getTarefa().getFim().toLocaleString());
		
		
		pdfUri = new URI(uri.getPath()+"/"+Objects.toString(
				atendimento.getFilaEsperaOcupacional().getEmpregado().getMatricula().trim())+".pdf");
		
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
	
	private Profissional getProfissional(String chave) throws Exception {
		ProfissionalFilter profissionalFilter = new ProfissionalFilter();
		profissionalFilter.setEmpregado(new EmpregadoFilter());
		profissionalFilter.getEmpregado().setChave(chave);
		profissionalFilter.setPageSize(1);
		profissionalFilter.setPageNumber(1);
		
		PagedList<Profissional> profissionais = ProfissionalBo.getInstance().getList(profissionalFilter);
		
		if ( profissionais.getTotal() > 0 ) return profissionais.getList().get(0);
		
		return null;
	}
	
	private PagedList<FilaEsperaOcupacional> check(FilaEsperaOcupacional fila,Date data) throws Exception {
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
		
		FilaEsperaOcupacionalFilter filaFilter = new FilaEsperaOcupacionalFilter();
		filaFilter.setPageNumber(1);
		filaFilter.setPageSize(1);
		filaFilter.setEmpregado(new EmpregadoFilter());
		filaFilter.getEmpregado().setMatricula(fila.getEmpregado().getMatricula());
		filaFilter.setHorarioCheckin(new DateFilter());
		filaFilter.getHorarioCheckin().setTypeFilter(TypeFilter.ENTRE);
		filaFilter.getHorarioCheckin().setInicio(new Date(data.getTime()));
		filaFilter.getHorarioCheckin().setFim(new Date(data.getTime()));
		
		return getList(getDao().getListLoadAll(getExampleBuilder(filaFilter).example()), this.functionLoadAll);
	}
	
	public String checkOut(FilaEsperaOcupacional fila) throws Exception {
		
		PagedList<FilaEsperaOcupacional> filaEsperaOcupacionais = check(fila,Helper.getToday());
		
		if(filaEsperaOcupacionais.getTotal() > 0) {
			fila = filaEsperaOcupacionais.getList().get(0);
			fila.setStatus(StatusFilaEsperaOcupacional.getInstance().ALMOCO);
			
			FichaColeta f = fila.getFichaColeta();
			fila.getFichaColeta().getRespostaFichaColetas().forEach(r->{
				r.setFicha(f);
				r.getItens().forEach(i->{
					i.setResposta(r);
				});
			});
			
			getDao().save(fila);
		}else
			throw new Exception("Não foi encontrado o Check-in do Empregado.");
		
		return "Registro de Almoço realizado para o Empregado "+fila.getEmpregado().getPessoa().getNome()+".";
	}
	
	public String checkIn(FilaEsperaOcupacional fila) throws Exception {
		return checkIn(fila,Helper.getNow());
	}
	
	public String checkInRetroativo(FilaEsperaOcupacionalFilter filter) throws Exception {
		
	    FilaEsperaOcupacional fila = new FilaEsperaOcupacional();
	    fila.setLocalizacao(new Localizacao());
	    fila.getLocalizacao().setId(Integer.parseInt(new Long(filter.getLocalizacao().getId()).toString()));
	    fila.setHorarioCheckin(filter.getHorarioCheckin().getInicio());
	    fila.setAtualizacao(Helper.getNow());
	    fila.setServico(new Servico());
	    fila.getServico().setId(Integer.parseInt(new Long(filter.getServico().getId()).toString()));;
		fila.setEmpregado(EmpregadoBo.getInstance().getById(Integer.parseInt(new Long(filter.getEmpregado().getId()).toString())));			
		
		return checkIn(fila,fila.getHorarioCheckin());
	}
	
	private String checkIn(FilaEsperaOcupacional fila, Date data) throws Exception {
		
		PagedList<FilaEsperaOcupacional> filaEsperaOcupacionais = check(fila,data);
		
		if(filaEsperaOcupacionais.getTotal() > 0) {
			
			fila = filaEsperaOcupacionais.getList().get(0);
			
			//SE ESTIVER EM ALMOÇO, VOLTAR PARA A FILA
			if(fila.getStatus().equals(StatusFilaEsperaOcupacional.getInstance().ALMOCO) ||
					fila.getStatus().equals(StatusFilaEsperaOcupacional.getInstance().AUSENTE)) {
				fila.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
				
				if(fila.getFichaColeta() == null)
					fila = criarFichaColeta(fila);
				
				FichaColeta f = fila.getFichaColeta();
				fila.getFichaColeta().getRespostaFichaColetas().forEach(r->{
					r.setFicha(f);
					
					if(r.getItens() != null)
						r.getItens().forEach(i->{
							i.setResposta(r);
						});
				});
				
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
		RiscoPotencial risco = null;
		FichaColeta ficha = null;
		Tarefa tarefa = null;
		PagedList<Tarefa> tarefas = obterTarefasAbertas(fila.getEmpregado(), data);
		
		if(tarefas.getTotal() > 0) {
			tarefa = getTarefaEquipeAcolhimento(tarefas);
		}else {
			tarefa = checkPendecia(fila.getEmpregado(),Helper.getToday());
		
			if(tarefa == null)
				throw new Exception("Não há agendamento para este Empregado.");
			
			//OBTER O RISCO ATIVO DO EMPREGADO
			RiscoPotencialFilter riscoFilter = new RiscoPotencialFilter();
			riscoFilter.setPageNumber(1);
			riscoFilter.setPageSize(1);
			riscoFilter.setAtual(new BooleanFilter());
			riscoFilter.getAtual().setValue(1);
			riscoFilter.setEmpregado(new EmpregadoFilter());
			riscoFilter.getEmpregado().setMatricula(fila.getEmpregado().getMatricula());
			
			PagedList<RiscoPotencial> riscos = RiscoPotencialBo.getInstance().getListLoadAll(riscoFilter);
			if(riscos.getTotal() > 0) {
				risco = riscos.getList().get(0);
				
				RiscoPotencial riscoAux = risco;
				risco.getRiscoEmpregados().forEach(r->{
					r.setRiscoPotencial(riscoAux);
					r.getTriagens().forEach(t->{
						t.setRiscoEmpregado(r);
					});
				});
				
				FilaEsperaOcupacionalFilter filaFilter = new FilaEsperaOcupacionalFilter();
				filaFilter.setPageNumber(1);
				filaFilter.setPageSize(Integer.MAX_VALUE);
				filaFilter.setRiscoPotencial(new RiscoPotencialFilter());
				filaFilter.getRiscoPotencial().setId(risco.getId());
				
				PagedList<FilaEsperaOcupacional> filas = getListAll(filaFilter);
				
				if(filas.getTotal() > 0) {
					filas.getList().sort(new Comparator<FilaEsperaOcupacional>() {
						@Override
						public int compare(FilaEsperaOcupacional arg0, FilaEsperaOcupacional arg1) {
							if(arg1.getFichaColeta() != null && arg0.getFichaColeta() != null)
								return new Integer(arg1.getFichaColeta().getId())
										.compareTo(arg0.getFichaColeta().getId());
							return 0;
						}
					}); 
					
					ficha = filas.getList().get(0).getFichaColeta();
					FichaColeta fichaAux = ficha;
					ficha.getRespostaFichaColetas().forEach(r->{
						r.setFicha(fichaAux);
						r.getItens().forEach(i -> {
							i.setResposta(r);
							i = loadItem(i);
						});
					});
					
					fila.setFichaColeta(ficha);
				}
			}
		}
		
		// 5 - INSTANCIAR FILA
		fila.setHorarioCheckin(data);
		fila.setAtualizacao(fila.getHorarioCheckin());
		fila.setTempoEspera(0);
		fila.setStatus(StatusFilaEsperaOcupacional.getInstance().AGUARDANDO);
		fila.setServico(tarefa.getServico());
		
		if(ficha == null && (tarefa.getEquipe().getAbreviacao().equals("ACO") ||
				tarefa.getEquipe().getAbreviacao().equals("ENF"))) {
			// 6 - CRIAR A FICHA DE COLETA, COM AS RESPOSTAS PARA AS PERGUNTAS ATIVAS
			fila = criarFichaColeta(fila);
			
			// 7 - GERAR O RISCO POTENCIAL
			if(risco == null) {
				risco = new RiscoPotencial();
				risco.setData(data);
				risco.setEmpregado(fila.getEmpregado());
				risco.setAtual(true);
				risco.setStatus(StatusRiscoPotencial.getInstance().ABERTO);
				risco.setAbreviacaoEquipeAcolhimento(tarefa.getEquipe().getAbreviacao());
			}
			fila.setRiscoPotencial(risco);
			
			// 8 - OBTER A LISTA DOS RISCOS DO EMPREGADO PARA SETAR COMO NÃO ATUAL
			atualizarRiscosAntigos(fila.getEmpregado());
		}else {
			if(fila.getRiscoPotencial() != null && fila.getRiscoPotencial().getId() == 0)
				fila.setRiscoPotencial(null);
			if(risco!=null && risco.getId() > 0)
				fila.setRiscoPotencial(risco);
		}
		
		// 9 - INSERIR NO BANCO
		getDao().save(fila);
		
		return "Empregado "+fila.getEmpregado().getPessoa().getNome()+" inserido na fila de espera. "+
				"Favor aguardar chamada.";
	}
	
	public PagedList<Tarefa> obterTarefasAbertas(Empregado empregado, Date data) throws Exception{
		TarefaFilter tarefaFilter = configurarTarefaFilter(empregado, data);
		
		return TarefaBo.getInstance()
			.getList(TarefaExampleBuilder.newInstance(tarefaFilter).exampleStatusNaoConcluidoCancelado());
	}
	
	public PagedList<Tarefa> obterTarefasAtendimentoAvulso(Empregado empregado, Date data) throws Exception{
		TarefaFilter tarefaFilter = configurarTarefaFilter(empregado, data);
		
		return TarefaBo.getInstance().getListAtendimentoAvulso(tarefaFilter);
	}
	
	private TarefaFilter configurarTarefaFilter(Empregado empregado, Date data) {
		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(Integer.MAX_VALUE);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setMatricula(empregado.getMatricula());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		
		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setTypeFilter(TypeFilter.ENTRE);
		tarefaFilter.getInicio().setInicio(new Date(data.getTime()));
		tarefaFilter.getInicio().setFim(new Date(data.getTime()));
		return tarefaFilter;
	}
	
	public Tarefa getTarefaEquipeAcolhimento(PagedList<Tarefa> tarefas) {
		
		Tarefa tarefa;
		
		if(tarefas.getList().stream().filter(t->t.getEquipe().getAbreviacao().equals("ACO"))
				.count() > 0)
			tarefa = tarefas.getList().stream()
				.filter(t->t.getEquipe().getAbreviacao().equals("ACO")).findFirst().get();
		else if(tarefas.getList().stream().filter(t->t.getEquipe().getAbreviacao().equals("ENF"))
				.count() > 0)
			tarefa = tarefas.getList().stream()
				.filter(t->t.getEquipe().getAbreviacao().equals("ENF")).findFirst().get();
		else
			tarefa = tarefas.getList().get(0);
		
		return tarefa;
	}
	
	public void atualizarRiscosAntigos(Empregado empregado) throws Exception {
		RiscoPotencialFilter riscoFilter = new RiscoPotencialFilter();
		riscoFilter.setPageNumber(1);
		riscoFilter.setPageSize(Integer.MAX_VALUE);
		riscoFilter.setEmpregado(new EmpregadoFilter());
		riscoFilter.getEmpregado().setId(empregado.getId());
		riscoFilter.setAtual(new BooleanFilter());
		riscoFilter.getAtual().setValue(1);
		
		PagedList<RiscoPotencial> riscos = RiscoPotencialBo.getInstance().getListLoadAll(riscoFilter);
		
		if(riscos.getTotal() > 0) {
			riscos.getList().forEach(r-> {
				r.setAtual(false);
				r.getRiscoEmpregados().forEach(rE -> {
					rE.setRiscoPotencial(r);
					rE.getTriagens().forEach(t -> {
						if(t.getAcoes() != null)
						t.getAcoes().forEach(a -> {
							a.setTriagem(t);
							a.getAcompanhamentos().forEach(ac -> ac.setAcao(a));
						});
					});
				});
			});
			RiscoPotencialBo.getInstance().saveList(riscos.getList());
		}
	}
	
	public FilaEsperaOcupacional criarFichaColeta(FilaEsperaOcupacional fila) throws Exception {
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
		
		return fila;
	}
	
	private ItemRespostaFichaColeta loadItem(ItemRespostaFichaColeta item) {
		if(item.getItem() != null)
			item.setItem(loadItem(item.getItem()));
		return item;
	}
	
	public void criarFichaColetaTriagem(List<FilaEsperaOcupacional> ids) throws Exception {
		for(int id : ids.stream().map(FilaEsperaOcupacional::getId)
						.collect(Collectors.toList())) {
			FilaEsperaOcupacional fila = getById(new Integer(id));
			Empregado empregado = EmpregadoBo.getInstance().getById(fila.getEmpregado().getId());
			
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
			
			fila.setRiscoPotencial(new RiscoPotencial());
			fila.getRiscoPotencial().setData(fila.getHorarioCheckin());
			fila.getRiscoPotencial().setEmpregado(fila.getEmpregado());
			fila.getRiscoPotencial().setAtual(true);
			fila.getRiscoPotencial().setStatus(StatusRiscoPotencial.getInstance().PLANEJAMENTO);
			fila.getRiscoPotencial().setRiscoEmpregados(new ArrayList<RiscoEmpregado>());
			
			if(empregado.getBase().getUf().equals(UF.getInstance().ES))
				fila.getRiscoPotencial().setAbreviacaoEquipeAcolhimento("ENF");
			else
				fila.getRiscoPotencial().setAbreviacaoEquipeAcolhimento("ACO");
			
			//OBTER OS ATENDIMENTOS ASSOCIADOS À FILA
			AtendimentoFilter aF = new AtendimentoFilter();
			aF.setPageNumber(1);
			aF.setPageSize(Integer.MAX_VALUE);
			aF.setFilaEsperaOcupacional(new FilaEsperaOcupacionalFilter());
			aF.getFilaEsperaOcupacional().setId(fila.getId());
			
			PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(aF);
			
			if(atendimentos.getTotal() > 0) {
				for(Atendimento atendimento:atendimentos.getList()) {
					atendimento = setTriagens(atendimento);
					Atendimento aAux = atendimento;
					if(atendimento.getTriagens() != null && atendimento.getTriagens().size() > 0) {
						atendimento.getTriagens().forEach(t->{
							t.setIndice(0);
							t.setAtendimento(aAux);
						});
						atendimento = AtendimentoBo.getInstance().gerarRisco(atendimento);
					
						fila.getRiscoPotencial().getRiscoEmpregados()
							.add(atendimento.getTriagens().get(0).getRiscoEmpregado());
							
						atendimento.getTriagens().forEach(t->{
							t.getRiscoEmpregado().setData(fila.getHorarioCheckin());
							t.getRiscoEmpregado().setRiscoPotencial(fila.getRiscoPotencial());
						});
					}
					
					atendimento.setFilaEsperaOcupacional(fila);
				}
				
				AtendimentoBo.getInstance().saveList(atendimentos.getList());
			}
			
		}
	}
	
	public Tarefa checkPendecia(Empregado empregado, Date dataReferencia) throws Exception {
		
		//VERIFICAR SE EXISTE TAREFA ABERTA ANTERIOR AO DIA ATUAL
		TarefaFilter tarefaFilter = new TarefaFilter();
		tarefaFilter.setPageNumber(1);
		tarefaFilter.setPageSize(Integer.MAX_VALUE);
		tarefaFilter.setCliente(new EmpregadoFilter());
		tarefaFilter.getCliente().setId(empregado.getId());
		tarefaFilter.setServico(new ServicoFilter());
		tarefaFilter.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		tarefaFilter.setInicio(new DateFilter());
		tarefaFilter.getInicio().setInicio(dataReferencia);
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
						tarefaPendencia = checkPendecia(filaEspera.getEmpregado(),Helper.getToday());
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
					TarefaFilter tarefaFilterAux = criarFiltroTarefa(filaEspera);
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
									
									if(aList.getList() == null || aList.getList().size() == 0)
										continue;
									
									boolean temHo = true;
									
									if(rE.getRegraAtendimentoEquipeRequisitos().size() > aList.getList().size()) {
										//VERIFICAR SE O EMPREGADO TEM TAREFA DE HO EM ABERTO, COM A MESMA DATA DA TAREFA
										//PENDENTE, SE EXISTIR, OU DATA ATUAL. SE EXISTIR, CONTINUAR
										
										TarefaFilter tarefaFilterHo = criarFiltroTarefa(filaEspera);
										tarefaFilterHo.setStatus(StatusTarefa.getInstance().ABERTA);
										tarefaFilterHo.setEquipe(new EquipeFilter());
										tarefaFilterHo.getEquipe().setAbreviacao("HIG");
										tarefaFilterHo.getServico().setCodigo("0003");
										
										if(tarefaPendencia != null) {
											tarefaFilterHo.setInicio(new DateFilter());
											tarefaFilterHo.getInicio().setTypeFilter(TypeFilter.ENTRE);
											tarefaFilterHo.getInicio().setInicio(tarefaPendencia.getInicio());
											tarefaFilterHo.getInicio().setFim(tarefaPendencia.getFim());
										}
										
										PagedList<Tarefa> tHo = new PagedList<Tarefa>();
										
										try {
											tHo = TarefaBo.getInstance().getList(tarefaFilterHo);
										} catch (Exception e) {
											e.printStackTrace();
										}
										
										if(tHo.getTotal() > 0)
											continue;
									}
									
									List<Atendimento> listAtendimento = aList.getList();
									if(rE.getRegraAtendimentoEquipeRequisitos().stream().filter(r-> {
										return listAtendimento.stream().filter(a->{
											return a.getTarefa().getEquipe().getId() == r.getEquipe().getId();
										}).count() > 0;
									}).count() != (rE.getRegraAtendimentoEquipeRequisitos().size() - (rE.isAcolhimento() && !temHo ? 1 : 0) ))
										continue;
									
									//SE FOR ACOLHIMENTO, 
									if(rE.isAcolhimento()) {
										//NÃO LEVAR EM CONSIDERAÇÃO OS ATENDIMENTOS
										//CUJA TAREFA ESTEJA EM EXECUÇÃO
										if(listAtendimento.stream()
												.filter(a->a.getTarefa().getStatus().equals(
														StatusTarefa.getInstance().EXECUCAO))
												.count() > 0 ) {
											continue;
										}
									}
								}else {
									//SE JÁ HOUVER UMA TAREFA EM EXECUÇÃO OU CONCLUÍDA, NÃO PROSSEGUIR
									PagedList<Tarefa> tAux = new PagedList<Tarefa>(); 
									TarefaFilter tFilterAux = criarFiltroTarefa(filaEspera);
									tFilterAux.setEquipe(new EquipeFilter());
									tFilterAux.getEquipe().setAbreviacao(rE.getEquipe().getAbreviacao());
									
									try {
										tAux = TarefaBo.getInstance().getList(
												TarefaExampleBuilder.newInstance(tFilterAux).exampleStatusExecucaoOuConcluido());
										
										//SE TIVER PENDÊNCIA, OBTER AS TAREFAS DO DIA DA PENDÊNCIA E ADICIONAR EM tarefas
										if(tarefaPendencia != null) {
											tFilterAux.setInicio(new DateFilter());
											tFilterAux.getInicio().setTypeFilter(TypeFilter.ENTRE);
											tFilterAux.getInicio().setInicio(tarefaPendencia.getInicio());
											tFilterAux.getInicio().setFim(tarefaPendencia.getFim());
											
											PagedList<Tarefa> t = TarefaBo.getInstance().getList(
													TarefaExampleBuilder.newInstance(tFilterAux).exampleStatusExecucaoOuConcluido());
											
											if(t.getTotal() > 0) {
												if(tAux.getList() == null)
													tAux.setList(new ArrayList<Tarefa>());
												
												tAux.getList().addAll(t.getList());
												tAux.setTotal(tAux.getTotal()+t.getList().size());
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									if(tAux.getTotal() > 0)
										continue;
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
								if(filaEspera.getFichaColeta() != null)
									filaEspera.getFichaColeta().getRespostaFichaColetas().forEach(rF -> {
										rF.setFicha(filaEspera.getFichaColeta());
										
										if(rF.getItens() != null)
											rF.getItens().forEach(iRF -> iRF.setResposta(rF));
									});
								
								if ( filaEspera.getRiscoPotencial() != null && 
										filaEspera.getRiscoPotencial().getRiscoEmpregados() != null )
									filaEspera.getRiscoPotencial().getRiscoEmpregados().forEach(r -> 
										r.setRiscoPotencial(filaEspera.getRiscoPotencial()));
								
								//INSTANCIAR
								Atendimento atendimento = new Atendimento();
								atendimento.setFilaAtendimentoOcupacional(fA);
								atendimento.setFilaEsperaOcupacional(filaEspera);
								atendimento.setTarefa(tarefa);
								atendimento = AtendimentoBo.getInstance().addAtualizacao(atendimento);
								fA.setAtualizacao(tarefa.getAtualizacao());
								
								//CRIAR A FICHA DE TRIAGEM
								if(!rE.isAcolhimento() && filaEspera.getRiscoPotencial() != null)
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
			
			if(atendimentos.getList() != null) {
				atendimentos.getList().forEach(a->{
					if( a.getFilaAtendimentoOcupacional() != null && a.getFilaAtendimentoOcupacional()
							.getAtualizacoes() != null) {
						a.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(aa->{
							aa.setFila(a.getFilaAtendimentoOcupacional());
						});
					}
				});
			}
			
			// 7 - SALVAR A LISTA DE ATENDIMENTO NO BANCO
			AtendimentoBo.getInstance().saveList(
					atendimentos.getList().stream().filter(a->a.getId() == 0).collect(Collectors.toList()));
		}
				
		// 8 - RETORNAR A LISTA DE ATENDIMENTO
		return  AtendimentoBo.getInstance().getBuilder(atendimentos.getList()).loadTarefa().getEntityList();
	}
	
	private TarefaFilter criarFiltroTarefa(FilaEsperaOcupacional filaEspera) {
		TarefaFilter tarefaFilterAux = new TarefaFilter();
		tarefaFilterAux.setPageNumber(1);
		tarefaFilterAux.setPageSize(1);
		tarefaFilterAux.setCliente(new EmpregadoFilter());
		tarefaFilterAux.getCliente().setId(filaEspera.getEmpregado().getId());
		tarefaFilterAux.setServico(new ServicoFilter());
		tarefaFilterAux.getServico().setGrupo(GrupoServico.ATENDIMENTO_OCUPACIONAL);
		
		Calendar calendarAux = Calendar.getInstance();
		calendarAux.setTime(Helper.getToday());
		calendarAux.add(Calendar.DATE, 1);
		
		tarefaFilterAux.setInicio(new DateFilter());
		tarefaFilterAux.getInicio().setTypeFilter(TypeFilter.ENTRE);
		tarefaFilterAux.getInicio().setInicio(Helper.getToday());
		tarefaFilterAux.getInicio().setFim(calendarAux.getTime());
		return tarefaFilterAux;
	}
	
	public Atendimento setTriagens(Atendimento atendimento) throws Exception {
		
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
