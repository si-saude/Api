package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.CatBuilder;
import br.com.saude.api.model.creation.builder.example.CatExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.EmailFactory;
import br.com.saude.api.model.entity.dto.CatDto;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.po.Cat;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.CatDao;
import br.com.saude.api.model.persistence.report.CatReport;
import br.com.saude.api.util.constant.EnsaioVedacao;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TipoConvocacao;
import br.com.saude.api.util.constant.VinculoEmpregado;

@SuppressWarnings("deprecation")
public class CatBo extends GenericBo<Cat, CatFilter, CatDao, CatBuilder, CatExampleBuilder> 
	implements GenericReportBo<CatDto> {

	private static CatBo instance;

	private CatBo() {
		super();
	}

	public static CatBo getInstance() {
		if (instance == null)
			instance = new CatBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadProfissionalCaracterizacao().loadProfissionalClassificacao().
					loadMunicipio().loadAgenteCausador().loadNaturezaLesao().loadParteCorpoAtingida()
					.loadCnae().loadClassificaoGravidade().loadInstalacao().loadDiagnosticoProvavel()
					.loadExamesConvocacao();
		};
	}

	@Override
	public Cat getById(Object id) throws Exception {
		Cat cat = getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		cat = loadFiles(cat);
		
		if ( cat.getJornadaTrabalho() == 0 ) {
			Empregado empregado = EmpregadoBo.getInstance().getById(cat.getEmpregado().getId());
			if ( empregado.getGhe() != null )
				cat.setJornadaTrabalho( empregado.getGhe().getDuracaoJornada() );
		}
		
		return cat;
	}
	
	public List<CatDto> getCats() throws Exception{
		return CatReport.getInstance().getCats();
	}

	@Override
	public PagedList<Cat> getList(CatFilter filter) throws Exception {
		return super.getList(filter);
	}

	@Override
	public Cat save(Cat cat) throws Exception {
		if ( cat.getClassificacao() != null 
				&& cat.getClassificacao().getId() > 0 
				&& !cat.isConvocado() && cat.getDataEmissao() != null 
				&& !cat.getEmpregado().getVinculo().equals(VinculoEmpregado.getInstance().CONTRATADO) ) {
			if ( ( cat.getExamesConvocacao() == null || cat.getExamesConvocacao().size() == 0 ) && !cat.isAusenciaExames() ) {
				throw new Exception("Por favor, adicione exames para o atestado ou selecione ausência de exames.");
			}
			
			cat.setConvocado(true);
			
			String tipoConvocacao = "";
			Servico servico = null;
			
			if(cat.getTempoPrevisto() >= 30) {
				tipoConvocacao = TipoConvocacao.RETORNO_AO_TRABALHO;
				servico = AtestadoBo.getInstance().servicoRetornoTrabalho();
			}else {
				tipoConvocacao = TipoConvocacao.PERICIAL;
				servico = AtestadoBo.getInstance().servicoExamePericial();
			}
			
			if(!cat.isAusenciaExames() && !cat.getEmpregado().getVinculo().equals(VinculoEmpregado.getInstance().CONTRATADO)) {
				criarConvocacao(cat,tipoConvocacao);
			}
			else {
				EquipeFilter equipeFilter = new EquipeFilter();
				equipeFilter.setPageNumber(1);
				equipeFilter.setPageSize(1);
				equipeFilter.setAbreviacao("MED");
				
				Calendar data = Calendar.getInstance();
				data.setTime(getVencimentoAtestado(cat).getTime());
				data.set(Calendar.HOUR, 8);
				
				Tarefa tarefa = new Tarefa();
				tarefa.setAtualizacao(Helper.getNow());
				tarefa.setCliente(cat.getEmpregado());
				tarefa.setInicio(data.getTime());
				
				data.set(Calendar.HOUR, 16);
				
				tarefa.setFim(data.getTime());
				tarefa.setServico(servico);
				tarefa.setStatus(StatusTarefa.getInstance().ABERTA);
				tarefa.setEquipe(EquipeBo.getInstance().getList(equipeFilter).getList().get(0));
				
				TarefaBo.getInstance().save(tarefa);
			}
		}
		
		Cat newCat = super.save(cat);
		saveFiles(cat, newCat);
		return newCat;
	}
	
	@SuppressWarnings({ "unlikely-arg-type", "resource" })
	private void saveFiles(Cat cat, Cat newCat) throws URISyntaxException, IOException {
		if (cat.getArquivo() != null) {
			byte[] arquivoArray = new byte[cat.getArquivo().size()];

			for (int i = 0; i < cat.getArquivo().size(); i++) {
				arquivoArray[i] = new Integer(cat.getArquivo().get(i + "")).byteValue();
			}

			URI uri = new URI(Helper.getProjectPath()+ "cat/arquivo/" + newCat.getId() + ".zip");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			FileUtils.writeByteArrayToFile(file, arquivoArray);
			
//			FileInputStream in;
//			ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(arquivoArray));
//			FileOutputStream stream = new FileOutputStream(file);
//			ZipOutputStream zipOut = new ZipOutputStream(stream);
//			ZipEntry entry = null;
//			
//			while ((entry = zipIn.getNextEntry()) != null) {
//				entry = new ZipEntry( entry.getName() );
//				zipOut.putNextEntry(entry);
//
//				in = new FileInputStream( entry.getName() );
//				
//			    byte[] byteBuff = new byte[4096];
//			    int bytesRead = 0;
//			    while ((bytesRead = zipIn.read(byteBuff)) != -1)
//			    {
//			        zipOut.write(byteBuff, 0, bytesRead);
//			    }
//
//			    in.close();
//			    zipOut.closeEntry();    
//			    zipIn.closeEntry();
//			}
//			
//			zipOut.close();
//			zipIn.close(); 
		}
	}
	
	@SuppressWarnings("resource")
	private Cat loadFiles(Cat cat) throws URISyntaxException {

		URI uri = new URI(Helper.getProjectPath() + "cat/arquivo/"+ cat.getId() + ".zip");

		File arquivoPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(arquivoPath);
			byte[] arquivoArray = new byte[(int) arquivoPath.length()];
			fileInputStreamReader.read(arquivoArray);
			cat.setArquivoBase64(Base64.getEncoder().encodeToString(arquivoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cat;
	}
	
	private void criarConvocacao(Cat cat, String tipoConvocacao) throws Exception {
		
		Convocacao convocacao = new Convocacao();
		
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(cat.getDataOcorrencia());
		
		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		convocacaoFilter.setTitulo(tipoConvocacao+" - "+dtCalendar.get(Calendar.YEAR));
		convocacaoFilter.setTipo(tipoConvocacao);
		convocacaoFilter.setPageNumber(1);
		convocacaoFilter.setPageSize(1);
		
		PagedList<Convocacao> convocacoes = ConvocacaoBo.getInstance().getList(convocacaoFilter);
		if ( convocacoes.getTotal() > 0 ) { 
			convocacao = convocacoes.getList().get(0);
			convocacao = ConvocacaoBo.getInstance().getById(convocacao.getId());
		} else {			
			dtCalendar = Calendar.getInstance();
			dtCalendar.setTime(Helper.getToday());
			
			convocacao.setTipo(tipoConvocacao);
			convocacao.setTitulo(convocacaoFilter.getTitulo());
			convocacao.setInicio(dtCalendar.getTime());
			
			dtCalendar.set(Calendar.MONTH, 11);
			dtCalendar.set(Calendar.DATE, 31);
			
			convocacao.setFim(dtCalendar.getTime());
		}
		
		if(convocacao.getEmpregadoConvocacoes() == null)
			convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
		
		EmpregadoConvocacao empregadoConvocacao = new EmpregadoConvocacao();
		empregadoConvocacao.setEmpregado(cat.getEmpregado());
		empregadoConvocacao.setConvocacao(convocacao);
		empregadoConvocacao.setDataConvocacao(Helper.getNow());
		empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
		
		if ( cat.getExamesConvocacao() != null && cat.getExamesConvocacao().size() > 0 ) {
			for ( Exame exame: cat.getExamesConvocacao() ) {
				EmpregadoConvocacaoExame empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
				empregadoConvocacaoExame.setExame(exame);
				empregadoConvocacaoExame.setEmpregadoConvocacao(empregadoConvocacao);
				empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
			}
		}
		
		convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);
		
		if(convocacao.getId() == 0) {
			ProfissiogramaFilter profissiogramaFilter = new ProfissiogramaFilter();
			profissiogramaFilter.setNome(convocacaoFilter.getTitulo());
			profissiogramaFilter.setPageSize(1);
			profissiogramaFilter.setPageNumber(1);
			
			PagedList<Profissiograma> profissiogramas = ProfissiogramaBo.getInstance().getList(profissiogramaFilter); 
			
			if(profissiogramas.getTotal() == 0)
				throw new Exception("Não foi possível encontrar o profissiograma "+convocacaoFilter.getTitulo());
			
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
		}else
			empregadoConvocacao.setSelecionado(true);

		String pathAnexos = ConvocacaoBo.getInstance().processarConvocacaoPath(convocacao);
		
		Calendar vencimentoAtestado = getVencimentoAtestado(cat);
		
		String dtVencimento = vencimentoAtestado.get(Calendar.DATE)+ "/" +
				((Integer)(vencimentoAtestado.get(Calendar.MONTH)+1)).toString() + "/" +
				vencimentoAtestado.get(Calendar.YEAR);
		
		EmailFactory emailFactory = EmailFactory.newInstance().assunto(tipoConvocacao)
				.conteudo("Prezado(a),</br></br> informamos a necessidade da realização do "+tipoConvocacao+
						" no serviço de saúde no dia "+dtVencimento+".")
				.destinatarios(getDestinatarioEmail(cat))
				.anexos(pathAnexos);
		EmailBo.getInstance().save(emailFactory.get());
	}
	
	private Calendar getVencimentoAtestado(Cat cat) throws Exception {
		Calendar dtCalendar = Calendar.getInstance();
		dtCalendar.setTime(cat.getDataOcorrencia());
		dtCalendar.add(Calendar.DATE, cat.getTempoPrevisto());
		
		return FeriadoBo.getInstance().getValidDates(dtCalendar, 1);
	}
	
	private List<Empregado> getDestinatarioEmail(Cat cat) {
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(cat.getEmpregado());
		return empregados;
	}
	
	@SuppressWarnings({ "resource" })
	public String getComunicadoOcorrencia(Cat cat) throws Exception{
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"REPORT/ComunicacaoOcorrencia.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		//CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri;
		File pdf;
		URI uri = new URI(Helper.getProjectPath()+"comunicacaoOcorrencia/");
		File file = new File(uri.getPath());
		file.mkdirs();
		
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
		
		stringReplacer = stringReplacer
			.replace("[NOME]", Objects.toString(cat.getEmpregado().getPessoa().getNome()))
			.replace("[MATRICULA]", Objects.toString(cat.getEmpregado().getMatricula()))
			.replace("[CARGO]", Objects.toString(cat.getEmpregado().getCargo().getNome()))
			.replace("[IDADE]", Objects.toString(cat.getEmpregado().getPessoa().getIdade()))
			.replace("[EMPRESA]", Objects.toString(cat.getEmpresa().getNome()))
			.replace("[GERENCIA]", Objects.toString(cat.getGerencia().getCodigoCompleto()))
			.replace("[GERENTE]", Objects.toString(cat.getGerenteContrato()))
			.replace("[TELEFONE_GERENTE]", Objects.toString(cat.getTelefoneGerente()))
			.replace("[FISCAL]", Objects.toString(cat.getFiscalContrato()))
			.replace("[TELEFONE_FISCAL]", Objects.toString(cat.getTelefoneFiscal()))
			.replace("[LOCAL]", Objects.toString(cat.getLocal()))
			.replace("[DESCRICAO]", Objects.toString(cat.getDescricao()))
			.replace("[RESPONSAVEL]", Objects.toString(cat.getResponsavelInformacao()))
			.replace("[CARACTERIZACAO]", Objects.toString(cat.getCaracterizacao()))
			.replace("[PROFISSIONAL_CARACTERIZACAO]", Objects.toString(cat.getProfissionalCaracterizacao().getEmpregado().getPessoa().getNome()))
			.replace("[TEMPO_PREVISTO]", Objects.toString(cat.getTempoPrevisto()))
			.replace("[CID]", Objects.toString(cat.getCid().getCodigo() + " - " + cat.getCid().getDescricao()))
			.replace("[PROFISSIONAL_CLASSIFICACAO]", Objects.toString(cat.getProfissionalClassificacao().getEmpregado().getPessoa().getNome()))
			.replace("[CLASSIFICACAO]", Objects.toString(cat.getClassificacao().getDescricao()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateTime = sdf.format(cat.getDataOcorrencia());
		stringReplacer = stringReplacer.replace("[DATA_OCORRENCIA]", dateTime);
		sdf = new SimpleDateFormat("HH:mm");
		dateTime = sdf.format(cat.getDataOcorrencia());
		stringReplacer = stringReplacer.replace("[HORARIO_OCORRENCIA]", dateTime);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateTime = sdf.format(cat.getDataInformacao());
		stringReplacer = stringReplacer.replace("[DATA_INFORMACAO]", dateTime);
		sdf = new SimpleDateFormat("HH:mm");
		dateTime = sdf.format(cat.getDataInformacao());
		stringReplacer = stringReplacer.replace("[HORARIO_INFORMACAO]", dateTime);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateTime = sdf.format(cat.getDataCaracterizacao());
		stringReplacer = stringReplacer.replace("[DATA_CARACTERIZACAO]", dateTime);
		sdf = new SimpleDateFormat("HH:mm");
		dateTime = sdf.format(cat.getDataCaracterizacao());
		stringReplacer = stringReplacer.replace("[HORARIO_CARACTERIZACAO]", dateTime);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateTime = sdf.format(cat.getDataClassificacao());
		stringReplacer = stringReplacer.replace("[DATA_CLASSIFICACAO]", dateTime);
		sdf = new SimpleDateFormat("HH:mm");
		dateTime = sdf.format(cat.getDataClassificacao());
		stringReplacer = stringReplacer.replace("[HORARIO_CLASSIFICACAO]", dateTime);
		
		stringReplacer = cat.isEmpregadoServicoCompanhia() ?
				stringReplacer
					.replace("[NAO_EMPREGADO_SERVICO_COMPANHIA]", Objects.toString(""))
					.replace("[SIM_EMPREGADO_SERVICO_COMPANHIA]", Objects.toString("X")) :
				stringReplacer
					.replace("[NAO_EMPREGADO_SERVICO_COMPANHIA]", Objects.toString("X"))
					.replace("[SIM_EMPREGADO_SERVICO_COMPANHIA]", Objects.toString(""));
					
		stringReplacer = cat.isOcorrenciaAmbienteTrabalho() ?
				stringReplacer
					.replace("[NAO_OCORRENCIA_AMBIENTE_TRABALHO]", Objects.toString(""))
					.replace("[SIM_OCORRENCIA_AMBIENTE_TRABALHO]", Objects.toString("X")) :
				stringReplacer
					.replace("[NAO_OCORRENCIA_AMBIENTE_TRABALHO]", Objects.toString("X"))
					.replace("[SIM_OCORRENCIA_AMBIENTE_TRABALHO]", Objects.toString(""));					
		
		stringReplacer = cat.isOcorrenciaTrajeto() ?
				stringReplacer
					.replace("[NAO_OCORRENCIA_TRAJETO]", Objects.toString(""))
					.replace("[SIM_OCORRENCIA_TRAJETO]", Objects.toString("X")) :
				stringReplacer
					.replace("[NAO_OCORRENCIA_TRAJETO]", Objects.toString("X"))
					.replace("[SIM_OCORRENCIA_TRAJETO]", Objects.toString(""));
					
		stringReplacer = cat.isLesaoCorporal() ?
				stringReplacer
					.replace("[NAO_LESAO]", Objects.toString(""))
					.replace("[SIM_LESAO]", Objects.toString("X")) :
				stringReplacer
					.replace("[NAO_LESAO]", Objects.toString("X"))
					.replace("[SIM_LESAO]", Objects.toString(""));
					
		stringReplacer = cat.isFerimentoGrave() ?
				stringReplacer
					.replace("[NAO_FERIMENTO_GRAVE]", Objects.toString(""))
					.replace("[SIM_FERIMENTO_GRAVE]", Objects.toString("X")) :
				stringReplacer
					.replace("[NAO_FERIMENTO_GRAVE]", Objects.toString("X"))
					.replace("[SIM_FERIMENTO_GRAVE]", Objects.toString(""));			
					
		switch( cat.getNexoCausal() ) {
			case EnsaioVedacao.NAO:
				stringReplacer =  stringReplacer
					.replace("[NAO_NEXO]", Objects.toString("X"))
					.replace("[SIM_NEXO]", Objects.toString(""))
					.replace("[NAO_APLICA_NEXO]", Objects.toString(""));
				break;
			case EnsaioVedacao.NAO_APLICAVEL:
				stringReplacer =  stringReplacer
					.replace("[NAO_NEXO]", Objects.toString(""))
					.replace("[SIM_NEXO]", Objects.toString(""))
					.replace("[NAO_APLICA_NEXO]", Objects.toString("X"));
				break;
			case EnsaioVedacao.SIM:
				stringReplacer =  stringReplacer
					.replace("[NAO_NEXO]", Objects.toString(""))
					.replace("[SIM_NEXO]", Objects.toString("X"))
					.replace("[NAO_APLICA_NEXO]", Objects.toString(""));
				break;
		}
		
		stringReplacer = cat.getClassificacao().isGeraAfastamento() ?
			stringReplacer
				.replace("[AFASTAMENTO]", Objects.toString("COM"))
				.replace("[NAO_AFASTAMENTO]", "")
				.replace("[SIM_AFASTAMENTO]", "X") : 
			stringReplacer
				.replace("[AFASTAMENTO]", Objects.toString("SEM"))
				.replace("[NAO_AFASTAMENTO]", "X")
				.replace("[SIM_AFASTAMENTO]", "");
				
		pdfUri = new URI(uri.getPath()+"/"+Objects.toString(
				cat.getEmpregado().getMatricula().trim())+".pdf");
		
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
	
}
