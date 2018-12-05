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
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;

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
import br.com.saude.api.model.creation.builder.entity.ConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.example.ConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.AsoFactory;
import br.com.saude.api.model.creation.factory.entity.ExameFactory;
import br.com.saude.api.model.entity.filter.ClinicaFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoExameFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.Clinica;
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
import br.com.saude.api.model.persistence.ConvocacaoDao;
import br.com.saude.api.util.constant.Operador;
import br.com.saude.api.util.constant.StatusEmpregado;
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
		
		convocacao.setProfissiograma(ProfissiogramaBo.getInstance()
				.getById(convocacao.getProfissiograma().getId()));
		
		List<Exame> exames = getExames(empregado, convocacao);
		
		if(empregadoConvocacao.getEmpregadoConvocacaoExames() == null)
			empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
		
		//REGISTRAR A EXIGÊNCIA DE RELATÓRIO MÉDICO
		exames.forEach(e->{
			EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
			empregadoConvocacaoExame.setExame(e);
			empregadoConvocacaoExame.setExigeRelatorio(e.isExigeRelatorio());
			empregadoConvocacaoExame.setOpcional(e.isOpcional());
			empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
		});
		
		empregadoConvocacao.setEmpregado(empregado);
		convocacao.getEmpregadoConvocacoes().set(0, empregadoConvocacao);
		
		return convocacao;
	}
	
	public Convocacao getEmpregadoConvocacoesByGerencia(Convocacao convocacao) throws Exception {
		
		GerenciaConvocacao gerenciaConvocacao = convocacao.getGerenciaConvocacoes().get(0);
		
		convocacao.setProfissiograma(ProfissiogramaBo.getInstance()
				.getById(convocacao.getProfissiograma().getId()));
		
		//CASO A GERÊNCIA CONVOCAÇÃO JÁ EXISTA, OBTER EMPREGADO CONVOCAÇÕES
		if(gerenciaConvocacao.getId() > 0) {
			convocacao.setEmpregadoConvocacoes(
					ConvocacaoBuilder.newInstance(getDao().getByIdGerencia(convocacao))
						.loadEmpregadoConvocacoes().getEntity().getEmpregadoConvocacoes());
		}
		
		EmpregadoFilter filter = new EmpregadoFilter();
		filter.setGerencia(new GerenciaFilter());
		filter.getGerencia().setId(gerenciaConvocacao.getGerencia().getId());
		filter.setStatus(StatusEmpregado.getInstance().ATIVO);
		filter.setVinculo(convocacao.getProfissiograma().getVinculo());
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		
		List<Empregado> empregados = EmpregadoBo.getInstance()
									.getListFunctionLoadGrupoMonitoramentos(filter).getList();
		
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
				
				//REGISTRAR A EXIGÊNCIA DE RELATÓRIO MÉDICO
				exameList.forEach(eE->{
					EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
					empregadoConvocacaoExame.setExame(eE);
					empregadoConvocacaoExame.setExigeRelatorio(eE.isExigeRelatorio());
					empregadoConvocacaoExame.setOpcional(eE.isOpcional());
					empregadoConvocacaoExames.add(empregadoConvocacaoExame);
				});
				
				empregadoConvocacao.setEmpregadoConvocacaoExames(empregadoConvocacaoExames);
				
				empregadoConvocacao.setEmpregado(e);
				convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);
			}
		});
		
		return convocacao;
	}
	
	@SuppressWarnings("resource")
	private String[] processarConvocacao(Convocacao convocacao, boolean gerarZip) throws Exception {
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uri = new URI(Helper.getProjectPath()
				.replace("/WEB-INF/classes", "")+"REPORT/GuiaExames.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uri.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		//CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri = null;
		File pdf;
		uri = new URI(Helper.getProjectPath()
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
			
			//GERAR GUIAS SEPARADAS POR CLÍNICA
			ClinicaFilter cFilter = new ClinicaFilter();
			cFilter.setPageNumber(1);
			cFilter.setPageSize(Integer.MAX_VALUE);
			cFilter.setUf(eC.getEmpregado().getBase().getUf());
			PagedList<Clinica> clinicas = ClinicaBo.getInstance().getListLoadAll(cFilter);
			List<EmpregadoConvocacaoExame> empregadoExames = new ArrayList<EmpregadoConvocacaoExame>();
			
			if(clinicas.getTotal() > 0) {
				for(Clinica clinica : clinicas.getList()) {
					
					eC.getEmpregadoConvocacaoExames().forEach(e->{
						if(clinica.getExames().contains(e.getExame())) {
							EmpregadoConvocacaoExame empregadoExame = new EmpregadoConvocacaoExame();
							empregadoExame.setExame(e.getExame());
							empregadoExames.add(empregadoExame);
						}
					});
					
					//SUBSTITUIR AS VARIÁVEIS
					StringReplacer stringReplacer = new StringReplacer(html.toString());
					stringReplacer = stringReplacer
							.replace("nomeClinica", clinica.getNome())
							.replace("enderecoClinica", Objects.toString(clinica.getEndereco(),"---"))
							.replace("telefonesClinica", Objects.toString(clinica.getTelefones(),"---"))
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
					
					if(convocacao.getFim() != null) {
						stringReplacer.replace("mesConvocacao", 
								new SimpleDateFormat("MMMMMMMMMM").format(convocacao.getFim()) +"/"+
										new SimpleDateFormat("YYYY").format(convocacao.getFim()));
					} else
						stringReplacer.replace("mesConvocacao","---");
					
					URI logoUri = new URI(Helper.getProjectPath()
							.replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
					stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
					
					//EXAMES
					if(empregadoExames.size() < 26) {
						int total = empregadoExames.size(); 
						for(int i=0; i<26; i++) {
							if(empregadoExames.size() == 26)
								break;
							
							if( ((i+1)%2 == 0 || (i/2 >= total)) && (total - i)+1+total <= 26 ) {
								Exame e = ExameFactory.newInstance().descricao("&nbsp;").get();
								EmpregadoConvocacaoExame eE = new EmpregadoConvocacaoExame();
								eE.setExame(e);
								try {
									empregadoExames.add(i, eE);
								}catch(IndexOutOfBoundsException ex) {
									empregadoExames.add(eE);
								}
							}
						}
					}
					
					String exames = "";
					String linha = "";
					int i = 0;
					boolean exigeRelatorio = false;
					
					for(EmpregadoConvocacaoExame e : empregadoExames) {
						if(i == 0) {
							linha += "<tr><td>"+e.getExame().getDescricao()+"</td>";
							i++;
						}else {
							linha += "<td>"+e.getExame().getDescricao()+"</td></tr>";
							exames+= linha;
							linha = "";
							i = 0;
						}
						
						if(!exigeRelatorio && e.isExigeRelatorio())
							exigeRelatorio = true;
					}
					
					if(linha != "") {
						linha += "<td></td></tr>";
						exames+= linha;
					}
					stringReplacer = stringReplacer.replace("exames", exames);
					
					//GERAR PDF
					pdfUri = new URI(uri.getPath()+"/"+Objects.toString(eC.getEmpregado().getMatricula().trim()+"-","")
					+"EXAMES_COMPLEMENTARES_"
					+clinica.getNome().replace(" ", "_")+""
					+ (exigeRelatorio ? "_exige_relatorio_medico" : "")
					+".pdf");
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
			}
		}
		
		//RETIRAR OS REGISTROS CUJO ID DO EXAME == 0, DEPOIS SALVA CONVOCAÇÃO
		convocacao.getEmpregadoConvocacoes().forEach(eC -> {
			eC.getEmpregadoConvocacaoExames().removeIf(x->x.getExame().getId() == 0);
		});
		this.save(convocacao);
		
		if(gerarZip) {
			//GERAR ZIP
			file = new File(file.getPath());
			URI zipUri = new URI(Helper.getProjectPath()
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
			
			return new String[] {zipUri.getPath(), Base64.getEncoder().encodeToString(zipArray)};
		}else 
			return new String[] {pdfUri.getPath(), ""};
	}
	
	public String processarConvocacaoBase64(Convocacao convocacao) throws Exception {
		return processarConvocacao(convocacao,true)[1];
	}
	
	public String processarConvocacaoPath(Convocacao convocacao) throws Exception {
		return processarConvocacao(convocacao,false)[0];
	}
	
	@Override
	public Convocacao getById(Object id) throws Exception {
		Convocacao newConvocacao = new Convocacao(); 
				
		if((int)id > 0)
			newConvocacao = this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		
		Convocacao convocacao = newConvocacao;
		
		if((int)id > 0)
			convocacao.setProfissiograma(ProfissiogramaBo.getInstance()
					.getById(convocacao.getProfissiograma().getId()));
		
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
			try {
				if(!eC.isAuditado()) {
					List<Exame> exames = getExames(eC.getEmpregado(), convocacao);
					exames.forEach(e->{
						
						if(eC.getEmpregadoConvocacaoExames().stream()
							.filter(eCX->eCX.getExame().equals(e))
							.count() == 0) {
							//REGISTRAR A EXIGÊNCIA DE RELATÓRIO MÉDICO
							EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
							empregadoConvocacaoExame.setExame(e);
							empregadoConvocacaoExame.setExigeRelatorio(e.isExigeRelatorio());
							empregadoConvocacaoExame.setOpcional(e.isOpcional());
							eC.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return ConvocacaoBuilder.newInstance(convocacao)
					.loadEmpregadoConvocacoesAll()
					.loadGerenciaConvocacoes()
					.loadProfissiograma().getEntity();
	}
	
	private Date getDataConvocacao(Empregado empregado, Convocacao convocacao) throws Exception {		
		// Considerar o mês anterior ao vencimento do Aso, caso o vencimento seja antes
		//  da data de convocação
		Date dataConvocacao = null;
		
		dataConvocacao = getValidadeAso(empregado);
		
		if(dataConvocacao != null) {
			dataConvocacao = Date.from(LocalDateTime.ofInstant(dataConvocacao.toInstant(), ZoneId.systemDefault())
										.minusMonths(1)
										.atZone(ZoneId.systemDefault())
										.toInstant());
		}
		
		if(convocacao != null && dataConvocacao != null) {
			//SETAR A MENOR DAS DUAS DATAS
			if(convocacao.getFim().before(dataConvocacao))
				dataConvocacao = convocacao.getFim(); 
		}else if(convocacao != null) {
			dataConvocacao = convocacao.getFim();
		}
		
		return dataConvocacao;
	}
	
	private Date getValidadeAso(Empregado empregado) throws Exception {
		Aso aso = AsoFactory.newInstance().empregado(empregado).get();
		aso = AsoBo.getInstance().getUltimoByEmpregado(aso);
		
		if(aso != null)
			return aso.getValidade();
		return null;
	}
	
	private List<Exame> getExames(Empregado empregado, Convocacao convocacao) throws Exception{
		
		Profissiograma profissiograma = convocacao.getProfissiograma();
		
		//OBTER A LISTA DE GRUPOS DE MONITORAMENTO RECORRENTES
		BooleanFilter recorrente = new BooleanFilter();
		recorrente.setValue(1);
		
		GrupoMonitoramentoFilter filter = new GrupoMonitoramentoFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		filter.setRecorrente(recorrente);
		
		List<GrupoMonitoramento> grupoMonitoramentosRecorrentes = GrupoMonitoramentoBo.getInstance()
											.getList(filter)
											.getList();
		
		List<Exame> exames = new ArrayList<Exame>();
		Map<Integer,Integer> equivalencias = new HashMap<Integer,Integer>();
		
		profissiograma.getGrupoMonitoramentoProfissiogramas().forEach(gmp -> {
			
			if(empregado.getGrupoMonitoramentos().contains(gmp.getGrupoMonitoramento()) ||
					grupoMonitoramentosRecorrentes.contains(gmp.getGrupoMonitoramento()) ) {
				
				gmp.getGrupoMonitoramentoProfissiogramaExames().forEach(gE -> {
					Date dataConvocacao = null;
					boolean criteriosAtendidos = true;
					
					gE.getExame().setOpcional(gE.isOpcional());
					
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
									   								new java.sql.Date(dataConvocacao.getTime()).toLocalDate() : 
								   											LocalDate.now());
							   
							   valor = periodo.getYears()+"";
							   break;
						      
						   case TipoCriterio.SEXO :
							   valor = empregado.getPessoa().getSexo();
							   break;
							      
						   case TipoCriterio.EXAME :
							   
							   break;
							   
						   case TipoCriterio.CARGO :
							   valor = empregado.getCargo().getNome() +"";  
							   break;
							      
						   case TipoCriterio.ENFASE :
							   if(empregado.getEnfase() != null)
								   valor = empregado.getEnfase().getId()+"";   
							   break;
							   
						   case TipoCriterio.EXIGE_RELATORIO_MEDICO :
							   valor = Objects.toString(true);
							   gE.getExame().setExigeRelatorio(true);
							   break;
							   
						   case TipoCriterio.PERIODICIDADE :
							   //OBTER A QUANTIDADE DE MESES DA PERIODICIDADE
							   try {
								   criterio.setValor(									
										   Objects.toString(PeriodicidadeBo.getInstance().getById(
												   new Integer(criterio.getValor())).getMeses()));
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							   
							   Calendar calendar = Calendar.getInstance();
							   calendar.setTime(convocacao.getInicio());
							   calendar.add(Calendar.MONTH, (-1)* new Integer(criterio.getValor()) );
							   
							   EmpregadoConvocacaoExameFilter xFilter = new EmpregadoConvocacaoExameFilter();
							   xFilter.setPageNumber(1);
							   xFilter.setPageSize(1);
							   xFilter.setExame(new ExameFilter());
							   xFilter.getExame().setId(gE.getExame().getId());
							   xFilter.setEmpregadoConvocacao(new EmpregadoConvocacaoFilter());
							   xFilter.getEmpregadoConvocacao().setEmpregado(new EmpregadoFilter());
							   xFilter.getEmpregadoConvocacao().getEmpregado().setId(empregado.getId());
							   xFilter.setRealizacao(new DateFilter());
							   xFilter.getRealizacao().setInicio(calendar.getTime());
							   xFilter.getRealizacao().setTypeFilter(TypeFilter.MAIOR_IGUAL);
							   
							   valor = (new Integer(criterio.getValor()) + 1)+"";
							   
							   try {
								   if(EmpregadoConvocacaoExameBo.getInstance().getList(xFilter).getTotal() > 0)
									   valor = "0";
							   } catch (Exception e1) {
								   e1.printStackTrace();
							   }							   
							   
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
								if( !(new Integer(valor)
										.compareTo(new Integer(criterio.getValor())) > 0) ) {
									criteriosAtendidos = false;								
									break loop;
								}
								break;
								
							case Operador.MAIOR_IGUAL:
								if( !(new Integer(valor)
										.compareTo(new Integer(criterio.getValor())) >= 0) ) {
									criteriosAtendidos = false;								
									break loop;
								}
								break;
								
							case Operador.MENOR:
								if( !(new Integer(valor)
										.compareTo(new Integer(criterio.getValor())) < 0) ) {
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
							
							case Operador.NUNCA_REALIZADO:
								EmpregadoConvocacaoExameFilter f = new EmpregadoConvocacaoExameFilter();
								f.setPageNumber(1);
								f.setPageSize(Integer.MAX_VALUE);
								f.setEmpregadoConvocacao(new EmpregadoConvocacaoFilter());
								f.getEmpregadoConvocacao().setEmpregado(new EmpregadoFilter());
								f.getEmpregadoConvocacao().getEmpregado().setId(empregado.getId());
								f.setExame(new ExameFilter());
								f.getExame().setId(gE.getExame().getId());
								
								try {
									PagedList<EmpregadoConvocacaoExame> pagedList = EmpregadoConvocacaoExameBo.getInstance()
											.getList(f);
									
									if(pagedList.getTotal() > 0) {
										criteriosAtendidos = false;								
										break loop;
									}
								} catch (Exception e1) {
									
								}
								
								break;
								
							case Operador.CONTEM :
								for(String val : criterio.getNome().split("%")) {
									if(!valor.contains(val)) {
										criteriosAtendidos = false;								
										break loop;
									}
								}
								break;
								
							case Operador.EQUIVALENTE:
								equivalencias.put(Integer.parseInt(criterio.getValor()), gE.getExame().getId());
								break;
							
							default :
						}
					}
					
					if (criteriosAtendidos)
						exames.add(gE.getExame());
					
				});
			}
		});
		
		//REMOVER EXAMES REPETIDOS
		List<Exame> examesRetorno = new ArrayList<Exame>();
		
		exames.forEach(e->{
			if(!examesRetorno.contains(e))
				examesRetorno.add(e);
		});
		
		//DEFINIR AS OBRIGATORIEDADES
		examesRetorno.forEach(e->{
			e.setOpcional(exames.stream().filter(ee->ee.equals(e) && !ee.isOpcional()).count() == 0);
		});
		
		//REMOVER OS EXAMES EQUIVALENTES
		equivalencias.forEach((key,value)->{
			if(examesRetorno.stream().filter(e->e.getId() == key).count() > 0)
				examesRetorno.removeIf(e-> e.getId() == value);
		});
		examesRetorno.sort(new Comparator<Exame>(){
			@Override
			public int compare(Exame e1, Exame e2) {
				return e1.getOrdem() > e2.getOrdem() ? 1 :(e1.getOrdem() < e2.getOrdem() ? -1 : 0);
			}
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
