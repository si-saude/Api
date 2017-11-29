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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.ConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.example.ConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.AsoFactory;
import br.com.saude.api.model.creation.factory.entity.ExameFactory;
import br.com.saude.api.model.creation.factory.entity.ResultadoExameFactory;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Criterio;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.RelatorioMedico;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.ConvocacaoDao;
import br.com.saude.api.util.constant.Operador;
import br.com.saude.api.util.constant.TipoCriterio;

@SuppressWarnings("deprecation")
public class ConvocacaoBo extends GenericBo<Convocacao, ConvocacaoFilter, ConvocacaoDao, 
									ConvocacaoBuilder, ConvocacaoExampleBuilder>	 {

	private static ConvocacaoBo instance;
	
	private ConvocacaoBo() {
		super();
	}
	
	public static ConvocacaoBo getInstance() {
		if(instance == null)
			instance = new ConvocacaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadProfissiograma().loadGerenciaConvocacoes().loadEmpregadoConvocacoesAll();
		};
	}
	
	public Convocacao getEmpregadoConvocacao(Convocacao convocacao) throws Exception {
		EmpregadoConvocacao empregadoConvocacao = convocacao.getEmpregadoConvocacoes().get(0); 
		Empregado empregado = empregadoConvocacao.getEmpregado();
		empregado = EmpregadoBo.getInstance().getByIdLoadGrupoMonitoramentos(empregado.getId());
		
		List<Exame> exames = getExames(empregado, convocacao);
		
		if(empregadoConvocacao.getEmpregadoConvocacaoExames() == null)
			empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
		
		exames.forEach(e->{
			//SE O EXAME EXIGIR RELATÓRIO MÉDICO, ADICIONAR O RELATÓRIO
			EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
			empregadoConvocacaoExame.setExame(e);
			
			if(e.isExigeRelatorio()) {
				RelatorioMedico relatorioMedico = new RelatorioMedico();
				empregadoConvocacaoExame.setRelatorioMedico(relatorioMedico);
			}
			
			empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
		});
		
		empregadoConvocacao.setEmpregado(empregado);
		convocacao.getEmpregadoConvocacoes().set(0, empregadoConvocacao);
		
		return convocacao;
	}
	
	public Convocacao getEmpregadoConvocacoesByGerencia(Convocacao convocacao) throws Exception {
		
		GerenciaConvocacao gerenciaConvocacao = convocacao.getGerenciaConvocacoes().get(0);
		
		//CASO A GERÊNCIA CONVOCAÇÃO JÁ EXISTA, OBTER EMPREGADO CONVOCAÇÕES
		if(gerenciaConvocacao.getId() > 0) {
			convocacao.setEmpregadoConvocacoes(
					ConvocacaoBuilder.newInstance(getDao().getByIdGerencia(convocacao))
						.loadEmpregadoConvocacoes().getEntity().getEmpregadoConvocacoes());
		}
		
		EmpregadoFilter filter = new EmpregadoFilter();
		filter.setGerencia(new GerenciaFilter());
		filter.getGerencia().setId(gerenciaConvocacao.getGerencia().getId());
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		
		List<Empregado> empregados = EmpregadoBo.getInstance()
									.getListFunctionLoadGrupoMonitoramentosExames(filter).getList();
		
		empregados.forEach(e->{
			List<Exame> exameList = new ArrayList<Exame>();
			
			try {
				exameList = getExames(e, convocacao);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//ADICIONAR O EMPREGADO NA CONVOCAÇÃO, CASO NÃO ESTEJA
			//VERIFICAR DIVERGÊNCIA DE EXAMES, CASO EXISTA
			EmpregadoConvocacao empregadoConvocacao = null;
			if(convocacao.getEmpregadoConvocacoes().stream()
					.filter(eC ->eC.getEmpregado().getId() == e.getId())
					.count() == 0) {
				
				List<EmpregadoConvocacaoExame> empregadoConvocacaoExames = new ArrayList<EmpregadoConvocacaoExame>(); 
				empregadoConvocacao = new EmpregadoConvocacao();
				
				exameList.forEach(eE->{
					EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
					empregadoConvocacaoExame.setExame(eE);
					
					if(eE.isExigeRelatorio()) {
						RelatorioMedico relatorioMedico = new RelatorioMedico();
						empregadoConvocacaoExame.setRelatorioMedico(relatorioMedico);
					}
					
					empregadoConvocacaoExames.add(empregadoConvocacaoExame);
				});
				
				empregadoConvocacao.setEmpregadoConvocacaoExames(empregadoConvocacaoExames);
				
				empregadoConvocacao.setEmpregado(e);
				convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);
			}else {
				for(int i=0; i<convocacao.getEmpregadoConvocacoes().size();i++) {
					if(convocacao.getEmpregadoConvocacoes().get(i).getEmpregado().getId()
							== e.getId()) {
						convocacao.getEmpregadoConvocacoes().set(i, 
							setDivergente(convocacao.getEmpregadoConvocacoes().get(i), exameList));
						break;
					}
				}
			}
		});
		
		return convocacao;
	}
	
	private GerenciaConvocacao findGerenciaConvocacaoByEmpregado(Empregado empregado, Convocacao convocacao) {
		GerenciaConvocacao gC = null;
		
		try {
			gC = convocacao.getGerenciaConvocacoes().stream()
									.filter(g->g.getGerencia().getId() == empregado.getGerencia().getId())
									.findFirst().get();
		}catch(NoSuchElementException ex) {
			ex.printStackTrace();
		}
		
		return gC;
	}
	
	@SuppressWarnings("resource")
	public String processarConvocacao(Convocacao convocacao) throws Exception {
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				.replace("/WEB-INF/classes", "")+"REPORT/GuiaExames.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uri.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		//CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri;
		File pdf;
		uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				+"convocacao/"+convocacao.getId());
		File file = new File(uri.getPath());
		file.mkdirs();
		FileUtils.cleanDirectory(file);
		
		List<EmpregadoConvocacao> empregadoConvocacoes = convocacao
					.getEmpregadoConvocacoes().stream().filter(e->e.isSelecionado()).collect(Collectors.toList());
		
		//PARA CADA EMPREGADO SELECIONADO, FAZER:
		for(EmpregadoConvocacao eC:empregadoConvocacoes) {
			
			if(eC.getEmpregadoConvocacaoExames() == null || eC.getEmpregadoConvocacaoExames().size() == 0)
				continue;
			
			//CARREGAR O EMPREGADO
			eC.setEmpregado(EmpregadoBo.getInstance().getById(eC.getEmpregado().getId()));
			
			//SUBSTITUIR AS VARIÁVEIS
			StringReplacer stringReplacer = new StringReplacer(html.toString());
			stringReplacer = stringReplacer
				.replace("nomeEmpregado", eC.getEmpregado().getPessoa().getNome())
				.replace("matriculaEmpregado", Objects.toString(eC.getEmpregado().getMatricula(),"---"))
				.replace("cpfEmpregado", Objects.toString(eC.getEmpregado().getPessoa().getCpf(),"---"))
				.replace("chaveEmpregado", Objects.toString(eC.getEmpregado().getChave(),"---"))
				.replace("gerenciaEmpregado", eC.getEmpregado().getGerencia().getCodigoCompleto())
				.replace("turmaEmpregado", "---")
				.replace("tipoConvocacao", Objects.toString(convocacao.getTipo(),"---"))
				.replace("centroCusto", "---")
				.replace("emailEmpregado", Objects.toString(eC.getEmpregado().getPessoa().getEmail(),"---"))
				.replace("data", new Date().toLocaleString().substring(0, 10));
			
			if(eC.getEmpregado().getCargo() != null)
				stringReplacer.replace("cargoEmpregado", Objects.toString(eC.getEmpregado().getCargo().getNome(),"---"));
			else
				stringReplacer.replace("cargoEmpregado","---");
			
			if(eC.getEmpregado().getPessoa().getDataNascimento() != null)
				stringReplacer.replace("dataNascimentoEmpregado", eC.getEmpregado().getPessoa().getDataNascimento().toLocaleString().substring(0, 10));
			else
				stringReplacer.replace("dataNascimentoEmpregado","---");
			
			//OBTER O MÊS DA CONVOCAÇÃO
			Date dataConvocacao = null;
			GerenciaConvocacao gC = findGerenciaConvocacaoByEmpregado(eC.getEmpregado(), convocacao);
			
			if(gC != null)
				dataConvocacao = getDataConvocacao(eC.getEmpregado(), convocacao);
			
			if(dataConvocacao != null) {
				stringReplacer.replace("mesConvocacao", 
						new SimpleDateFormat("MMMMMMMMMM").format(dataConvocacao) +"/"+
						new SimpleDateFormat("YYYY").format(dataConvocacao));
			} else
				stringReplacer.replace("mesConvocacao","---");
			
			URI logoUri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					.replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
			stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
			
			
			//EXAMES
			if(eC.getEmpregadoConvocacaoExames().size() < 26) {
				int total = eC.getEmpregadoConvocacaoExames().size(); 
				for(int i=0; i<26; i++) {
					if(eC.getEmpregadoConvocacaoExames().size() == 26)
						break;
					
					if( ((i+1)%2 == 0 || (i/2 >= total)) && (total - i)+1+total <= 26 ) {
						Exame e = ExameFactory.newInstance().descricao("&nbsp;").get();
						EmpregadoConvocacaoExame eE = new EmpregadoConvocacaoExame();
						eE.setExame(e);
						try {
							eC.getEmpregadoConvocacaoExames().add(i, eE);
						}catch(IndexOutOfBoundsException ex) {
							eC.getEmpregadoConvocacaoExames().add(eE);
						}
					}
				}
			}
			
			String exames = "";
			String linha = "";
			int i = 0;
			
			for(EmpregadoConvocacaoExame e : eC.getEmpregadoConvocacaoExames()) {
				if(i == 0) {
					linha += "<tr><td>"+e.getExame().getDescricao()+"</td>";
					i++;
				}else {
					linha += "<td>"+e.getExame().getDescricao()+"</td></tr>";
					exames+= linha;
					linha = "";
					i = 0;
				}
			}
			
			if(linha != "") {
				linha += "<td></td></tr>";
				exames+= linha;
			}
			stringReplacer = stringReplacer.replace("exames", exames);
			
			//GERAR PDF
			pdfUri = new URI(uri.getPath()+"/"+Objects.toString(eC.getEmpregado().getChave()+"-","")
					+eC.getEmpregado().getPessoa().getNome().replace(' ', '_')+".pdf");
			pdf = new File(pdfUri.getPath());
			OutputStream stream = new FileOutputStream(pdf);
			Document doc = new Document();
			PdfWriter.getInstance(doc, stream);
			doc.open();
			
			HTMLWorker htmlWorker = new HTMLWorker(doc);
			htmlWorker.parse(new StringReader(stringReplacer.result()));
			
			doc.close();
			stream.close();
		}
		
		
		//GERAR ZIP
		file = new File(file.getPath());
		URI zipUri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation()
									.toString()+"convocacao/"+convocacao.getTitulo().replace(' ', '_')+".zip");
		File zipFile = new File(zipUri.getPath());
		
		ZipEntry zipEntry;
		FileInputStream in;
		FileOutputStream stream = new FileOutputStream(zipFile);
		ZipOutputStream zipStream = new ZipOutputStream(stream);
		byte[] buffer = new byte[1024];
		
		for(File f : file.listFiles() ) {
			zipEntry = new ZipEntry( f.getName() );
			zipStream.putNextEntry(zipEntry);
			
			in = new FileInputStream( f.getAbsolutePath() );
			
			int len;
        	while ((len = in.read(buffer)) > 0) {
        		zipStream.write(buffer, 0, len);
        	}

        	in.close();
		}

		zipStream.closeEntry();
		zipStream.close();
		FileUtils.cleanDirectory(file);
		
		//RETORNO
		byte[] zipArray = new byte[(int) zipFile.length()];
		new FileInputStream(zipFile).read(zipArray);
		return Base64.getEncoder().encodeToString(zipArray);
	}
	
	@Override
	public Convocacao getById(Object id) throws Exception {
		Convocacao newConvocacao = new Convocacao(); 
				
		if((int)id > 0)
			newConvocacao = this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		
		Convocacao convocacao = newConvocacao;
		
		if(convocacao.getGerenciaConvocacoes() != null)
			convocacao.getGerenciaConvocacoes().forEach(g->g.setSelecionado(true));
		else
			convocacao.setGerenciaConvocacoes(new ArrayList<GerenciaConvocacao>());
		
		//OBTÉM AS GERÊNCIAS QUE NÃO ESTÃO SELECIONADAS NA CONVOCAÇÃO
		List<Integer> ids = new ArrayList<Integer>();
		convocacao.getGerenciaConvocacoes().forEach(g->ids.add(g.getGerencia().getId()));
		
		List<Gerencia> gerencias = GerenciaBo.getInstance().getListNotIn(ids);
		gerencias.forEach(g->{
			GerenciaConvocacao gerenciaConvocacao = new GerenciaConvocacao();
			gerenciaConvocacao.setGerencia(g);
			convocacao.getGerenciaConvocacoes().add(gerenciaConvocacao);
		});
		convocacao.getGerenciaConvocacoes().sort(new Comparator<GerenciaConvocacao>() {
			public int compare(GerenciaConvocacao arg0, GerenciaConvocacao arg1) {
				return ((!arg0.isSelecionado())+" - "+arg0.getGerencia().getCodigoCompleto())
						.compareTo((!arg1.isSelecionado())+" - "+arg1.getGerencia().getCodigoCompleto());
			}
		});
		
		if(convocacao.getEmpregadoConvocacoes() == null)
			convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
		
		//VERIFICAR OS EXAMES DOS EMPREGADOS
		convocacao.getEmpregadoConvocacoes().forEach(eC->{
			List<Exame> exames = new ArrayList<Exame>();;
			
			try {
				exames = getExames(eC.getEmpregado(), convocacao);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
			eC = setDivergente(eC, exames);
		});
		
		return ConvocacaoBuilder.newInstance(convocacao)
					.loadEmpregadoConvocacoesAll()
					.loadGerenciaConvocacoes()
					.loadProfissiograma().getEntity();
	}
	
	private EmpregadoConvocacao setDivergente(EmpregadoConvocacao eC, List<Exame> exames) {
				
		//VERIFICAR SE OS EXAMES DA CONVOCAÇÃO SÃO OS MESMOS DO PROFISSIOGRAMA
		int qtd = 0;
		for(EmpregadoConvocacaoExame eE:eC.getEmpregadoConvocacaoExames()) {
			for(Exame exameProfissiograma:exames) {
				if(eE.getExame().getId() == exameProfissiograma.getId()) {
					qtd++;
					break;
				}
			}
		}
		
		if(eC.getEmpregadoConvocacaoExames().size() != exames.size() || exames.size() != qtd)
			eC.setDivergente(true);
		
		return eC;
	}
	
	private Date getDataConvocacao(Empregado empregado, Convocacao convocacao) throws Exception {		
		// Considerar o mês anterior ao vencimento do Aso, caso o vencimento seja antes
		//  da data de convocação
		Date dataConvocacao = null;
		GerenciaConvocacao gC = findGerenciaConvocacaoByEmpregado(empregado, convocacao);
		
		dataConvocacao = getValidadeAso(empregado);
		
		if(dataConvocacao != null) {
			dataConvocacao = Date.from(LocalDateTime.from(dataConvocacao.toInstant())
								.minusMonths(1).atZone(ZoneId.systemDefault())
								.toInstant());
		}
		
		if(gC != null && dataConvocacao != null) {
			//SETAR A MENOR DAS DUAS DATAS
			if(gC.getFim().before(dataConvocacao))
				dataConvocacao = gC.getFim(); 
		}else if(gC != null) {
			dataConvocacao = gC.getFim();
		}
		
		return dataConvocacao;
	}
	
	private Date getValidadeAso(Empregado empregado) throws Exception {
		Aso aso = AsoFactory.newInstance().empregadoConvocacao()
				.empregadoConvocacaoEmpregado(empregado).get();
		aso = AsoBo.getInstance().getUltimoByEmpregado(aso);
		
		if(aso != null)
			return aso.getValidade();
		return null;
	}
	
	private List<Exame> getExames(Empregado empregado, Convocacao convocacao) throws Exception{
		List<GrupoMonitoramento> grupoMonitoramentosProfissiograma = new ArrayList<GrupoMonitoramento>();
		List<GrupoMonitoramento> grupoMonitoramentosRecorrentes = new ArrayList<GrupoMonitoramento>();
		
		Profissiograma profissiograma = ProfissiogramaBo.getInstance()
				.getById(convocacao.getProfissiograma().getId());
		
		//OBTER A LISTA DE GRUPOS DE MONITORAMENTO RECORRENTES
		BooleanFilter recorrente = new BooleanFilter();
		recorrente.setValue(1);
		
		GrupoMonitoramentoFilter filter = new GrupoMonitoramentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setRecorrente(recorrente);
		
		grupoMonitoramentosRecorrentes = GrupoMonitoramentoBo.getInstance()
											.getListLoadGrupoMonitoramentoExames(filter)
											.getList();
		
		if(empregado.getGrupoMonitoramentos() == null)
			empregado.setGrupoMonitoramentos(grupoMonitoramentosRecorrentes);
		else
			empregado.getGrupoMonitoramentos().addAll(grupoMonitoramentosRecorrentes);
			
		profissiograma.getGrupoMonitoramentos().addAll(grupoMonitoramentosRecorrentes);
		
		
		
		//REMOVER GRUPOS DE MONITORAMENTO REPETIDOS
		profissiograma.getGrupoMonitoramentos().forEach(g->{
			if(!grupoMonitoramentosProfissiograma.contains(g))
				grupoMonitoramentosProfissiograma.add(g);
		});
		profissiograma.setGrupoMonitoramentos(grupoMonitoramentosProfissiograma);
		
		List<GrupoMonitoramento> grupoMonitoramentos = new ArrayList<GrupoMonitoramento>();
		List<Exame> exames = new ArrayList<Exame>();		
		
		profissiograma.getGrupoMonitoramentos().forEach(g->{
			if(empregado.getGrupoMonitoramentos().contains(g))
				grupoMonitoramentos.add(g);
		});
		
		grupoMonitoramentos.forEach(g->{
			g.getGrupoMonitoramentoExames().forEach(gE->{
				Date dataConvocacao = null;
				boolean criteriosAtendidos = true;
				
				try {
					dataConvocacao = getDataConvocacao(empregado, convocacao);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				//VERIFICAÇÃO DO ATENDIMENTO DOS CRITÉRIOS
				loop:
				for(Criterio criterio : gE.getCriterios()) {
					String valor="";
					
					switch(criterio.getTipo()) {
					   case TipoCriterio.IDADE :
						   LocalDate nascimento = empregado.getPessoa().getDataNascimento().toInstant()
						   							.atZone(ZoneId.systemDefault()).toLocalDate();
						   Period periodo = Period.between(nascimento, dataConvocacao != null ? 
								   										LocalDate.from(dataConvocacao.toInstant()) : 
							   											LocalDate.now());
						   valor = periodo.getYears()+"";
						   break;
					      
					   case TipoCriterio.SEXO :
						   valor = empregado.getPessoa().getSexo();
						   break;
						      
					   case TipoCriterio.EXAME :
						   
						   break;
						   
					   case TipoCriterio.CARGO :
						   valor = empregado.getCargo().getId()+"";
						   break;
						      
					   case TipoCriterio.FUNCAO :
						   valor = empregado.getFuncao().getId()+"";   
						   break;
						   
					   case TipoCriterio.EXIGE_RELATORIO_MEDICO :
						   valor = Objects.toString(true);
						   break;
					   
					   default :
					}
					
					//SE O CRITÉRIO NÃO FOR SATISFEITO, SETAR FALSE NO FLAG E SAIR DA REPETIÇÃO
					switch(criterio.getOperador()) {
						
						case Operador.IGUAL :
							if(!valor.equals(criterio.getValor())) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
							
						case Operador.DIFERENTE:
							if(valor.equals(criterio.getValor())) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
							
						case Operador.MAIOR:
							if( !(valor.compareTo(criterio.getValor()) > 0) ) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
							
						case Operador.MAIOR_IGUAL:
							if( !(valor.compareTo(criterio.getValor()) >= 0) ) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
							
						case Operador.MENOR:
							if( !(valor.compareTo(criterio.getValor()) < 0) ) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
							
						case Operador.MENOR_IGUAL:
							if( !(valor.compareTo(criterio.getValor()) <= 0) ) {
								criteriosAtendidos = false;								
								break loop;
							}
							break;
						
						default :
					}
				}
				
				boolean periodicidadeAtendida = true;

				//VERIFICAÇÃO DA PERIODICIDADE
				if(gE.getPeriodicidade() != null) {
					
					//1 - OBTER O ÚLTIMO RESULTADO DESTE EMPREGADO PARA ESTE EXAME
					ResultadoExame resultadoExame = ResultadoExameFactory.newInstance()
							.empregadoConvocacao().empregadoConvocacaoEmpregado(empregado)
							.exame(gE.getExame()).get();
					
					try {
						resultadoExame = ResultadoExameBo.getInstance()
								.getUltimoByEmpregadoExame(resultadoExame);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					//2 - SE O RETORNO FOR DIFERENTE DE NULO, VERIFICAR SE O EXAME VAI VENCER NO
					// ANO DO PERIÓDICO
					if(resultadoExame != null) {
						Date dataLimiteExame = dataConvocacao;
						
						if(dataLimiteExame != null) {
							//OBTER O ÚLTIMO DIA DO ANO
							dataLimiteExame.setMonth(11);
							dataLimiteExame.setDate(31);
							
							//VERIFICAR SE O EXAME VAI VENCER NO ANO DO PERIÓDICO
							if(Date.from(LocalDateTime.from(resultadoExame.getData().toInstant())
										.plusMonths(gE.getPeriodicidade().getMeses())
										.atZone(ZoneId.systemDefault())
										.toInstant())
									.after(dataLimiteExame))
								periodicidadeAtendida = false;
						}
					}
				}
				
				if (criteriosAtendidos && periodicidadeAtendida) {
					if(gE.getCriterios().stream()
							.filter(c->c.getTipo().equals(TipoCriterio.EXIGE_RELATORIO_MEDICO))
							.count() > 0) {
						gE.getExame().setExigeRelatorio(true);
					}
					exames.add(gE.getExame());
				}
			});
		});
		
		//REMOVER EXAMES REPETIDOS
		List<Exame> examesRetorno = new ArrayList<Exame>();
		
		exames.forEach(e->{
			if(!examesRetorno.contains(e))
				examesRetorno.add(e);
		});		
		
		return examesRetorno;
	}
	
	@Override
	public Convocacao save(Convocacao convocacao) throws Exception {
		convocacao.getGerenciaConvocacoes().forEach(g->g.setConvocacao(convocacao));
		convocacao.getEmpregadoConvocacoes().forEach(e->{
			e.setConvocacao(convocacao);
			e.getEmpregadoConvocacaoExames().forEach(eC->eC.setEmpregadoConvocacao(e));
		});
		return super.save(convocacao);
	}
}
