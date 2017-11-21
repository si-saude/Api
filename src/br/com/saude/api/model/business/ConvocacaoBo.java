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
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
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
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Criterio;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.Profissiograma;
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
		
		empregadoConvocacao.setEmpregado(empregado);
		empregadoConvocacao.setExames(exames);
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
				
				empregadoConvocacao = new EmpregadoConvocacao();
				empregadoConvocacao.setEmpregado(e);
				empregadoConvocacao.setExames(exameList);
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
	
	public File processarConvocacao(Convocacao convocacao) throws Exception {
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
			
			//OBTER O CRONOGRAMA DA GERÊNCIA DO EMPREGADO
			GerenciaConvocacao gC = null; 
			
			try {
					gC = convocacao.getGerenciaConvocacoes().stream()
											.filter(g->g.getGerencia().getId() == eC.getEmpregado().getGerencia().getId())
											.findFirst().get();
			}catch(NoSuchElementException ex) {
				ex.printStackTrace();
			}
			
			if(gC != null) {
				stringReplacer.replace("mesConvocacao", 
						new SimpleDateFormat("MMMMMMMMMM").format(gC.getInicio()) +"/"+
						new SimpleDateFormat("YYYY").format(gC.getInicio()));
			} else
				stringReplacer.replace("mesConvocacao","---");
			
			URI logoUri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					.replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
			stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
			
			
			//EXAMES
			String exames = "";
			for(Exame exame : eC.getExames()) {
				exames+="<tr><td>"+exame.getDescricao()+"</td></tr>";				
			}
			stringReplacer = stringReplacer.replace("exames", exames);
			
			//GERAR PDF
			pdfUri = new URI(uri.getPath()+"/"+eC.getEmpregado().getPessoa().getNome().replace(' ', '_')+".pdf");
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
		return zipFile;
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
		for(Exame exameConvocacao:eC.getExames()) {
			for(Exame exameProfissiograma:exames) {
				if(exameConvocacao.getId() == exameProfissiograma.getId()) {
					qtd++;
					break;
				}
			}
		}
		
		if(eC.getExames().size() != exames.size() || exames.size() != qtd)
			eC.setDivergente(true);
		
		return eC;
	}
	
	private List<Exame> getExames(Empregado empregado, Convocacao convocacao) throws Exception{
		Profissiograma profissiograma = ProfissiogramaBo.getInstance()
				.getById(convocacao.getProfissiograma().getId());
		
		//OBTER A LISTA DE GRUPOS DE MONITORAMENTO E ADICIONAR AO PROFISSIOGRAMA
		BooleanFilter recorrente = new BooleanFilter();
		recorrente.setValue(1);
		
		GrupoMonitoramentoFilter filter = new GrupoMonitoramentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setRecorrente(recorrente);
		
		profissiograma.getGrupoMonitoramentos().addAll(
									GrupoMonitoramentoBo.getInstance()
														.getListLoadGrupoMonitoramentoExames(filter)
														.getList());
		
		List<GrupoMonitoramento> grupoMonitoramentos = new ArrayList<GrupoMonitoramento>();
		List<Exame> exames = new ArrayList<Exame>();
		
		empregado.getGrupoMonitoramentos().forEach(g->{
			if(profissiograma.getGrupoMonitoramentos().contains(g))
				grupoMonitoramentos.add(g);
		});
		
		grupoMonitoramentos.forEach(g->{
			g.getGrupoMonitoramentoExames().forEach(gE->{
				boolean criteriosAtendidos = true;
				
				loop:
				for(Criterio criterio : gE.getCriterios()) {
					String valor="";
					
					switch(criterio.getTipo()) {
					   case TipoCriterio.IDADE :
						   LocalDate nascimento = empregado.getPessoa().getDataNascimento().toInstant()
						   							.atZone(ZoneId.systemDefault()).toLocalDate();
						   Period periodo = Period.between(nascimento, LocalDate.now());
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
				
				if (criteriosAtendidos)
					exames.add(gE.getExame());
			});
		});
		
		return exames;
	}
	
	@Override
	public Convocacao save(Convocacao convocacao) throws Exception {
		convocacao.getGerenciaConvocacoes().forEach(g->g.setConvocacao(convocacao));
		convocacao.getEmpregadoConvocacoes().forEach(e->e.setConvocacao(convocacao));
		return super.save(convocacao);
	}
}
